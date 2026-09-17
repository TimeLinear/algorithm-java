# 🤖 AI 분석

## 💡 접근 방식

2차원 배열을 사용하여 지뢰 찾기 게임 구현. BFS로 빈 칸 그룹을 탐색하며, 주위 지뢰 수를 계산하여 최종 카운트 반환.

## ⏱️ 시간 복잡도

O(N^2) — N×N 크기의 격자를 순회하며 지뢰 위치와 주변 지뢰 수를 판별, 전체 반복과 BFS 탐색 포함.

## 📦 공간 복잡도

O(N^2) — 폭탄 배열, 방문 배열, 그리고 빈 칸 맵을 저장하기 위해 각 N×N 크기 메모리 사용.

## 🔧 개선 사항

1) 상수 공간과 반복 계산을 줄이기 위해 bombMap의 상태를 검사하는 과정에서 직접 빈 칸 카운트를 하고, 따로 zeroMap 배열을 사용하지 않도록 통합.
2) BFS에서 deque 사용으로 효율성을 높일 수 있지만 ArrayDeque도 적절. 
3) countSurrounds() 내 중복 배열 검사를 줄여 효율성 향상.

## 🎯 다음 추천 문제

백준 4485번 - 녹색 옷 입은 애가 젤다지? | BFS와 DFS를 활용해 경로 최단 구하기 문제로 단계적 복습.

## 🏷️ 태그

bfs, graph

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
	static int N, totalCnt;
	static boolean[][] bombMap = new boolean[300][300];
	static boolean[][] visited = new boolean[300][300];
	static final int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};
	static final int[] dc = {1, 1, 0, -1, -1, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			totalCnt = 0;
			
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				for(int j = 0; j < N; j++) {
					bombMap[i][j] = line.charAt(j) == '*';
					if (!bombMap[i][j]) totalCnt++;
				}
			}
			
			int cCnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if (bombMap[i][j] || visited[i][j]) continue;
					if (countSurrounds(i, j) == 0) {
						cCnt++;
						bfs(i, j);
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(cCnt + totalCnt).append('\n');
		}
		System.out.print(sb);
	}

	static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {r, c});
		visited[r][c] = true;
		totalCnt--;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d = 0; d < 8; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || bombMap[nr][nc]) continue;
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
			if (nr >= 0 && nr < N && nc >= 0 && nc < N && bombMap[nr][nc]) {
				bCnt++;
			}
		}
		return bCnt;
	}
}
```
