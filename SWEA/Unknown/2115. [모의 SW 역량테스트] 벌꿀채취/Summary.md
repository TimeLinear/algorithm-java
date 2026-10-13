# 🤖 AI 분석

## 💡 접근 방식

각 행에서 길이 M인 가로 구간마다 DFS로 부분집합을 탐색해, 합이 C 이하인 조건에서 제곱합의 최댓값을 profit[i][j]에 미리 계산해 둔다. 이후 두 일꾼의 시작 위치를 완전 탐색한다. 같은 행이면 두 번째 시작 열을 j+m 이상으로 제한해 구간이 겹치지 않게 하고, profit 합의 최댓값을 구한다.

## ⏱️ 시간 복잡도

O(N^2 * 2^M + N^4) - 구간 N*(N-M+1)개마다 부분집합 2^M을 탐색하고, 두 구간 쌍을 전수 비교한다.

## 📦 공간 복잡도

O(N^2 + M) - map과 profit 배열, 그리고 깊이 M의 재귀 스택을 사용한다.

## 🔧 개선 사항

로직과 경계 조건은 모두 올바르다. 같은 행 중복 제거(j+m)와 서로 다른 행 처리도 정확하다. 개선할 부분은 사소하다. 1) 주석 'dfs로도 구현 가능'은 실제로 dfs를 쓰고 있어 부정확하니 수정한다. 2) 두 구간 쌍 탐색은 행별 최댓값을 미리 구해 두면 O(N^4)에서 줄일 수 있지만 N≤10이라 필요하지 않다. 3) 변수명 profit을 구간별 최대 이익임이 드러나게 바꾸고, dfs에 map 접근용 파라미터를 줄이면 가독성이 좋아진다. 4) StreamTokenizer를 static 필드로 두는 것은 문제없다.

## 🎯 다음 추천 문제

SWEA에서 이어 풀 만한 문제로, 부분집합/완전탐색 후 구간 조합을 고르는 유형인 '[모의 SW 역량테스트] 요리사'(검색 키워드: SWEA 요리사 모의 SW 역량테스트)를 추천한다.

## 🏷️ 태그

brute-force, dfs, implementation, backtracking

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Solution {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int n, m, c;
	static int[][] map, best;

	public static void main(String[] args) throws IOException {
		int T = nextInt();
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			n = nextInt();
			m = nextInt();
			c = nextInt();

			map = new int[n][n];
			best = new int[n][n - m + 1];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = nextInt();
				}
			}

			// 각 구간(i, j~j+m-1)에서 얻을 수 있는 최대 이익을 미리 계산
			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= n - m; j++) {
					best[i][j] = dfs(i, j, 0, 0);
				}
			}

			// 두 일꾼의 구간 선택 (겹치지 않게, 중복 조합 제거)
			int maxProfit = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= n - m; j++) {
					for (int k = i; k < n; k++) {
						int start = (k == i) ? j + m : 0;
						for (int l = start; l <= n - m; l++) {
							maxProfit = Math.max(maxProfit, best[i][j] + best[k][l]);
						}
					}
				}
			}

			sb.append('#').append(tc).append(' ').append(maxProfit).append('\n');
		}
		System.out.print(sb);
	}

	// 부분집합 DFS: 합이 c 이하인 선택 중 제곱합의 최댓값
	static int dfs(int row, int col, int idx, int sum) {
		if (idx == m) return 0;

		int val = map[row][col + idx];

		// 선택하지 않는 경우
		int result = dfs(row, col, idx + 1, sum);

		// 선택하는 경우 (용량을 넘지 않을 때만)
		if (sum + val <= c) {
			result = Math.max(result, val * val + dfs(row, col, idx + 1, sum + val));
		}
		return result;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}

```
