# 🤖 AI 분석

## 💡 접근 방식

Dynamic Programming 방식을 사용해 주어진 칼로리 제한 L 내에서 최대 맛 점수 계산.

## ⏱️ 시간 복잡도

O(N * L) — N개의 햄버거와 제한 L을 순회하며 dp 배열을 업데이트하므로, 최악의 경우 O(N * L) 시간 소요.

## 📦 공간 복잡도

O(L) — dp 배열만 사용하여 칼로리 제한 L에 따라 크기가 결정됨. 추가 배열 사용 안 함.

## 🔧 개선 사항

1) StreamTokenizer는 코드 가독성을 떨어뜨리므로 BufferedReader와 String.split()을 사용하여 코드 간결화.
2) dp 배열을 사용하기 전에 전부 0으로 초기화하는 불필요한 점검을 피하기 위해 생성 시 곧바로 초기화.
3) StringBuilder 생성자 호출 후 append 방식으로 결과를 쌓는 방법으로 향상된 출력 성능.

## 🎯 다음 추천 문제

백준 12865번 - 평범한 배낭 | 동일한 배낭 문제로 변동 폭 있는 유형 연습.

## 🏷️ 태그

dynamic-programming, implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    static int N, L;
    static int[] taste;
    static int[] kalory;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            L = Integer.parseInt(input[1]);

            dp = new int[L + 1];
            taste = new int[N];
            kalory = new int[N];

            for (int i = 0; i < N; i++) {
                input = br.readLine().split(" ");
                taste[i] = Integer.parseInt(input[0]);
                kalory[i] = Integer.parseInt(input[1]);
            }

            for (int i = 0; i < N; i++) {
                for (int j = L; j >= kalory[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - kalory[i]] + taste[i]);
                }
            }

            sb.append('#').append(tc).append(' ').append(dp[L]).append('\n');
        }
        System.out.print(sb);
    }
}
```
