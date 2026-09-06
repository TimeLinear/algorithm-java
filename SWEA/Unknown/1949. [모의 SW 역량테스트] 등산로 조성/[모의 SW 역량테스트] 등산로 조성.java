import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Solution
{
	static int N, K;
	static int[][] map = new int[8][8];
	static int maxLen;
	static int[] visited;
	static final int[] DY = {-1, 1, 0, 0};
	static final int[] DX = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuilder sb = new StringBuilder();
		
		in.nextToken();
		int T = (int) in.nval;
		
		for(int tc = 1; tc <= T; tc++) {
			in.nextToken();
			N = (int) in.nval;
			
			in.nextToken();
			K = (int) in.nval;
			
			maxLen = 0;
			visited = new int[N];
			
			int maxVal = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					in.nextToken();
					map[i][j] = (int) in.nval;
					if (map[i][j] > maxVal) maxVal = map[i][j];
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if (map[i][j] == maxVal) {
						dfs(i, j, 1, false);
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(maxLen).append('\n');
		}
		System.out.print(sb);
	}

	static void dfs(int y, int x, int curLen, boolean k_used) {
		if (maxLen < curLen) maxLen = curLen;
		visited[y] |= (1 << x);
		
		for(int d = 0; d < 4; d++) {
			int ny = y + DY[d];
			int nx = x + DX[d];
			
			if (ny >= N || ny < 0 
					|| nx >= N || nx < 0 
					|| (visited[ny] & (1 << nx)) > 0) continue;
			
			if (map[ny][nx] < map[y][x]) {
				dfs(ny, nx, curLen + 1, k_used);
			} else if ((map[ny][nx] - K) < map[y][x] && !k_used) {
				int tmp = map[ny][nx];
				map[ny][nx] = map[y][x] - 1;
				dfs(ny, nx, curLen + 1, true);
				map[ny][nx] = tmp;
			}
		}
		
		visited[y] &= ~(1 << x);
	}
}