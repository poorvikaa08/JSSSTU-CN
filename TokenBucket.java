import java.util.*;

public class TokenBucket {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);

        System.out.print("Enter bucket capacity (number of tokens): ");
        int capacity = sc.nextInt();
        
        System.out.print("Enter token generation rate (tokens per second): ");
        int tokenRate = sc.nextInt();


        System.out.print("Enter the number of packets: ");
        int n = sc.nextInt();
        
        int[] packets = new int[n];
        
        System.out.println("Enter the packet sizes: ");
        for (int i = 0; i < n; i++) {
            packets[i] = sc.nextInt();
        }

        int tokens = 0;
        int sent = 0;
        
        
        System.out.println("\nPacket Size\tTokens Available\tSent\tTokens Remaining\tStatus");
        
        for (int p : packets) {
           	 // Generate tokens for this iteration
            	tokens = Math.min(tokens + tokenRate, capacity);
		
		int before = tokens;
            	// Check if packet can be processed
            	if (p <= tokens) {
                	tokens -= p;
                	sent = p;
                	System.out.println(p + "\t\t" + before + "\t\t\t" + sent + "\t" + tokens +  "\t\t\tAccepted");
            	} else {
                	sent = 0;
                	System.out.println(p + "\t\t" + tokens + "\t\t\t" + sent + "\t" + tokens + "\t\t\tDropped");
            	}
        }
        
        sc.close();
    }
}
