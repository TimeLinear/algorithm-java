# 🤖 AI 분석

## 💡 접근 방식

비트마스크 백트래킹으로 푼 코드다. 행 단위로 퀸을 하나씩 놓고, 열(col), 왼쪽 대각선(main), 오른쪽 대각선(sub)의 점유 상태를 정수 비트로 관리한다. 한 행에서는 FULL & ~(col|main|sub)로 놓을 수 있는 칸만 구한 뒤, 최하위 비트를 하나씩 꺼내 다음 행으로 재귀한다. 대각선 마스크는 행이 내려갈 때마다 각각 왼쪽, 오른쪽으로 시프트하고, row가 N에 도달하면 해의 개수를 센다.

## ⏱️ 시간 복잡도

O(N!) - 행마다 후보 칸이 줄어드는 백트래킹이며 실제로는 가지치기로 이보다 훨씬 적게 탐색한다. 이 문제는 N이 작아 충분히 빠르다.

## 📦 공간 복잡도

O(N) - 재귀 깊이가 N이고 상태는 정수 비트마스크로만 들고 있다.

## 🔧 개선 사항

로직은 올바르고 이미 충분히 좋은 코드다. main 마스크를 왼쪽으로 시프트하면 FULL 밖의 비트가 생기지만, available 계산에서 FULL로 걸러내므로 결과에는 문제가 없다. 다만 정리하면 좋은 점이 있다. 첫째, 매개변수 이름 main이 main 메서드와 헷갈리므로 ld, rd 같은 이름이 낫다. 둘째, 시프트할 때 바로 FULL로 마스킹해 두면 의도가 더 분명하다. 셋째, 테스트 케이스마다 같은 N이 반복될 수 있으니 결과를 N별로 캐싱하면 중복 계산이 사라진다. 넷째, int T 줄 끝의 세미콜론이 두 개(;;)인 것도 정리하면 좋다.

## 🎯 다음 추천 문제

번호는 확실하지 않아 적지 않는다. SWEA에서 '백트래킹' 또는 '부분집합 / 순열 완전탐색' 유형인 D3~D4 문제(검색 키워드: SWEA 백트래킹 D4, 부분 수열의 합, 조합)를 이어서 풀어 보는 것을 추천한다.

## 🏷️ 태그

backtracking, bitmask, recursion

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	static int N, FULL, cnt;
	static int[] memo = new int[20];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			if (memo[N] == 0) {
				FULL = (1 << N) - 1;
				cnt = 0;
				place(0, 0, 0, 0);
				memo[N] = cnt;
			}
			sb.append('#').append(tc).append(' ').append(memo[N]).append('\n');
		}
		System.out.print(sb);
	}

	static void place(int row, int cols, int ld, int rd) {
		if (row == N) {
			cnt++;
			return;
		}

		int available = FULL & ~(cols | ld | rd);

		while (available != 0) {
			int bit = available & -available;
			available ^= bit;
			place(row + 1, cols | bit, ((ld | bit) << 1) & FULL, (rd | bit) >> 1);
		}
	}
}

```
