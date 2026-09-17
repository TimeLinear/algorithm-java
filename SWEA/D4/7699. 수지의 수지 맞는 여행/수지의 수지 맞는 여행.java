import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	static final int MAX_SIZE = 20;
	static final int[] dr = {0, 1, 0, -1};
	static final int[] dc = {1, 0, -1, 0};
	static int R, C;
	// 실제 인덱스 1 ~ MAX_SIZE
	static char[][] map = new char[MAX_SIZE + 1][MAX_SIZE + 1];
	static int fullFlag, maxCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			fullFlag = 0;
			maxCnt = 0;
			
			for(int i = 1; i <= R; i++) {
				String line = br.readLine();
				for(int j = 1; j <= C; j++) {
					map[i][j] = line.charAt(j - 1);
					// 이번 입력의 알파벳 풀 비트 마스크 만들기
					fullFlag |= (1 << (map[i][j] - 'A'));
				}
			}
			
			// 첫 칸(1, 1)의 알파벳 미리 체크
			dfs(1, 1, (1 << map[1][1] - 'A'));
			
			sb.append('#').append(tc).append(' ').append(maxCnt).append('\n');
		}
		System.out.print(sb);
	}

	static void dfs(int r, int c, int flag) {
		if (flag == fullFlag) {
			maxCnt = Integer.bitCount(fullFlag);
			return;
		}
		
		boolean moved = false;
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr > R || nr < 1 || nc > C || nc < 1 || (flag & (1 << (map[nr][nc] - 'A'))) != 0) continue; 
			
			dfs(nr, nc, (flag | (1 << (map[nr][nc] - 'A'))));
			moved = true;
		}
		
		if (!moved) {
			maxCnt = Math.max(maxCnt, Integer.bitCount(flag));
		}
	}
}