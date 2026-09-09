import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Solution
{
	static int N, M, ans;
	static int[] mask;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuilder sb = new StringBuilder();

		in.nextToken();
		int T = (int) in.nval;

		for (int tc = 1; tc <= T; tc++) {
			in.nextToken();
			N = (int) in.nval;
			in.nextToken();
			M = (int) in.nval;

			ans = 0;

			mask = new int[N + 1];
			visited = new boolean[(1 << N)];

			for (int i = 0; i < M; i++) {
				in.nextToken();
				int idx = (int) in.nval;
				in.nextToken();
				int val = (int) in.nval;
				mask[idx] |= (1 << (val - 1));
				mask[val] |= (1 << (idx - 1));
			}

			dfs(0, 0);

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		System.out.print(sb);
	}

	static void dfs(int idx, int flag) {
		if (idx == N) {
			ans++;
			return;
		}

		dfs(idx + 1, flag);
		
		if ((mask[idx + 1] & flag) == 0) {
	        dfs(idx + 1, flag | (1 << idx));
	    }
	}
}