# 🤖 AI 분석

## 💡 접근 방식

patience sorting 방식의 LIS 알고리즘입니다. 배열 c[0..size-1]에 길이별 가장 작은 꼬리 값을 유지하고, 새 값이 마지막 꼬리보다 크면 뒤에 추가해 길이를 늘립니다. 그렇지 않으면 이분 탐색(lower bound)으로 처음으로 target 이상인 위치를 찾아 그 값을 교체합니다. 최종 size가 LIS 길이입니다.

## ⏱️ 시간 복잡도

O(N log N) - 원소마다 이분 탐색을 최대 1회 수행한다.

## 📦 공간 복잡도

O(N) - 입력 배열 nums와 꼬리 값 배열 c를 사용한다.

## 🔧 개선 사항

이미 충분히 좋은 코드입니다. 정확성도 확인했습니다. 엄격히 증가하는 수열이라 c[size-1] < nums[i]일 때만 추가하고, 같거나 작으면 lowerBound로 교체하는 처리가 맞습니다. lowerBound는 탐색 범위를 [0, size-1]로 두며, 이 구간에 target 이상인 값이 반드시 존재하므로(마지막 꼬리 >= target) end를 반환해도 안전합니다. 사소한 정리 사항으로, 입력 크기가 N=1이어도 c[0] 초기화가 정상 동작합니다. n, nums, c를 static 필드 대신 지역 변수로 두면 가독성이 좋아지고, lowerBound에 배열과 size를 인자로 넘기면 의존성이 명확해집니다. 또한 else 분기를 제거하고 Arrays.binarySearch나 단일 lowerBound(0, size, x)로 통일하면 코드가 더 단순해집니다. 주석은 '교체 대상 인덱스를 반환'처럼 명확히 다듬으면 좋습니다.

## 🎯 다음 추천 문제

백준 12015번 '가장 긴 증가하는 부분 수열 2'처럼 N이 매우 큰 LIS 문제를 풀어보거나, 같은 플랫폼에서는 'swea 최장 증가 부분 수열 경로 복원' 유형을 검색해 보세요.

## 🏷️ 태그

binary-search, dynamic-programming, lis

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Solution {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int T = nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int n = nextInt();
			int[] tails = new int[n];
			int size = 0;

			for (int i = 0; i < n; i++) {
				int x = nextInt();
				int idx = lowerBound(tails, size, x);
				tails[idx] = x;
				if (idx == size) {
					size++;
				}
			}
			sb.append('#').append(tc).append(' ').append(size).append('\n');
		}
		System.out.print(sb);
	}

	// tails[0..size) 중 target 이상인 첫 위치를 반환한다. 없으면 size를 반환한다.
	static int lowerBound(int[] tails, int size, int target) {
		int lo = 0;
		int hi = size;
		while (lo < hi) {
			int mid = (lo + hi) >>> 1;
			if (tails[mid] >= target) {
				hi = mid;
			} else {
				lo = mid + 1;
			}
		}
		return lo;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}

```
