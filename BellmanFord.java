import java.util.*;

class BellmanFord {
	public static void bellmanford(int n, int source, int[][] adj) {
		int[] distance = new int[n];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[source] = 0;
		
		for(int i = 0; i < n - 1; i++) {
			for(int u = 0; u < n; u++) {
				for(int v = 0; v < n; v++) {
					if (adj[u][v] != -1 && distance[u] != Integer.MAX_VALUE && distance[u] + adj[u][v] < distance[v]) {
    						distance[v] = distance[u] + adj[u][v];
}
				}
			}
		}
		
		
		// to detect negative weight cycle
		for(int u = 0; u < n; u++) {
			for(int v = 0; v < n; v++) {
				if(adj[u][v] != -1 && distance[u] != Integer.MAX_VALUE && distance[u] + adj[u][v] < distance[v]) {
					System.out.println("Negative weight cycle detected!");
					return;
				}
			}
		}
		
		
		System.out.println("\n\nVertex\tDist from source");
		for(int i = 0; i < n; i++) {
			System.out.println((i + 1) + " \t" + (distance[i] == Integer.MAX_VALUE ? "-1" : distance[i]));
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the number of vertices: ");
		int n = sc.nextInt();
		
		int[][] matrix = new int[n][n];
		
		System.out.println("\nEnter the weight matrix (use -1 for no edge): ");
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		
		System.out.print("\nEnter the source vertex (1 based index): ");
		int source = sc.nextInt();
		
		bellmanford(n, source - 1, matrix);
		sc.close();
	}
}
