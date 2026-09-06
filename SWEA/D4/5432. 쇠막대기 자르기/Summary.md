# 🤖 AI 분석

## 💡 접근 방식

주어진 문자열에서 '('가 레이저를, ')'가 막대의 끝을 나타내며, '('와 ')'의 빈도에 따라 잘린 쇠막대기의 수를 계산합니다.

## ⏱️ 시간 복잡도

O(n) — 각 테스트 케이스에 대해 문자열을 한 번 순회하므로 문자열 길이에 비례.

## 📦 공간 복잡도

O(n) — StringBuilder를 사용하여 결과를 저장하므로 전체 입력 길이에 비례하는 공간이 필요합니다.

## 🔧 개선 사항

1) 불필요한 char 배열 생성 대신 String의 charAt() 메소드 사용하여 배열 사용을 피함: 
if (c.charAt(i) == '(') {  ... }
2) 또한, BufferedReader 대신 Scanner를 사용해 입력을 쉽게 처리할 수 있지만 성능은 BufferedReader가 우세.

## 🎯 다음 추천 문제

SWEA 5431번 - 놀이 공원 | 스트링으로 주어지는 다른 길이의 파트를 구간 분할하여 추가 연습 가능.

## 🏷️ 태그

stack, implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			String c = br.readLine();
			int n = c.length();

			int cnt = 0, ans = 0;
			for (int i = 0; i < n; i++) {
				if (c.charAt(i) == '(') {
					cnt++;
				} else {
					cnt--;
					if (c.charAt(i - 1) == '(') {
						ans += cnt;
					} else {
						ans++;
					}
				}
			}

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		System.out.print(sb);
	}
}
```
