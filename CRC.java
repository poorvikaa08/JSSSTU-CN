import java.util.*;


class CRC {
	public static String divide(char[] message, char[] key){
		int n = message.length, m = key.length;
		
		for(int i = 0; i <= n - m; i++) {
			if(message[i] == '1') {
				//if(n - 1 < m) break;
				if (i + m > n) break;
				
				for(int j = 0; j < m; j++) {
					//message[i + j] = message[j] == '0' ? message[i + j] : message[i + j] == '1' ? '0' : '1';				
					message[i + j] = (message[i + j] == key[j]) ? '0' : '1';
				}
			}
		}
		
		return new String(message).substring(n - m + 1);
	}
	
	public static String encode(String data, String key) {
		return data + divide((data + "0".repeat(key.length() - 1)).toCharArray(), key.toCharArray());
	}
	
	public static boolean decode(String encodedData, String key) {
		return divide(encodedData.toCharArray(), key.toCharArray()).contains("1");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the binary data: ");
		String data = sc.next();
		
		System.out.print("\nEnter the key: ");
		String key = sc.next();
		
		System.out.println("Encoded data = " + encode(data, key));
		
		System.out.print("\nEnter the recieved data: ");
		String encodedData = sc.next();
		
		System.out.println(decode(encodedData, key) ? "Error in the data" : "Data is error free");
		
		sc.close();
		
	}

}
