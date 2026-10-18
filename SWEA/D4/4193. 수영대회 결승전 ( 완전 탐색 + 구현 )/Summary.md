# 🤖 AI 분석

## 💡 접근 방식

BFS로 수영 대회 경로를 탐색하며 소용돌이에 따른 대기 처리 구현. 막힘 없이 최단 거리 도출.

## ⏱️ 시간 복잡도

O(N^2) — 모든 가능한 위치를 탐색하며, 각 위치에 대해 최대 4방향 계산. 최악의 경우 모든 위치가 탐색됨.

## 📦 공간 복잡도

O(N^2) — 맵과 방문 배열은 n*n 크기를 가짐. 추가 큐 사용으로 O(N) 추가.

## 🔧 개선 사항

1) 대기 처리 로직을 개선. wait 사용 대신 큐에 위치 추가 시 직전 깊이에서 처리하는 방법으로 개선 가능.
2) 누적 깊이 정보 분리를 통해 코드 가독성 향상. 
3) 반복적인 범위 체크를 메서드로 분할하여 가독성 향상 및 중복 줄이기.

## 🎯 다음 추천 문제

SW Expert Academy 4194번 - 수영대회 결승전 (다양한 상황에서 대처하는 BFS 조건 연습) | 유사한 BFS 대응 문제로 이해 확대.

## 🏷️ 태그

bfs, implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
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
			start = new int[]{nextInt(), nextInt()};
			end = new int[]{nextInt(), nextInt()};
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
				if (isOutOfBounds(nr, nc) || visited[nr][nc] || map[nr][nc] == 1) continue;
				if (map[nr][nc] == 2 && (cur.depth + 1) % 3 != 0) {
					wait = true;
					continue;
				}
				if (nr == end[0] && nc == end[1]) {
					ans = cur.depth + 1;
					return;
				}
				q.offer(new Position(nr, nc, cur.depth + 1));
				visited[nr][nc] = true;
			}
			if (wait) q.offer(new Position(cur.r, cur.c, cur.depth + 1));
		}
	}

	static boolean isOutOfBounds(int r, int c) {
		return r >= n || r < 0 || c >= n || c < 0;
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
```
