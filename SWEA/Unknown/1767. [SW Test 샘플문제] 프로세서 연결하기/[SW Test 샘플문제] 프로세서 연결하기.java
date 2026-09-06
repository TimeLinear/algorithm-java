import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

class Solution
{
	static int N;
	static int[][] map = new int[12][12];
	static ArrayList<int[]> core = new ArrayList<>();
	static final int[] DY = {-1, 1, 0, 0};
	static final int[] DX = {0, 0, -1, 1};
	static int lenSum = 0, coreSum = 0;

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuilder sb = new StringBuilder();
		
		in.nextToken();
		int T = (int) in.nval;
		
		for(int tc = 1; tc <= T; tc++) {
			in.nextToken();
			N = (int) in.nval;
			core.clear();
			lenSum = 0;
			coreSum = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					in.nextToken();
					map[i][j] = (int) in.nval;
					if ((int) in.nval == 1) {
						if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
							continue;
						}
						core.add(new int[] {i, j});
					}
				}
			}
			dfs(0, 0, 0);
			
			sb.append('#').append(tc).append(' ').append(lenSum).append('\n');
		}
		System.out.print(sb);
	}
	
	static void dfs(int cIdx, int cCnt, int len) {
		if(cIdx >= core.size()) {
			if (coreSum < cCnt) {
				coreSum = cCnt;
				lenSum = len;
			} else if (coreSum == cCnt) {
				if (lenSum > len) {
					lenSum = len;
				}
			}
			return;
		}
		
		int[] cur = core.get(cIdx);
	    int y = cur[0];
	    int x = cur[1];
		
		dfs(cIdx + 1, cCnt, len);
		
		for(int d = 0; d < 4; d++) {
			int ny = y + DY[d];
			int nx = x + DX[d];
			
			while (ny < N && ny >= 0 && nx < N && nx >= 0 && map[ny][nx] == 0) {
				if (ny == 0 || ny == N - 1 || nx == 0 || nx == N - 1) {
					int dLen = attach(y, x, d, true);
					dfs(cIdx + 1, cCnt + 1, len + dLen);
					attach(y, x, d, false);
					break;
				}
				ny = ny + DY[d];
				nx = nx + DX[d];
			}
		}
	}
	
	static int attach(int y, int x, int d, boolean connect) {
		int ny = y + DY[d];
		int nx = x + DX[d];
		int dLen = 0;
		while (ny < N && ny >= 0 && nx < N && nx >= 0) {
			if (connect) {
				map[ny][nx] = 2;
			} else {
				map[ny][nx] = 0;
			}
			dLen++;
			ny = ny + DY[d];
			nx = nx + DX[d];
		}
		return dLen;
	}
}