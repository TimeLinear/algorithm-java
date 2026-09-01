# 🤖 AI 분석

## 💡 접근 방식

문자열 카드를 반으로 나누고, 각 반의 카드를 번갈아가며 출력하여 셔플 효과를 구현하는 방식.

## ⏱️ 시간 복잡도

O(N) — N개의 카드를 한 번 순회하여 반으로 나눈 후 번갈아 출력하므로 선형 시간 복잡도.

## 📦 공간 복잡도

O(N) — 카드 배열을 저장하기 위해 N/2 크기의 배열 사용. 전체 N개 카드를 저장하기 위한 공간이다.

## 🔧 개선 사항

1) 카드 배열을 두 개로 나누어 직접 구성하는 방법으로 더 간결하게 처리 가능.
2) StringBuilder 대신 ArrayList 사용 → 이러한 방식은 더 유연하고 동적이며 관리가 쉽다.

예시: 
- ArrayList<String> firstHalf = new ArrayList<>();
- ArrayList<String> secondHalf = new ArrayList<>();

## 🎯 다음 추천 문제

SWEA 3498번 - 반반 | 유사한 배열 및 문자열 조작 문제로 연습을 이어갈 수 있는 좋은 문제.

## 🏷️ 태그

implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int half = (N + 1) / 2;
			List<String> firstHalf = new ArrayList<>();
			List<String> secondHalf = new ArrayList<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < half; i++) {
				firstHalf.add(st.nextToken());
			}
			for (int i = 0; i < N - half; i++) {
				secondHalf.add(st.nextToken());
			}
			
			sb.append('#').append(tc);
			int size = Math.max(firstHalf.size(), secondHalf.size());
			for (int i = 0; i < size; i++) {
				if (i < firstHalf.size()) sb.append(' ').append(firstHalf.get(i));
				if (i < secondHalf.size()) sb.append(' ').append(secondHalf.get(i));
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
```
