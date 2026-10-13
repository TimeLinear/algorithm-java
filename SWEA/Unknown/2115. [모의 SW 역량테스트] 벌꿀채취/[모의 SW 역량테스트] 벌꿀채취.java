import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

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
					profit[i][j] = dfs(i, j, 0, 0);
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
	
	// dfs로도 구현 가능
	static int dfs(int row, int col, int idx, int sum) {
	    if (idx == m) return 0;

	    int val = map[row][col + idx];
	    
	    // 안 넣는 경우
	    int best = dfs(row, col, idx + 1, sum);

	    // 넣는 경우 (용량을 넘지 않을 때만)
	    if (sum + val <= c) {
	        best = Math.max(best, val * val + dfs(row, col, idx + 1, sum + val));
	    }
	    return best;
	}
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}