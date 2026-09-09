# 🤖 AI 분석

## 💡 접근 방식

비트 마스크와 DFS를 이용하여 수제 버거 제작 가능성을 탐색하고 조건을 충족하는 경우의 수를 계산.

## ⏱️ 시간 복잡도

O(N * 2^N) — N개의 재료에 대해 각 상태를 재귀적으로 확인하므로 최악의 경우 각 상태에 대해 DFS가 발생. 이론적으로 2^N에서 DFS가 깊어지며 N이 증가함에 따라 시간 증가.

## 📦 공간 복잡도

O(2^N) — 비트마스크 배열인 visited를 사용하여 상태 저장, 이 배열의 크기는 1 << N이므로 최대 2^N의 공간 요구.

## 🔧 개선 사항

1) DFS를 사용하는 대신 비트마스크를 효과적으로 활용하여 경우의 수를 기록하고 정리. 2) 조건문을 단순화 및 중복 호출을 줄여 성능 개선을 목표로 하는데, 이론적으로 트리 구조를 간결하게 만들 수 있음. 3) 메모리 사용량을 줄이기 위해 visited를 줄이고 필요한 값을 임시 계산하여 저장.

## 🎯 다음 추천 문제

SWEA 16929번 - 두 지역구간 | DFS와 비트마스크를 활용한 경로 탐색 및 조건 확인 문제로, 기초로 적합.

## 🏷️ 태그

backtracking, bit-manipulation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    static int N, M, ans;
    static int[] mask;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String[] inputs = br.readLine().split();
            N = Integer.parseInt(inputs[0]);
            M = Integer.parseInt(inputs[1]);

            ans = 0;
            mask = new int[N + 1];

            for (int i = 0; i < M; i++) {
                inputs = br.readLine().split();
                int idx = Integer.parseInt(inputs[0]);
                int val = Integer.parseInt(inputs[1]);
                mask[idx] |= (1 << (val - 1));
                mask[val] |= (1 << (idx - 1));
            }

            dfs(0, 0);
            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int idx, int flag) {
        if (idx == N) {
            ans++;
            return;
        }
        dfs(idx + 1, flag);
        if ((mask[idx + 1] & flag) == 0) {
            dfs(idx + 1, flag | (1 << idx));
        }
    }
}
```
