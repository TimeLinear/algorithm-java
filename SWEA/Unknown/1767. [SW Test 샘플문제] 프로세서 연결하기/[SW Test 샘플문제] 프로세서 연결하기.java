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
					if (map[i][j] == 1 && (i != 0 && i != N - 1 && j != 0 && j != N - 1)) {
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
		int remain = core.size() - cIdx;
		// 코어 수로 가지치기
		if (cCnt + remain < coreSum) return;
		
		if(cIdx == core.size()) {
			if (cCnt > coreSum || (cCnt == coreSum && len < lenSum)) {
				coreSum = cCnt;
				lenSum = len;
			}
			return;
		}
		
		dfs(cIdx + 1, cCnt, len); // 코어 스킵
		
		int y = core.get(cIdx)[0];
        int x = core.get(cIdx)[1];
		
		for(int d = 0; d < 4; d++) {
			int dLen = tryWiring(y, x, d);
			if (dLen == -1) continue;
			
			mark(y, x, d, dLen, 2);
			dfs(cIdx + 1, cCnt + 1, len + dLen);
			mark(y, x, d, dLen, 0);
		}
	}
	
	static int tryWiring(int y, int x, int d) {
		int ny = y;
		int nx = x;
		int len = 0;
		
		// 이미 벽면에 붙은 core는 걸러낸 뒤
		while (true) {
            ny += DY[d];
            nx += DX[d];
            if (map[ny][nx] != 0) return -1;
            len++;
            if (ny == 0 || ny == N - 1 || nx == 0 || nx == N - 1) return len;
        }
	}
	
	static void mark(int y, int x, int d, int len, int val) {
		int ny = y, nx = x;
        for (int i = 0; i < len; i++) {
            ny += DY[d];
            nx += DX[d];
            map[ny][nx] = val;
        }
	}
}