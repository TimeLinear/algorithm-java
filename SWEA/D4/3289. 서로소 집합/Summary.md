# 🤖 AI 분석

## 💡 접근 방식

유니온-파인드 알고리즘을 이용해 서로소 집합을 구현. union-find 연산 및 경로 압축을 통한 성능 최적화 작업 수행.

## ⏱️ 시간 복잡도

O(M * α(N)) — M개의 union/find 연산을 처리하고 α(N) (아커만 함수의 역함수)로 매우 빠르게 처리됨.

## 📦 공간 복잡도

O(N) — N개의 정수 배열(p, rank)을 사용하여 부모와 랭크를 저장.

## 🔧 개선 사항

1) 러닝된 StreamTokenizer 대신 BufferedReader 사용하여 I/O 최적화. 2) find() 함수 개선: 불필요한 if 문 제거로 성능 향상. 3) 두 집합의 랭크 비교 시, 더 높은 랭크의 루트가 낮은 랭크의 루트를 품도록 수정하여 `if (rank[r1] == rank[r2]) rank[r2]++;` 제거.

예시: `p[r1] = r2; if (rank[r1] == rank[r2]) rank[r2]++;`

## 🎯 다음 추천 문제

백준 1717번 - 집합의 표현 | 친구 관계를 다루는 문제로 연산의 복잡성 이해에 도움. 유니온-파인드 사용.

## 🏷️ 태그

union-find, implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    static int N, M; 
    static int[] p, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String[] input = in.readLine().split();
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);

            p = new int[N + 1];
            rank = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                p[i] = i;
                rank[i] = 0;
            }
            sb.append('#').append(tc).append(' ');

            for (int i = 0; i < M; i++) {
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
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    static void union(int s1, int s2) {
        int r1 = find(s1);
        int r2 = find(s2);
        if (r1 == r2) return;
        if (rank[r1] > rank[r2]) p[r2] = r1;
        else { p[r1] = r2; if (rank[r1] == rank[r2]) rank[r2]++; }
    }
}
```
