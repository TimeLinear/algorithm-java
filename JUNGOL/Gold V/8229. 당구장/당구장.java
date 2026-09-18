import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
    static int N;
	static long X;
	static int[] p, a;
	static long[] dist;
	static int[] state;
	static int maxNum;

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

		in.nextToken();
		N = (int) in.nval;
		in.nextToken();
		X = (long) in.nval;

		p = new int[N + 1];
		a = new int[N + 1];
		dist = new long[N + 1];
		state = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			in.nextToken();
			a[i] = (int) in.nval;
		}
		for (int i = 1; i <= N; i++) {
			in.nextToken();
			p[i] = (int) in.nval;
		}

		maxNum = -1;
		int[] stack = new int[N];

		for (int start = 1; start <= N; start++) {
			if (state[start] != 0)
				continue;

			int top = 0;
			int cur = start;

			while (cur != -1 && state[cur] == 0) {
				state[cur] = 1;
				stack[top++] = cur;
				cur = p[cur];
			}

			long base;
			boolean valid;
			if (cur == -1) {
				base = 0;
				valid = true;
			} else if (state[cur] == 2) {
				base = dist[cur];
				valid = true;
			} else {
				base = -1;
				valid = false;
			}

			for (int k = top - 1; k >= 0; k--) {
				int node = stack[k];
				if (valid) {
					base += a[node];
					dist[node] = base;
					state[node] = 2;
				} else {
					state[node] = 3;
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			if (state[i] == 2 && dist[i] <= X) {
				maxNum = Math.max(maxNum, i);
			}
		}

		System.out.println(maxNum);
	}
}