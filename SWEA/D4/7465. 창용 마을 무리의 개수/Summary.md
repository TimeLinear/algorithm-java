# 🤖 AI 분석

## 💡 접근 방식

유니온-파인드 구조를 사용해 각 연결 성분의 루트 노드를 찾아 마을 무리의 개수를 카운트하는 방식.

## ⏱️ 시간 복잡도

O(M * α(N)) — M개의 union 연산에서 각 find 연산은 효율적으로 이루어지므로 시간 복잡도는 거의 상수로 간주되는 아커만 함수의 역함수 α에 따라 간주할 수 있음.

## 📦 공간 복잡도

O(N) — 배열 p와 roots를 사용하여 최대 N개의 요소를 저장하므로 O(N) 공간 복잡도.

## 🔧 개선 사항

1) union-find 최적화를 추가하여 경로 압축을 적용해 find() 성능 개선
2) 메모리 사용을 줄이고 효율적인 I/O를 위해 BufferedReader 대신 Scanner를 고려해 input을 쉽게 관리할 수 있음
3) try-catch 블록 없이 적절한 예외 처리를 통해 다음Int 메서드를 개선할 수 있음.

## 🎯 다음 추천 문제

백준 1717번 - 집합의 표현 | 동일한 유니온-파인드 구조를 사용하여 집합의 연산을 다룸.

## 🏷️ 태그

union-find, graph

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {
    static int N, M;
    static int[] parent = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String[] nm = br.readLine().split();
            N = Integer.parseInt(nm[0]);
            M = Integer.parseInt(nm[1]);

            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < M; i++) {
                String[] pair = br.readLine().split();
                int s1 = Integer.parseInt(pair[0]);
                int s2 = Integer.parseInt(pair[1]);
                union(s1, s2);
            }

            int rootCount = (int) Arrays.stream(parent)
                                         .filter(x -> x == find(x)).count();
            sb.append('#').append(tc).append(' ').append(rootCount).append('\n');
        }
        System.out.print(sb);
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int s1, int s2) {
        int r1 = find(s1);
        int r2 = find(s2);
        if (r1 != r2) {
            parent[r1] = r2;
        }
    }
}
```
