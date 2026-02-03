
set ns [new Simulator]
create-god 6

set topo [new Topography]
$topo load_flatgrid 1500 1500


set ntrace [open network.tr w]
set namfile   [open network.nam w]

$ns trace-all $ntrace
$ns namtrace-all $namfile
$ns namtrace-all-wireless $namfile 1500 1500


# node configuration
$ns node-config \
    -adhocRouting DSDV \
    -llType LL \
    -macType Mac/802_11 \
    -ifqType Queue/DropTail \
    -ifqLen 20 \
    -phyType Phy/WirelessPhy \
    -channelType Channel/WirelessChannel \
    -propType Propagation/TwoRayGround \
    -antType Antenna/OmniAntenna \
    -topoInstance $topo \
    -agentTrace ON \
    -routerTrace ON

set coords {
    {630 501}
    {454 340}
    {785 326}
    {270 190}
    {539 131}
    {964 177}
}

for {set i 0} {$i < 6} {incr i} {
    set n($i) [$ns node]
    $n($i) set X_ [lindex $coords $i 0]
    $n($i) set Y_ [lindex $coords $i 1]
    $n($i) set Z_ 0.0
    $ns initial_node_pos $n($i) 20
}


# UDP: node 0 -> node 4
set udp0 [new Agent/UDP]
set null0 [new Agent/Null]
$ns attach-agent $n(0) $udp0
$ns attach-agent $n(4) $null0
$ns connect $udp0 $null0
$udp0 set packetSize_ 1500

# TCP: node 3 -> node 5
set tcp0 [new Agent/TCP]
set sink0 [new Agent/TCPSink]
$ns attach-agent $n(3) $tcp0
$ns attach-agent $n(5) $sink0
$ns connect $tcp0 $sink0


set cbr0 [new Application/Traffic/CBR]
$cbr0 attach-agent $udp0
$cbr0 set packetSize_ 1000
$cbr0 set rate_ 1.0Mb
$cbr0 set random_ false

set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0


proc finish {} {
    global ns tracefile namfile
    $ns flush-trace
    close $ntrace
    close $namfile

    exec nam lab13.nam &
    exec echo "Number of packets dropped:" &
    exec grep -c "^D" network.tr &
    exit 0
}


$ns at 1.0   "$cbr0 start"
$ns at 2.0   "$ftp0 start"
$ns at 180.0 "$ftp0 stop"
$ns at 200.0 "$cbr0 stop"
$ns at 200.0 "finish"

# Node 4 movement
$ns at 70  "$n(4) set dest 100 60 20"
$ns at 100 "$n(4) set dest 700 300 20"
$ns at 150 "$n(4) set dest 900 200 20"


$ns run

