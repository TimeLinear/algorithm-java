# 🤖 AI 분석

## 💡 접근 방식

원형 큐를 사용하여 요세푸스 문제를 해결. K번째 요소를 제거 후, 해당 요소를 새로운 큐로 이동시키며 남은 요소를 순회.

## ⏱️ 시간 복잡도

O(N) — N번의 요소 제거가 발생하지만, 각 K까지의 순회는 최악의 케이스를 고려해도 상수 횟수의 이동만 고려. 효율적인 큐 작동으로 협소한 시간 소요.

## 📦 공간 복잡도

O(N) — 최대 크기 3N인 임시 큐를 사용하여 최악의 경우 메모리 소비.

## 🔧 개선 사항

1) 원형 큐 로직을 단순화하여 인덱스 관리 및 제거를 최적화. 2) 큐의 크기를 N으로 제한해 메모리 사용을 줄이고, 예외 처리를 통해 중복 이동 제거. 3) StringBuilder의 작성을 줄이고 직접 출력하여 성능 개선.

## 🎯 다음 추천 문제

백준 1158번 - 요세푸스 문제 | 동일 문제로 기능 확장을 통한 이해도 및 메커니즘 복습.

## 🏷️ 태그

implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		StringBuilder sb = new StringBuilder();
		while (queue.size() > 0) {
			for (int i = 0; i < K - 1; i++) {
				queue.offer(queue.poll());
			}
			sb.append(queue.poll()).append(' ');
		}
		System.out.print(sb.toString());
	}
}
```
