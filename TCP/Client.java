import java.net.*;
import java.io.*;

public class Client {

	public static void main(String[] args) throws Exception {
		
		Socket socket = new Socket("127.0.0.1", 8000);
		
		System.out.print("Enter the filename: ");
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String filename = r.readLine();
		
		OutputStream ostream = socket.getOutputStream();
		
		PrintWriter p = new PrintWriter(ostream, true);
		p.println(filename);
		
		InputStream istream = socket.getInputStream();
  		BufferedReader socketRead = new BufferedReader(new InputStreamReader(istream));
  		
 		String str;

		while ((str = socketRead.readLine()) != null){
   			System.out.println(str);
  		}

  		p.close();
  		socketRead.close();
  		r.close();
	}
}
