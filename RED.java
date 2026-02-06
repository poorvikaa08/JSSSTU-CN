import java.util.*;

class RED {
	static class RandomEarlyDetection {
		private double minT, maxT, maxDropProb;
		private int size, curr;
		private Random r = new Random();
		
		public RandomEarlyDetection(double min, double max, double prob, int n) {
			minT = min;
			maxT = max;
			maxDropProb = prob;
			size = n;
			curr = 0;
		}
		
		public double calculateDropProb() {
			if (curr < minT) return 0.0;
			else if (curr >= maxT) return maxDropProb;
			else {
				double p = maxDropProb * ((curr - minT) / (maxT - minT)); 
				return p;
			}
		}
		
		public boolean shouldDrop(double prob) {

			return r.nextDouble() < prob;
		}
		
		public boolean enqueue() {
			if(curr >= size) {
				System.out.println("Packet Dropped. Queue Full!");			
				return false;
			}
			
			double prob = calculateDropProb();
			
			if(prob > 0 && shouldDrop(prob)) {
				System.out.println("Packet Dropped. RED!");
				return false;
			}
			
			curr++;
			System.out.println("Packet enqued. Current queue size = " + curr);
			return true;
			
		}
		
	}
	
	public static void main(String[] args){
		        Scanner sc = new Scanner(System.in);
		        
		        System.out.print("Enter the minimum threshold: ");
		        double min = sc.nextDouble();
		        
		        System.out.print("Enter the maximum threshold: ");
		        double max = sc.nextDouble();
		        
		        System.out.print("Enter the maximum drop probability (0-1): ");
		        double prob = sc.nextDouble();
		        
		        System.out.print("Enter the queue size: ");
		        int size = sc.nextInt();
		        
		        System.out.print("Enter the number of packets: ");
		        int n = sc.nextInt();

		        RandomEarlyDetection red = new RandomEarlyDetection(min, max, prob, size);
		        
		        for(int i = 0; i < n; i++){
		                red.enqueue();
		        }
		        
		        sc.close();
		}

}
