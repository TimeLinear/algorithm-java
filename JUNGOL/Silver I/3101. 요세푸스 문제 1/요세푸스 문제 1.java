import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	static int N, K;
	static int[] q;
	static int head, tail;
	
	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuilder sb = new StringBuilder();
		
		in.nextToken();
		N = (int) in.nval;
		
		in.nextToken();
		K = (int) in.nval;
		
		// 최악의 케이스가 N * 3이라고 함
		q = new int[N * 3];
		for (int i = 0;  i < N; i++) q[i] = i;
		
		head = 0;
		tail = N;
		
		int remains = N;
		while (remains > 0) {
			int step = (K - 1) % remains;
			
			for (int i = 0; i < step; i++) {
				int val = q[head++];
				q[tail++] = val;
			}
			sb.append(q[head] + 1).append(' ');
			head++;
			remains--;
			
			if (head >= N) {
				int size = tail - head;
				System.arraycopy(q, head, q, 0, size);
				head = 0;
				tail = size;
			}
		}
		
		System.out.print(sb);
	}
}