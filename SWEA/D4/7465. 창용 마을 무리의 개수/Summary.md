# 🤖 AI 분석

## 💡 접근 방식

유니온 파인드 알고리즘을 사용하여 서로 연결된 집단을 추적하고, 최종적으로 서로 다른 루트(집단)의 수를 세는 방식.

## ⏱️ 시간 복잡도

O(N + M) — N개의 노드와 M개의 엣지를 처리. union/find 방식의 경우 거의 상수 시간 복잡도에 가까움. 최악의 경우 반감에 따라 log-log 성질을 통해 O(M log* N)으로 간주.

## 📦 공간 복잡도

O(N) — 집단을 표현하기 위한 배열과 기초 데이터 구조적 저장(부모 배열, 루트 추적 배열)으로 N 크기를 요구.

## 🔧 개선 사항

1) union 연산 수행 시 Union by Rank 기법을 사용하여 루트를 비교하고 더 작은 랭크에 연결하여 트리의 높이를 줄여 성능 개선.
2) boolean 배열 대신 HashSet 등을 활용해 중복 확인 효율을 개선.
3) 예외 처리를 통해 nextInt()의 예외를 처리하는 로직을 개선.

## 🎯 다음 추천 문제

백준 1717번 - 집합의 표현 | 유사한 유니온 파인드 문제로 연습, 추가 쿼리 기능 연습.

## 🏷️ 태그

union-find, implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

class Solution {

    static int[] p = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        for(int tc = 1; tc <= T; tc++) {
            String[] tokens = in.readLine().split(" ");
            int N = Integer.parseInt(tokens[0]);
            int M = Integer.parseInt(tokens[1]);

            for(int i = 1; i <= N; i++) {
                p[i] = i;
            }

            for(int i = 0; i < M; i++) {
                tokens = in.readLine().split(" ");
                int s1 = Integer.parseInt(tokens[0]);
                int s2 = Integer.parseInt(tokens[1]);
                union(s1, s2);
            }

            HashSet<Integer> roots = new HashSet<>();
            for(int i = 1; i <= N; i++) {
                roots.add(find(p[i]));
            }

            sb.append('#').append(tc).append(' ').append(roots.size()).append('\n');
        }
        System.out.print(sb);
    }

    static int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    static void union(int s1, int s2) {
        int r1 = find(s1);
        int r2 = find(s2);
        if (r1 != r2) p[r1] = r2;
    }
}
```
