import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

class Solution
{
	static StreamTokenizer in;
	static int N, M;
	static int[] p = new int[101];
	static boolean[] roots = new boolean[101];

	public static void main(String[] args) {
		in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuilder sb = new StringBuilder();

		int T = nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = nextInt();
			M = nextInt();
			
			Arrays.fill(p, 0, N + 1, 0);
			Arrays.fill(roots, 0, N + 1, false);
			
			for(int i = 1; i <= N; i++) {
				p[i] = i;
			}
			
			for(int i = 0; i < M; i++) {
				int s1 = nextInt();
				int s2 = nextInt();
				union(s1, s2);
			}
			
			int cnt = 0;
			for(int i = 1; i < N + 1; i++) {
				int root = find(i);
				if (!roots[root]) {
					roots[root] = true;
					cnt++;
				}
			}
			
			sb.append('#').append(tc).append(' ').append(cnt).append('\n');
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
		p[r1] = r2;
	}
	
	static int nextInt() {
		try {
			in.nextToken();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (int) in.nval;
	}
}