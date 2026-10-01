import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Solution
{
	static int n, digitNum, maxCnt; // n은 1이상 99999 이하

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuilder sb = new StringBuilder();
		
		in.nextToken();
		int T = (int) in.nval;

		for(int tc = 1; tc <= T; tc++) {
			in.nextToken();
			n = (int) in.nval;
			maxCnt = 0;
			digitNum = 0;
			
			dfs(n, 0);
			
			sb.append('#').append(tc).append(' ').append(maxCnt).append('\n');
		}
		System.out.println(sb);
	}
	
	static void dfs(int n, int cnt) {
		if (n < 10) {
			maxCnt = Math.max(maxCnt, cnt);
			return;
		}
		
		int digitNum = 0;
		for(int d = n; d > 0; d /= 10) digitNum++;
		
		for(int i = 1; i < (1 << (digitNum - 1)); i++) {
			int prod = 1;
			int rest = n; // 아직 안 자른 부분
			int divNum = 1; // 현재 조각 자릿값 ( 10 ^ 조각 길이 )
			for(int j = 0; j < digitNum - 1; j++) {
				divNum *= 10;
				if ((i & (1 << j)) != 0) {
					prod *= rest % divNum;  // 오른쪽 조각
					rest /= divNum;  // 남은 왼쪽 부분
					divNum = 1;  // 새 조각
				}
			}
			
			dfs(prod * rest, cnt + 1);
		}
	}
}