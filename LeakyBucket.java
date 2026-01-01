import java.util.*;

class LeakyBucket {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the bucket capacity: ");
		int capacity = sc.nextInt();
		
		System.out.print("\nEnter the output rate (packet per second): ");
		int rate = sc.nextInt();
		
		System.out.print("\nEnter the number of packets: ");
		int n = sc.nextInt();
		
		int[] packets = new int[n];
		
		System.out.print("\nEnter the packet sizes: ");
		
		for(int i = 0; i < n; i++) {
			packets[i] = sc.nextInt();
		}
		
		int curr = 0;
        	System.out.println("\nPacket Size\tBucket Size\tSent\tRemaining\tStatus");
        	
        	for(int p: packets) {
        		if(curr + p <= capacity) {
        			curr += p;
        			System.out.println(p + "\t\t" + curr + "\t\t" + Math.min(rate, curr) + "\t" + Math.max(0, curr - rate) + "\tAccepted");
        		} else {
        			System.out.println(p + "\t\t" + curr + "\t\t" + Math.min(rate, curr) + "\t" + Math.max(0, curr - rate) + "\tDropped");
        		}
        		
        		curr = Math.max(0, curr - rate);
        	}
        	
        	sc.close();

		
	}

}
