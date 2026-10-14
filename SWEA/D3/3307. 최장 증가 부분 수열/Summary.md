# 🤖 AI 분석

## 💡 접근 방식

tails 배열 c[k]에 '길이 k인 증가 부분 수열의 마지막 값 중 최솟값'을 유지하는 방식(patience sorting)으로 푼다. 각 원소가 c[size]보다 크면 끝에 붙여 길이를 늘리고, 아니면 lower bound 이분 탐색으로 처음으로 target 이상인 위치를 찾아 교체한다. 최종 size가 LIS의 길이다.

## ⏱️ 시간 복잡도

O(N log N) - 원소마다 이분 탐색을 최대 1회 수행한다.

## 📦 공간 복잡도

O(N) - nums와 c 배열이 각각 N+1 크기다.

## 🔧 개선 사항

알고리즘은 정확하고 엄격한 증가(strictly increasing) 조건도 lower bound로 올바르게 처리한다. 다만 c[0]=0을 센티넬로 쓰기 때문에 모든 값이 1 이상이라는 전제에 의존한다. 0 이하의 값이 들어오면 lowerBound가 0을 반환해 센티넬을 덮어쓰고 결과가 틀려진다. 이 문제의 제약에서는 통과하지만, 센티넬 없이 0-indexed tails 배열로 pos == size이면 size++ 하는 방식으로 바꾸면 값 범위와 무관하게 안전하고 if/else 분기도 사라진다. nums 배열은 저장할 필요 없이 읽으면서 바로 처리해도 된다. static 필드도 지역 변수로 바꾸는 편이 깔끔하다.

## 🎯 다음 추천 문제

번호는 확실하지 않아 키워드로 제시한다. SWEA에서 '최장 증가 부분 수열(LIS)'을 응용하는 문제나 '가장 긴 증가하는 부분 수열', '전깃줄'류 문제(LIS 변형, 이분 탐색)를 검색해 풀어보면 좋다.

## 🏷️ 태그

binary-search, dynamic-programming, lis

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Solution {
	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuilder sb = new StringBuilder();

		in.nextToken();
		int T = (int) in.nval;

		for (int tc = 1; tc <= T; tc++) {
			in.nextToken();
			int n = (int) in.nval;
			int[] tails = new int[n];
			int size = 0;

			for (int i = 0; i < n; i++) {
				in.nextToken();
				int x = (int) in.nval;
				int pos = lowerBound(tails, size, x);
				tails[pos] = x;
				if (pos == size) {
					size++;
				}
			}
			sb.append('#').append(tc).append(' ').append(size).append('\n');
		}
		System.out.print(sb);
	}

	// tails[0..len) 에서 target 이상인 첫 인덱스를 반환한다. 없으면 len.
	static int lowerBound(int[] a, int len, int target) {
		int lo = 0, hi = len;
		while (lo < hi) {
			int mid = (lo + hi) >>> 1;
			if (a[mid] >= target) {
				hi = mid;
			} else {
				lo = mid + 1;
			}
		}
		return lo;
	}
}

```
