# 🤖 AI 분석

## 💡 접근 방식

재귀적 깊이 우선 탐색(DFS)을 통해 모든 조합의 합을 계산하여 목표 높이 B에 대한 최소 차이를 찾음.

## ⏱️ 시간 복잡도

O(2^N) — 각 보조원 선택 여부에 따라 두 가지 경로를 가지므로 지수적 시간 복잡도. N이 20 이하로 제한되어 있으나, 최악의 경우 모든 조합을 생성해야 함.

## 📦 공간 복잡도

O(N) — 재귀 스택 최대 깊이에 의해 N만큼의 공간을 사용함. 변수가 아닌 특정한 추가 구조는 없음.

## 🔧 개선 사항

1) 조기 종료 조건 추가: 현재 total이 minDiff보다 크면 재귀를 중단해 불필요한 호출 방지.
2) 중복 합계 계산을 피하기 위해 전체 합을 미리 계산 후 B에 가까운 총합을 찾도록 변경.
3) 표준 출력 개선: 배열을 이용해 각 테스트 케이스의 결과를 모은 후 한 번에 출력.

## 🎯 다음 추천 문제

SWEA 1487번 - 엘리베이터 | 조합 문제의 변형으로, 제한 조건의 조합 및 빠른 탐색 학습.

## 🏷️ 태그

recursive, backtracking, implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    static int N, B;
    static int[] assistants = new int[20];
    static int minDiff;

    private static void dfs(int n, int total) {
        if (total >= B) {
            minDiff = Math.min(minDiff, total - B);
            return;
        }
        if (n == N || total + assistants[n] > minDiff) return;
        dfs(n + 1, total + assistants[n]);
        dfs(n + 1, total);
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            minDiff = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                assistants[i] = Integer.parseInt(st.nextToken());
            }
            dfs(0, 0);
            result.append('#').append(tc).append(' ').append(minDiff).append('\n');
        }
        System.out.print(result);
    }
}
```
