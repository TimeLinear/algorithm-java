# 🤖 AI 분석

## 💡 접근 방식

요세푸스 문제를 해결하기 위한 원형 큐 시뮬레이션. K번째 사람을 제거 후 나머지를 회전하여 재배치.

## ⏱️ 시간 복잡도

O(N*K) — 각 사람을 제거하기 위해 최대 K-1 회전 반복 과정이라 전체적으로 N에 대해 K번 반복하는 방식으로 기하급수적인 성능 저하 가능.

## 📦 공간 복잡도

O(N) — 입력으로 받은 인원 수만큼의 배열을 사용하고 상황에 따라 확장성을 위해 추가 공간을 사용.

## 🔧 개선 사항

1) 원형 큐의 반복과정 대신 LinkedList를 사용해 불필요한 복사를 줄일 수 있음.
2) 아니면 K번째 사람을 직접 계산해 인덱스 조정 방식으로 전환하여 반복문 최소화.

예시 개선된 코드:
List<Integer> circle = new LinkedList<>();
for (int i = 1; i <= N; i++) circle.add(i);
while (!circle.isEmpty()) { K = (K - 1) % circle.size(); System.out.print(circle.remove(K) + " "); }

## 🎯 다음 추천 문제

백준 1158번 - 요세푸스 문제 | 동일한 문제를 동일한 방법으로 풀되 입력 구조를 변환하여 새로운 풀이 접근기를 만든다.

## 🏷️ 태그

implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split();
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        LinkedList<Integer> circle = new LinkedList<>();
        for (int i = 1; i <= N; i++) circle.add(i);

        StringBuilder sb = new StringBuilder();
        while (!circle.isEmpty()) {
            K = (K - 1) % circle.size();
            sb.append(circle.remove(K)).append(" ");
        }
        System.out.print(sb);
    }
}
```
