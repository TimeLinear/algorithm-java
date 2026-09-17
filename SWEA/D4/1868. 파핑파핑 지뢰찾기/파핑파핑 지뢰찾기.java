import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {
	static int N, totalCnt;
	static boolean[][] bombMap = new boolean[300][300];
	static boolean[][] zeroMap = new boolean[300][300];
	static boolean[][] visited = new boolean[300][300];
	static List<int[]> targets = new ArrayList<>();
	static final int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};
	static final int[] dc = {1, 1, 0, -1, -1, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<= T; tc++) {
			N = Integer.parseInt(br.readLine());
			totalCnt = 0;
			
			for(int i = 0; i < N; i++) {
				Arrays.fill(bombMap[i], 0, N, false);
				Arrays.fill(zeroMap[i], 0, N, false);
				Arrays.fill(visited[i], 0, N, false);
			}
			targets.clear();
			
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				for(int j = 0; j < N; j++) {
					// true면 폭탄, false면 빈 칸
					bombMap[i][j] = line.charAt(j) == '*' ? true : false;
					if (!bombMap[i][j]) {
						totalCnt++;
					}
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if (bombMap[i][j]) continue;
					zeroMap[i][j] = !bombMap[i][j] && countSurrounds(i, j) == 0;
					if (zeroMap[i][j]) targets.add(new int[] {i, j});
				}
			}
			
			int cCnt = 0;
			for(int[] pos : targets) {
				if (visited[pos[0]][pos[1]]) continue;
				cCnt++;
				bfs(pos[0], pos[1]);
			}

			sb.append('#').append(tc).append(' ').append(cCnt + totalCnt).append('\n');
		}
		System.out.print(sb);
	}

	static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		
		visited[r][c] = true;
		totalCnt--;
		q.add(new int[] {r, c});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			if (!zeroMap[cur[0]][cur[1]]) continue;
			
			for(int d = 0; d < 8; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				if (nr >= N || nr < 0 || nc >= N || nc < 0 || visited[nr][nc]) continue;
				
				if (bombMap[nr][nc] || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				totalCnt--;
				q.add(new int[] {nr, nc});
			}
		}
	}
	
	static int countSurrounds(int r, int c) {
		int bCnt = 0;
		for(int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr >= N || nr < 0 || nc >= N || nc < 0) continue;
			
			if (bombMap[nr][nc]) {
				bCnt++;
			}
		}
		return bCnt;
	}
}