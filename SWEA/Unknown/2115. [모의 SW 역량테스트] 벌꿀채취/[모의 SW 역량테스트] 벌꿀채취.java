import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

class Solution
{
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int n, m, c;
	static int[][] map, profit;
	
	public static void main(String[] args) throws IOException {
		int T = nextInt();
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= T; tc++) {
			n = nextInt();
			m = nextInt();
			c = nextInt();
			
			map = new int[n][n];
			profit = new int[n][n - m + 1];
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					map[i][j] = nextInt();
				}
			}
			
			// 미리 모든 점에서의 최적해 계산
			for (int i = 0; i < n; i++) {
				for(int j = 0; j <= n - m; j++) {
					calcProfit(i, j);
				}
			}
			
			// 두 일꾼의 채취 장소 선택
			int maxProfit = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j <= n - m; j++) {
					for(int k = i; k < n; k++) {
						// 주의. 중복 조합 제거
						int start = (k == i) ? j + m : 0;
						for(int l = start; l <= n - m; l++) {
							maxProfit = Math.max(maxProfit, profit[i][j] + profit[k][l]);
						}
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(maxProfit).append('\n');
		}
		System.out.print(sb);
	}
	
	static int calcProfit(int row, int col) {
		// m 칸 중에 c를 넘지않는 선에서 어떤 칸이 몇 칸 선택될 지 알 수 없다.
		// combination으로 조건 따져가면서 고르는 것이 좋겠음.
		int[] nums = Arrays.copyOfRange(map[row], col, col + m);
		
		int maxProfit = 0;
		Outer:
		for(int flag = 0; flag < (1 << m); flag++) {
			int sum = 0, cur = 0;
			for(int i = 0; i < m; i++) {
				if ((flag & (1 << i)) != 0) {
					// c를 넘는지 보면서
					sum += nums[i];
					if (sum > c) continue Outer;
					// 실제 이득값 계산
					cur += nums[i] * nums[i];
				}
			}
			
			maxProfit = Math.max(maxProfit, cur);
		}
		
		return profit[row][col] = maxProfit; 
	}
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}