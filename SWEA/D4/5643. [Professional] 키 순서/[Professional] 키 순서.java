import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

class Solution
{
	static int N, M;
	static int[] tp;
	static int[] q;
	static List<Integer>[] adj;
	static int head, tail;
	static BitSet[] taller, smaller;
	
	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuilder sb = new StringBuilder();
		
		in.nextToken();
		int T = (int) in.nval;
		
		for(int tc = 1; tc <= T; tc++) {
			in.nextToken();
			N = (int) in.nval;
			
			in.nextToken();
			M = (int) in.nval;
			
			head = 0;
			tail = 0;
			
			q = new int[N + 1];
			tp = new int[N + 1];
			adj = new List[N + 1];
			
			for(int i = 1; i <= N; i++) {
				adj[i] = new ArrayList<>();
			}
			
            int n1 = 0, n2 = 0;
			for(int i = 0; i < M; i++) {
				in.nextToken();
                n1 = (int) in.nval;
                in.nextToken();
                n2 = (int) in.nval;
                adj[n1].add(n2);
                tp[n2]++;
			}
			
			for(int i = 1; i <= N; i++) {
				if (tp[i] == 0) {
					q[tail++] = i;
				}
			}
			
			int cur;
			while(head < tail) {
				cur = q[head++];

				for (int next : adj[cur]) {
                    if (--tp[next] == 0) {
                        q[tail++] = next;
                    }
                }
			}
			
			taller = new BitSet[N + 1];
			smaller = new BitSet[N + 1];
			for(int i = 1; i <= N; i++) {
				taller[i] = new BitSet(N + 1);
				smaller[i] = new BitSet(N + 1);
			}
			
			for (int i = 0; i < N; i++) {
				int node = q[i];
				for (int next : adj[node]) {
					taller[next].or(taller[node]);
					taller[next].set(node);
				}
			}
			
			for (int i = N - 1; i >= 0; i--) {
				int node = q[i];
				for (int next : adj[node]) {
					smaller[node].or(smaller[next]);
					smaller[node].set(next);
				}
			}
			
			int answer = 0;
			for (int i = 1; i <= N; i++) {
				if (taller[i].cardinality() + smaller[i].cardinality() == N - 1) {
					answer++;
				}
			}
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}
}