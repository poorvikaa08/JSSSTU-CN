import java.io.*;
import java.net.*;

class Client {
	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket();
                InetAddress IPAddress = InetAddress.getByName("localhost");
                
                BufferedReader r = new BufferedReader(
                	new InputStreamReader(System.in)
                );
                
                System.out.println("Enter the text in lowercase: ");
		byte[] sendData = r.readLine().getBytes();

		// send to server		
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 5454);
        	socket.send(sendPacket);
        	
               	// receive from server
               
             	byte[] receiveData = new byte[1024];
        	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        	socket.receive(receivePacket);

        	String reply = new String(
                	receivePacket.getData(), 0, receivePacket.getLength()
        	);
        	
        	System.out.println("FROM SERVER: " + reply);
                
                socket.close();
        }
}
