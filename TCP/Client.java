import java.net.*;
import java.io.*;

public class Client {

	public static void main(String[] args) throws Exception {
		
		Socket socket = new Socket("127.0.0.1", 8000);
		
		BufferedReader r = new BufferedReader(
			new InputStreamReader(System.in)
		);
		
		System.out.print("Enter the filename: ");
		String filename = r.readLine();
		
		
		// send filename to server
		PrintWriter out = new PrintWriter(
			socket.getOutputStream(), true
		);
        	out.println(filename);
        	
        	
		// receieve file content
		BufferedReader in = new BufferedReader(
                	new InputStreamReader(socket.getInputStream())
     		);
  		
 		String line;
 		
        	while ((line = in.readLine()) != null) {
            		System.out.println(line);
        	}


  		socket.close();
  		r.close();
	}
}
