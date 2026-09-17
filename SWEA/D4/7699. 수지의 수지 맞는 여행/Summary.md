# 🤖 AI 분석

## 💡 접근 방식

DFS를 사용하여 맵을 탐색하며 방문한 알파벳을 비트마스크로 체크, 최대 방문 알파벳 수를 기록.

## ⏱️ 시간 복잡도

O(R * C * (4^K)) — (R*C)는 탐색 영역 크기, K는 방문 가능 알파벳 수. 알파벳 수가 K개일 때 각 칸에서 이웃 4 칸에 대해 DFS 호출할 수 있어 최악의 경우 지수 시간 복잡도 도출.

## 📦 공간 복잡도

O(1) — 비트마스크는 상수 크기 사용, 재귀 호출 스택은 최대 R*C 깊이이지만 비트마스크 외에 별도의 배열은 사용하지 않으므로 상수적.

## 🔧 개선 사항

1) 방문했던 알파벳 기록 방법: 비트마스크에 의한 판독 시 항상 범위를 체크하여 성능 저하를 피할 수 있음.
2) 매개변수에서 새로 업데이트한 flag를 직접 전달하는 것이 아닌, 지역변수로 셋팅하여 중복연산 방지.
3) 이동 방향 확인 시 배열 범위 체크를 제외하고 다양한 조건을 활용할 수 있도록 수정. 예시
   - if (isValid(nr, nc, flag)) { dfs(nr, nc, newFlag) }
4) 한 전역 변수 대신 더 명확한 지역변수나 클래스를 사용하면 가독성 향상.

## 🎯 다음 추천 문제

SWEA 1767번 - 프로세서 연결하기 | DFS를 활용하여 조건부 탐색 및 결과 최적화를 배우기 좋은 문제.

## 🏷️ 태그

dfs, backtracking, implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static final int MAX_SIZE = 20;
	static final int[] dr = {0, 1, 0, -1};
	static final int[] dc = {1, 0, -1, 0};
	static int R, C;
	static char[][] map = new char[MAX_SIZE + 1][MAX_SIZE + 1];
	static int maxCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			maxCnt = 0;

			for (int i = 1; i <= R; i++) {
				String line = br.readLine();
				for (int j = 1; j <= C; j++) {
					map[i][j] = line.charAt(j - 1);
				}
			}

			dfs(1, 1, (1 << (map[1][1] - 'A')));
			sb.append('#').append(tc).append(' ').append(maxCnt).append('\n');
		}
		System.out.print(sb);
	}

	static void dfs(int r, int c, int flag) {
		if (Integer.bitCount(flag) == 26) {
			maxCnt = Math.max(maxCnt, 26);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isValid(nr, nc, flag)) {
				dfs(nr, nc, (flag | (1 << (map[nr][nc] - 'A'))));
			}
		}
	}

	static boolean isValid(int r, int c, int flag) {
		return r >= 1 && r <= R && c >= 1 && c <= C && (flag & (1 << (map[r][c] - 'A'))) == 0;
	}
}
```
