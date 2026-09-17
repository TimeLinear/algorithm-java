# 🤖 AI 분석

## 💡 접근 방식

유니온-파인드 자료구조를 사용하여 서로소 집합 처리. 두 개의 집합 연산(cmd에 따라 union 또는 find) 수행 후 결과를 출력.

## ⏱️ 시간 복잡도

O(M * α(N)) — M개의 연산을 수행하며, find와 union은 경로 압축을 통해 상수 시간에 가까워짐. α(N)은 거의 상수에 가까운 함수.

## 📦 공간 복잡도

O(N) — 부모 배열 p[]에 모든 원소의 정보를 저장함으로써 N의 크기를 사용.

## 🔧 개선 사항

1) union 시 rank를 사용해 트리의 깊이를 최소화하여 성능 향상: 부모 배열 대신 rank 배열을 추가.
2) input을 좀 더 간편하게 처리하기 위해 BufferedReader와 StringTokenizer를 그대로 활용. 그러나 StreamTokenizer는 slower할 수 있으므로 switch할 수 있음.

## 🎯 다음 추천 문제

SW Expert Academy 7193번 - 서로소 집합의 응용 문제로, 더 복잡한 상황에서 집합 처리 연습.

## 🏷️ 태그

union-find, implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    static BufferedReader in;
    static int N, M;
    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        for(int tc = 1; tc <= T; tc++) {
            String[] input = in.readLine().split();
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            parent = new int[N + 1];
            rank = new int[N + 1];

            for(int i = 1; i <= N; i++) {
                parent[i] = i;
                rank[i] = 0;
            }

            sb.append('#').append(tc).append(' ');

            for(int i = 0; i < M; i++) {
                input = in.readLine().split();
                int cmd = Integer.parseInt(input[0]);
                int s1 = Integer.parseInt(input[1]);
                int s2 = Integer.parseInt(input[2]);

                if (cmd == 0) union(s1, s2);
                else {
                    boolean isUnion = find(s1) == find(s2);
                    sb.append(isUnion ? 1 : 0);
                }
            }
            sb.append('\n');
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
            if (rank[r1] > rank[r2]) {
                parent[r2] = r1;
            } else {
                parent[r1] = r2;
                if (rank[r1] == rank[r2]) rank[r2]++;
            }
        }
    }
}
```
