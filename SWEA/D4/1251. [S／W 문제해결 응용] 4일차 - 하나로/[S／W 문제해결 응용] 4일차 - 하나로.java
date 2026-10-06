import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Solution
{
	static int N, cnt;
	static double E;
	static int[] parents;
	static List<Edge> edges;
	static long result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			parents = new int[N];
			Arrays.fill(parents, -1);
			result = 0;
			cnt = 0;
			
			int[] posX = new int[N];
			int[] posY = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				posX[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				posY[i] = Integer.parseInt(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			
			edges = new ArrayList<>(N * (N - 1) / 2);
			for(int i = 0; i < N - 1; i++) {
				for(int j = i + 1; j < N; j++) {
					long xd = posX[i] - posX[j];
					long yd = posY[i] - posY[j];
					edges.add(new Edge(i, j, xd * xd + yd * yd));
				}
			}
			
			Collections.sort(edges);
			
			int x, y;
			for(Edge e : edges) {
				if (cnt == N - 1) break;
				x = find(e.from);
				y = find(e.to);
				if (x != y) {
					if (parents[x] <= parents[y]) {   // x 쪽 집합이 사이즈가 같거나 더 큼 (절대값이 더 큰 음수)
						parents[x] += parents[y];
						parents[y] = x;
					} else {
						parents[y] += parents[x];
						parents[x] = y;
					}
					result += e.weight;
					cnt++;
				}
			}
			sb.append("#").append(tc).append(" ").append(Math.round(result * E)).append("\n");
		}
		System.out.println(sb);
	}
	
	static int find(int x) {
		if (parents[x] < 0) {
			return x;
		} else {
			return parents[x] = find(parents[x]);
		}
	}
    
    static class Edge implements Comparable<Edge> {
		int from, to;
		long weight;
		
		Edge(int from, int to, long weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}
}