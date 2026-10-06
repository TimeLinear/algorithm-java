import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution
{
	static int V, E;
	static int[] parents;
	static List<int[]> edges;
	static long result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); 
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			result = 0;
			
			parents = new int[V + 1];
			for(int i = 1; i <= V; i++) {
				parents[i] = i;
			}
			
			edges = new ArrayList<>();
					
			int fv, sv, w;
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				fv = Integer.parseInt(st.nextToken());
				sv = Integer.parseInt(st.nextToken());
				w = Integer.parseInt(st.nextToken());
				
				edges.add(new int[] {fv, sv, w});
			}
			
			edges.sort((a, b) -> a[2] - b[2]);
			
			int x, y;
			for(int[] edge : edges) {
				x = find(edge[0]);
				y = find(edge[1]);
				if (x != y) {
					parents[x] = y;
					result += edge[2];
				}
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
    
    static int find(int x) {
		if (parents[x] == x) {
			return x;
		} else {
			return parents[x] = find(parents[x]);
		}
	}
}