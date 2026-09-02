import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution
{
	static int N, M, result;
	static int[] snack;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			snack = new int[N + 1];
			result = -1;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				snack[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(snack);
			
			int p1 = 1, p2 = N;
			int curW = 0;
			while(p1 != p2) {
				curW = snack[p1] + snack[p2];
				if (curW > M) p2--;
				else if (curW < M) {
					result= curW > result ? curW : result;
					p1++;
				} else {
					result = M;
					break;
				}
			}
			
			sb.append('#').append(tc)
				.append(' ').append(result)
				.append(System.lineSeparator());
		}
		System.out.print(sb);
	}
}