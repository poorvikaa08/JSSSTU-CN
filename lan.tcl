set ns [new Simulator]

set ntrace [open lan.tr w]
$ns trace-all $ntrace
set namfile [open lan.nam w]
$ns namtrace-all $namfile

$ns color 1 blue
$ns color 2 red

set winFile0 [open WinFile0 w]
set winFile1 [open WinFile1 w]

proc Finish {} {
	global ns ntrace namfile
	$ns flush-trace
	close $namfile
	close $ntrace
	
	exec nam lan.nam &
	
	exec xgraph WinFile0 WinFile1 &
	exit 0
}

#plot window procedure  (x, y) => (time, cwnd)
proc PlotWindow {tcpSource file} {
	global ns
	set time 0.1
	set now [$ns now]
	
	set cwnd [$tcpSource set cwnd_]
	puts $file "$now $cwnd"
	$ns at [expr $now+$time] "PlotWindow $tcpSource $file"
}

for {set i 0} {$i < 6} {incr i} {
	set n($i) [$ns node]
}

$ns duplex-link $n(0) $n(2) 2Mb 10ms DropTail
$ns duplex-link $n(1) $n(2) 2Mb 10ms DropTail
$ns duplex-link $n(2) $n(3) 0.6Mb 100ms DropTail

set lan [$ns newLan "$n(3) $n(4) $n(5)" 0.5Mb 40ms LL Queue/DropTail MAC/802_3 Channel]

#or 

# $ns newLan "$n(3) $n(4) $n(5)" 0.5Mb 40ms

$ns duplex-link-op $n(0) $n(2) orient right-down
$ns duplex-link-op $n(1) $n(2) orient right-up
$ns duplex-link-op $n(2) $n(3) orient right

$ns queue-limit $n(2) $n(3) 20
$ns duplex-link-op $n(2) $n(3) queuePos 0.5


#error model
set loss_module [new ErrorModel]
$loss_module ranvar [new RandomVariable/Uniform]
$loss_module drop-target [new Agent/Null]
$ns lossmodel $loss_module $n(2) $n(3)


# Set up the TCP connection between n(0) and n(4)
set tcp0 [new Agent/TCP/Newreno]
$tcp0 set fid_ 1             #fid = flow id -> to visualize the flow by diff colors
$tcp0 set window_ 8000
$ns attach-agent $n(0) $tcp0

set sink0 [new Agent/TCPSink/DelAck]
$ns attach-agent $n(4) $sink0

$ns connect $tcp0 $sink0

#we are using ftp instead of cbr for traffic
#apply ftp application over tcp

set ftp0 [new Application/FTP]
$ftp0 set type_ FTP   #optional
$ftp0 attach-agent $tcp0


# Set up another TCP connection between n(5) and n(1)

set tcp1 [new Agent/TCP/Newreno]
$tcp1 set window_ 8000
$tcp1 set fid_ 2
$ns attach-agent $n(5) $tcp1

set sink1 [new Agent/TCPSink/DelAck]
$ns attach-agent $n(1) $sink1

$ns connect $tcp1 $sink1

set ftp1 [new Application/FTP]
$ftp1 set type_ FTP
$ftp1 attach-agent $tcp1



#schedule events

$ns at 0.1 "$ftp0 start"
$ns at 0.1 "PlotWindow $tcp0 $winFile0"
$ns at 0.5 "$ftp1 start"
$ns at 0.5 "PlotWindow $tcp1 $winFile1"
$ns at 25.0 "$ftp0 stop"
$ns at 25.1 "$ftp1 stop"
$ns at 25.2 "Finish"


#run the simulation
$ns run


