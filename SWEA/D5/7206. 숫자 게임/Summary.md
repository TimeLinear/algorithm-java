# 🤖 AI 분석

## 💡 접근 방식

주어진 숫자를 재귀적으로 분할하여 곱한 값의 최대 분할 수를 계산하는 DFS를 사용한 접근 방식.

## ⏱️ 시간 복잡도

O(2^(d-1)) — d는 분할 가능한 자리 수; 모든 조합을 시도하므로 최악의 경우 지수 시간복잡도 발생. 분할 수가 5자리 이내로 제한되므로 실질적으로는 다루기 수월함.

## 📦 공간 복잡도

O(d) — 호출 스택에 따른 공간 사용, 각 분할 재귀 호출에서 추가적인 변수에 대한 공간이 필요하므로 최대 d (자리 수) 만큼 필요.

## 🔧 개선 사항

1) dfs()의 호출 수를 줄이기 위해 이미 계산한 값 저장(use memoization). 2) 곱셈이 아닌 덧셈으로 구분을 했을 때의 예외 처리로 엣지 케이스 검토. 3) 코드 가독성 향상을 위해 변수를 명확하고 일관되게 사용.

## 🎯 다음 추천 문제

백준 14501번 - 퇴사 | 조합과 최적화 문제로 동적인 실전 연습.

## 🏷️ 태그

recursion, math

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Solution {
    static int maxCnt;
    static HashMap<Integer, Integer> memo = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for(int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            maxCnt = 0;
            dfs(n, 0);
            sb.append('#').append(tc).append(' ').append(maxCnt).append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int n, int cnt) {
        if (n < 10) {
            maxCnt = Math.max(maxCnt, cnt);
            return;
        }
            
        if (memo.containsKey(n) && memo.get(n) >= cnt) return;
        memo.put(n, cnt);

        int digitNum = String.valueOf(n).length();
        for(int i = 1; i < (1 << (digitNum - 1)); i++) {
            int prod = 1;
            int rest = n;
            int divNum = 1;
            for(int j = 0; j < digitNum - 1; j++) {
                divNum *= 10;
                if ((i & (1 << j)) != 0) {
                    prod *= rest % divNum;
                    rest /= divNum;
                    divNum = 1;
                }
            }
            dfs(prod * rest, cnt + 1);
        }
    }
}
```
