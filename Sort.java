import java.util.*;

class Sort {
	public static class Frame {
		int fnum;
		String content;
		
		Frame(int n, String s) {
			this.fnum = n;
			this.content = s;
		}
	}
	
	public static void sorting(int n, Frame[] F) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n - i - 1; j++) {
				if(F[j].fnum > F[j + 1].fnum) {
					String s1 = F[j].content;
					String s2 = F[j + 1].content;
					int n1 = F[j].fnum; 
					int n2 = F[j + 1].fnum;
					F[j].fnum = n2; 
					F[j + 1].fnum = n1;
					F[j].content = s2;
					F[j + 1].content = s1;
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
			
		System.out.print("Enter the number of frames: ");
		int n = sc.nextInt();
		
		Frame[] F = new Frame[n];
		
		
		for(int i = 0; i < n; i++) {
			System.out.print("Frame number: ");
			int num = sc.nextInt();
			
			System.out.print("Frame content:");
			String str = sc.next();
			
			F[i] = new Frame(num, str);
		}
		
		/*
		List <Frame> framelist = new ArrayList<>(Arrays.asList(F));
		
		Collections.shuffle(framelist);
		F = framelist.toArray(new Frame[0]);
		
		
		*/
		
		// shuffle the input array
		
		List <Frame> list = new ArrayList<>();
		for (Frame f: F) list.add(f);
		
		Collections.shuffle(list);
		
		F = list.toArray(new Frame[0]);
		
		
		System.out.println("Frames before sorting");
		for(int i = 0; i < n; i++) {
			System.out.println("F" + F[i].fnum + ": " + F[i].content);
		}
		
		sorting(n, F);
		
		System.out.println("\nFrames after sorting");
		for(int i = 0; i < n; i++) {
			System.out.println("F" + F[i].fnum + ": " + F[i].content);
		}
		
		sc.close();
		
	}

}
