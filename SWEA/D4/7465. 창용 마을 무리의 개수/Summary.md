# 🤖 AI 분석

## 💡 접근 방식

Union-Find 자료구조를 통해 연결된 컴포넌트의 수를 세는 알고리즘. 주어진 M개의 연결에 대해 Union 및 Find 연산 활용.

## ⏱️ 시간 복잡도

O((N + M) * α(N)) — Union-Find의 기본 연산은 거의 상수 시간 O(1)이며, α는 아커만 함수의 역함수로 매우 느리게 증가함. 따라서 최악의 경우에도 효율적.

## 📦 공간 복잡도

O(N) — 부모 배열과 루트 배열에 대해 최대 N개의 요소를 사용해 메모리 소모.

## 🔧 개선 사항

1) Union-Find의 경로 압축을 추가하는 것이 이미 구현됨. 고급 최적화로는 Union by rank를 도입하여 성능을 한층 강화할 수 있습니다. 
2) StreamTokenizer 대신 Scanner를 사용하면 대화형 입출력에서 성능이 개진될 수 있습니다. 3) IOException 예외 처리를 구체적으로 해주면 코드의 가독성과 안정성을 높일 수 있습니다.

## 🎯 다음 추천 문제

백준 1976번 - 여행 가자 | 유사한 Union-Find 알고리즘을 적용하여 그룹을 찾는 문제. 좋은 연습이 될 것입니다.

## 🏷️ 태그

union-find, implementation

## ✨ 모범 답안

```java
import java.util.Scanner;
import java.util.Arrays;

class Solution {
    static int N, M;
    static int[] p = new int[101];
    static boolean[] roots = new boolean[101];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            Arrays.fill(p, 0, N + 1, 0);
            Arrays.fill(roots, 0, N + 1, false);

            for (int i = 1; i <= N; i++) {
                p[i] = i;
            }

            for (int i = 0; i < M; i++) {
                int s1 = sc.nextInt();
                int s2 = sc.nextInt();
                union(s1, s2);
            }

            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                int root = find(i);
                if (!roots[root]) {
                    roots[root] = true;
                    cnt++;
                }
            }

            sb.append('#').append(tc).append(' ').append(cnt).append('\n');
        }
        System.out.print(sb);
        sc.close();
    }

    static int find(int x) {
        if (p[x] == x) return x;
        return p[x] = find(p[x]);
    }

    static void union(int s1, int s2) {
        int r1 = find(s1);
        int r2 = find(s2);
        if (r1 != r2) {
            p[r1] = r2;
        }
    }
}
```
