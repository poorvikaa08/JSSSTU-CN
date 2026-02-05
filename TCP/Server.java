import java.net.*;
import java.io.*;

public class Server {

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(8000);
		
		System.out.println("Server is ready for connection");
		
		Socket socket = server.accept();
		System.out.println("Connection is successful and waiting for the client request");
		
		// read filename from client
		BufferedReader in = new BufferedReader(
			new InputStreamReader(socket.getInputStream())
		);
		
		String filename = in.readLine();
		
		// read file content
		BufferedReader file = new BufferedReader(new FileReader(filename));
		
		// send content to client
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		String line;
		
		while ((line = file.readLine()) != null) {
			out.println(line);
		}
		
		socket.close();
		server.close();
		file.close();
	}
}



	       /*InputStream istream = socket.getInputStream();
		
		BufferedReader fileRead = new BufferedReader(new InputStreamReader(istream));
		
		String filename = fileRead.readLine();
		
		BufferedReader contentRead = new BufferedReader(new FileReader(filename));
		
		OutputStream ostream = socket.getOutputStream();
		PrintWriter p = new PrintWriter(ostream, true);
		*/
