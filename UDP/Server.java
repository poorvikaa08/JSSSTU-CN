import java.net.*;

class Server { 
        public static void main(String args[]) throws Exception {
                DatagramSocket socket = new DatagramSocket(5454);
                System.out.println("UDP Server is Ready for the client");
                
                byte[] receiveData = new byte[1024];

                
                while (true) {
                	// recieve packet
                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        socket.receive(receivePacket);
                        
                        String msg = new String(receivePacket.getData());
                        System.out.println("RECEIVED: " + msg);
                        
                        // convert to uppercase
			byte[] sendData = msg.toUpperCase().getBytes();
                       
			// send back to client
                        DatagramPacket sendPacket = new DatagramPacket(
                        	sendData, sendData.length,
                        	receivePacket.getAddress(), receivePacket.getPort()
                        );
                        
                        socket.send(sendPacket);
                }
        }
}
