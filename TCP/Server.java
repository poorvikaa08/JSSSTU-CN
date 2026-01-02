import java.net.*;
import java.io.*;

public class Server {

	public static void main(String[] args) throws Exception {
		ServerSocket s = new ServerSocket(8000);
		
		System.out.println("Server is ready for connection");
		
		Socket socket = s.accept();
		System.out.println("Connection is successful and waiting for the client request");
		
		InputStream istream = socket.getInputStream();
		
		BufferedReader fileRead = new BufferedReader(new InputStreamReader(istream));
		
		String filename = fileRead.readLine();
		
		BufferedReader contentRead = new BufferedReader(new FileReader(filename));
		
		OutputStream ostream = socket.getOutputStream();
		PrintWriter p = new PrintWriter(ostream, true);
		
		
		String str;
		
		while ((str = contentRead.readLine()) != null) {
			p.println(str);
		}
		
		socket.close();
		s.close();
		p.close();
		contentRead.close();
	}
}
