# 🤖 AI 분석

## 💡 접근 방식

BFS를 통해 미로에서 시작점(2)부터 도착점(3)까지의 경로를 탐색하여 존재 여부를 판단.

## ⏱️ 시간 복잡도

O(V + E) — 각 노드(V)와 간선(E)을 최대 한 번씩 탐색. 최악의 경우 100x100 그리드 전체를 탐색할 수 있으므로 O(100^2)로 간주 가능.

## 📦 공간 복잡도

O(V) — BFS를 위해 큐(q)와 맵을 사용. 최대 10000개의 셀을 저장할 수 있다.

## 🔧 개선 사항

1) 큐 크기를 동적으로 조정: ArrayList를 사용하여 메모리 효율 개선.
2) 죽은 노드를 확인할 필요 없이 방문 종료 후 큐 비우기를 좀 더 명확하게 수행.
3) 조건문에서 불필요한 계산을 최소화할 수 있도록 경계 검사를 한 번의 if문으로 통합.

## 🎯 다음 추천 문제

백준 2178번 - 미로 탐색 | 더 큰 그리드에서 BFS를 통해 최단 거리를 찾는 문제로 연습.

## 🏷️ 태그

bfs, graph

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
	static final int SIZE = 100;
	static final int[] DY = {-1, 1, 0, 0};
	static final int[] DX = {0, 0, -1, 1};
	static boolean[][] map = new boolean[SIZE][SIZE];
	static boolean[][] visited = new boolean[SIZE][SIZE];
	static int startY, startX, destY, destX; // y, x

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for(int tc = 1; tc <= 10; tc++) {
			// 테스트 케이스 번호
			sb.append('#').append(br.readLine().trim()).append(' ');
			Queue<int[]> queue = new LinkedList<>();
			result = 0;

			for(int i = 0; i < SIZE; i++) {
				String line = br.readLine();
				for(int j = 0; j < SIZE; j++) {
					char cell = line.charAt(j);
					if (cell == '2') {
						startY = i;
						startX = j;
					} else if (cell == '3') {
						destY = i;
						destX = j;
					}
					map[i][j] = cell != '1';
				}
			}

			queue.offer(new int[]{startY, startX});
			visited[startY][startX] = true;

			while(!queue.isEmpty()) {
				int[] cur = queue.poll();
				int curY = cur[0];
				int curX = cur[1];

				// 도착점 확인
				if(curY == destY && curX == destX) {
					result = 1;
					break;
				}

				for(int d = 0; d < DY.length; d++) {
					int ny = curY + DY[d];
					int nx = curX + DX[d];

					if(ny >= 0 && ny < SIZE && nx >= 0 && nx < SIZE && map[ny][nx] && !visited[ny][nx]) {
						visited[ny][nx] = true;
						queue.offer(new int[]{ny, nx});
					}
				}
			}
			sb.append(result).append(System.lineSeparator());
		}
		System.out.print(sb);
	}
}
```
