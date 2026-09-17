import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Solution
{
	static StreamTokenizer in;
	static int N, M;
	static int[] p, rank;

	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuilder sb = new StringBuilder();

		int T = nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = nextInt();
			M = nextInt();
			
			p = new int[N + 1];
			rank = new int[N + 1];
			
			for(int i = 1; i <= N; i++) {
				p[i] = i;
				rank[i] = 0;
			}
			
			sb.append('#').append(tc).append(' ');
			
			for(int i = 0; i < M; i++) {
				int cmd = nextInt();
				int s1 = nextInt();
				int s2 = nextInt();
				if (cmd == 0) union(s1, s2);
				else {
					boolean isUnion = find(s1) == find(s2);
					sb.append(isUnion ? 1 : 0);
				}
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}

	static int find(int x) {
		if (p[x] == x) return x;
		return p[x] = find(p[x]);
	}
	
	static void union(int s1, int s2) {
		int r1 = find(s1);
		int r2 = find(s2);
		if (r1 == r2) return;
		if (rank[r1] > rank[r2]) p[r2] = r1;
		else {
			p[r1] = r2;
			if (rank[r1] == rank[r2]) rank[r2]++;
		}
	}
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}