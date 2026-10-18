import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.Queue;

class Solution
{
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	static int n, ans;
	static int[][] map;
	static int[] start, end;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		int T = nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			n = nextInt();
			map = new int[n][n];
			visited = new boolean[n][n];
			ans = -1;
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					map[i][j] = nextInt();
				}
			}
			
			start = new int[2]; end = new int[2];
			
			start[0] = nextInt(); start[1] = nextInt();
			end[0] = nextInt(); end[1] = nextInt();
			
			bfs();
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		System.out.print(sb);
	}
	
	static void bfs() {
		Queue<Position> q = new ArrayDeque<>();
		
		q.add(new Position(start[0], start[1], 0));
		visited[start[0]][start[1]] = true;
		
		while(!q.isEmpty()) {
			Position cur = q.poll();
			boolean wait = false;
			
			for(int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if (nr >= n || nr < 0 || nc >= n || nc < 0) continue;
				// 막히거나 이미 방문
				if (visited[nr][nc] || map[nr][nc] == 1) continue;
				
				// 소용돌이 활성화 시
				if (map[nr][nc] == 2 && (cur.depth + 1) % 3 != 0) {
					wait = true;
					continue;
				}
				
				// 답 찾으면 바로 리턴
				if (nr == end[0] && nc == end[1]) {
					ans = cur.depth + 1;
					return;
				}
				
				// 답 못찾으면 그냥 주변 칸 큐에 넣기
				q.offer(new Position(nr, nc, cur.depth + 1));
				visited[nr][nc] = true;
			}
			
			// 소용돌이 활성화로 막혔을 때 딱 한번 대기
			// 위의 while 문 안에 넣으면 대기가 여러번 들어감
			if (wait) {
				q.offer(new Position(cur.r, cur.c, cur.depth + 1));
			}
		}
	}
	
	static class Position {
		int r, c, depth;
		
		public Position(int r, int c, int depth) {
			this.r = r;
			this.c = c;
			this.depth = depth;
		}
	}
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}