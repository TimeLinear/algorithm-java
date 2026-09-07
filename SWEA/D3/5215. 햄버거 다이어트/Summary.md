# 🤖 AI 분석

## 💡 접근 방식

주어진 재료(N개)로 최대 맛(taste)과 제한 칼로리(L) 사이의 최적 조합을 찾기 위해 0-1 배낭 문제 방식으로 동적 계획법 사용.

## ⏱️ 시간 복잡도

O(N * L) — N개의 재료에 대해 각 칼로리 자극(L) 여부를 확인하는 2중 루프 사용.

## 📦 공간 복잡도

O(L) — DP 배열(dp)은 칼로리 제한만큼의 크기를 가지며 추가적인 메모리 사용은 없음.

## 🔧 개선 사항

1) StreamTokenizer 대신 BufferedReader와 String.split()을 사용해 입력 유연성 및 가독성 개선.
2) dp 배열의 크기를 L+1로 설정하여 범위 초과를 방지하고 1차원 인덱스 사용에서 2차원 배열 불필요. 3) 결과 출력을 System.out.println()으로 간결하게 변경하고 StringBuilder 제거.

## 🎯 다음 추천 문제

백준 12865번 - 평범한 배낭 | 0-1 배낭 문제의 유사한 포맷을 여러 번 연습하면서 문제 해결 능력 강화.

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            L = Integer.parseInt(input[1]);

            taste = new int[N];
            kalory = new int[N];

            for (int i = 0; i < N; i++) {
                input = br.readLine().split(" ");
                taste[i] = Integer.parseInt(input[0]);
                kalory[i] = Integer.parseInt(input[1]);
            }

            int[] dp = new int[L + 1];
            for (int i = 0; i < N; i++) {
                for (int j = L; j >= kalory[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - kalory[i]] + taste[i]);
                }
            }

            System.out.println('#' + tc + ' ' + dp[L]);
        }
    }
}
```
