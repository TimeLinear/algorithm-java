# 🤖 AI 분석

## 💡 접근 방식

주어진 숫자를 분할하여 가능한 모든 곱의 최대 횟수를 재귀적으로 계산하는 DFS 기반 접근.

## ⏱️ 시간 복잡도

O(2^D) — D는 자릿수. 각 자릿수별로 분할 여부를 결정하며 호출 횟수는 지수적으로 증가. (최대 5자리 수로 인한 한계)

## 📦 공간 복잡도

O(D) — DFS의 재귀 깊이와 dp 배열이 사용되며 자릿수를 기반으로 메모리 사용량 결정.

## 🔧 개선 사항

1) dp 배열 초기화 추가: dp 배열을 사용하기 전에 초기화 필요.
2) 자릿수 계산을 반복문이 아니라 Character.toString()으로 변환하여 간단히 계산.
3) 제곱수를 계산할 때 pow() 대신 곱하기 사용하면 불필요한 연산 줄임.
4) 결과를 collect & display하기 위한 StringBuilder는 메인 루프에서 초기화 후 사용.

## 🎯 다음 추천 문제

SWEA 7205번 - 숫자 게임 1 | 구조가 유사하므로 복습 겸 진행하기 좋음.

## 🏷️ 태그

recursion, math, implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Solution {
    static int[] dp = new int[100000];

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        StringBuilder sb = new StringBuilder();

        in.nextToken();
        int T = (int) in.nval;

        for (int tc = 1; tc <= T; tc++) {
            in.nextToken();
            int n = (int) in.nval;
            dp = new int[100000]; // 각 테스트 케이스마다 초기화
            sb.append('#').append(tc).append(' ').append(dfs(n)).append('\n');
        }
        System.out.println(sb);
    }

    static int dfs(int n) {
        if (n < 10) return 0;
        if (dp[n] != 0) return dp[n];

        int digitNum = String.valueOf(n).length(); // 자릿수 간단히 계산
        int maxCnt = 0;
        for (int i = 1; i < (1 << (digitNum - 1)); i++) {
            int prod = 1;
            int rest = n;
            int divNum = 1;
            for (int j = 0; j < digitNum - 1; j++) {
                divNum *= 10;
                if ((i & (1 << j)) != 0) {
                    prod *= rest % divNum;
                    rest /= divNum;
                    divNum = 1;
                }
            }
            maxCnt = Math.max(maxCnt, dfs(prod * rest) + 1);
        }
        return dp[n] = maxCnt;
    }
}
```
