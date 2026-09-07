import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Solution
{
	static int N, L;
	static int[][] food; // food[i][0] = taste, food[i][1] = kalory
	static int result;
	static int minKal;

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
			
			food = new int[N][2];
			
			minKal = Integer.MAX_VALUE;
			
			result = 0;
			
			for(int i = 0; i < N; i++) {
				in.nextToken();
				food[i][0] = (int) in.nval;
				
				in.nextToken();
				food[i][1] = (int) in.nval;
				
				if (minKal > food[i][1]) {
					minKal = food[i][1];
				}
			}
			
			dfs(0, 0, 0);
			
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	static void dfs(int cnt, int taste, int kalory) {
		if ((cnt == N && kalory <= L) || kalory + minKal > L) {
			result = result < taste ? taste : result;
			return;
		}
		
		if (kalory + food[cnt][1] > L) {
			dfs(cnt + 1, taste, kalory);
		} else {
			dfs(cnt + 1, taste + food[cnt][0], kalory + food[cnt][1]);
			dfs(cnt + 1, taste, kalory);
		}
	}
}