import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

class Solution {
	static int N, totalCnt;
	static boolean[][] bombMap = new boolean[300][300];
	static boolean[][] zeroMap = new boolean[300][300];
	static boolean[][] visited = new boolean[300][300];
	static int[] q = new int[300 * 300];
	static final int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};
	static final int[] dc = {1, 1, 0, -1, -1, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1 << 16];
		int len;
		while ((len = System.in.read(buf)) > 0) {
			bos.write(buf, 0, len);
		}
		String[] input = new String(bos.toByteArray()).split("\n");
		StringBuilder sb = new StringBuilder();
		int strIdx = 0;
		
		int T = Integer.parseInt(input[strIdx++].trim());
		
		for(int tc = 1; tc<= T; tc++) {
			N = Integer.parseInt(input[strIdx++].trim());
			totalCnt = 0;
			
			for(int i = 0; i < N; i++) {
				Arrays.fill(bombMap[i], 0, N, false);
				Arrays.fill(zeroMap[i], 0, N, false);
				Arrays.fill(visited[i], 0, N, false);
			}
			
			for(int i = 0; i < N; i++) {
				String line = input[strIdx++];
				for(int j = 0; j < N; j++) {
					// true면 폭탄, false면 빈 칸
					bombMap[i][j] = line.charAt(j) == '*';
					if (!bombMap[i][j]) {
						totalCnt++;
					}
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					zeroMap[i][j] = !bombMap[i][j] && !hasBomb(i, j);
				}
			}
			
			// 폭탄 0인 칸 리스트 순회하며 클릭(bfs 함수) 처리
			int cCnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if (!zeroMap[i][j] || visited[i][j]) continue;
					cCnt++;
					bfs(i, j);
				}
			}

			// 0인 칸만 다 채우면 나머진 일일히 눌러줘야하므로 즉시 남은 totalCnt 더하기
			sb.append('#').append(tc).append(' ').append(cCnt + totalCnt).append('\n');
		}
		System.out.print(sb);
	}

	static void bfs(int r, int c) {
		int head = 0, tail = 0;
		
		visited[r][c] = true;
		totalCnt--;
		q[tail++] = r * N + c;
		
		while(head < tail) {
			int cur = q[head++];
			int cr = cur / N;
			int cc = cur % N;
			
			for(int d = 0; d < 8; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				if (nr >= N || nr < 0 || nc >= N || nc < 0 || visited[nr][nc] || bombMap[nr][nc]) continue;
				
				visited[nr][nc] = true;
				totalCnt--;
				if (zeroMap[nr][nc]) q[tail++] = nr * N + nc;
			}
		}
	}
	
	static boolean hasBomb(int r, int c) {
		for(int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
 
			if (nr >= N || nr < 0 || nc >= N || nc < 0) continue;
 
			if (bombMap[nr][nc]) return true;
		}
		return false;
	}
}
