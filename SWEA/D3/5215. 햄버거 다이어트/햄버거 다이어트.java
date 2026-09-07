import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Solution
{
	static int N, L;
	static int[] taste;
	static int[] kalory;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuilder sb = new StringBuilder();
		
		in.nextToken();
		int T = (int) in.nval;
		
		for(int tc = 1; tc <= T; tc++) {
			in.nextToken();
			N = (int) in.nval;
			in.nextToken();
			L = (int) in.nval;
			
			dp = new int[L + 1]; // 자동으로 0으로 초기화됨
            taste = new int[N];
            kalory = new int[N];
			
			for(int i = 0; i < N; i++) {
				in.nextToken();
				taste[i] = (int) in.nval;
				
				in.nextToken();
				kalory[i] = (int) in.nval;
			}
			
			for (int i = 0; i < N; i++) {
                for (int j = L; j > kalory[i] - 1; j--) {
                    dp[j] = Math.max(dp[j], dp[j - kalory[i]] + taste[i]);
                }
            }
			
			sb.append('#').append(tc).append(' ').append(dp[L]).append('\n');
		}
		System.out.print(sb);
	}
}