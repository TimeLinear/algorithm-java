# 🤖 AI 분석

## 💡 접근 방식

크루스칼 알고리즘으로 MST를 구한다. 간선을 [u, v, w] 배열로 리스트에 저장해 가중치 오름차순으로 정렬하고, union-find(경로 압축 + 랭크 기반 합치기)로 사이클을 만들지 않는 간선만 선택한다. 선택한 간선 수가 V-1이 되면 조기 종료하고, 가중치 합은 long으로 누적한다.

## ⏱️ 시간 복잡도

O(E log E) - 간선 정렬이 지배적이고 union-find 연산은 거의 상수 시간(역아커만 함수)이다.

## 📦 공간 복잡도

O(V + E) - parents와 rank 배열은 O(V), 간선 리스트는 O(E)이며 각 간선이 int[3] 객체다.

## 🔧 개선 사항

로직은 정확하고 효율적이다. 결과를 long으로 누적하고 V-1개 선택 시 조기 종료하는 점도 좋다. 개선할 부분은 두 가지다. (1) 비교자 `a[2] - b[2]`는 뺄셈 오버플로 위험이 있으므로 `Integer.compare(a[2], b[2])`가 안전하다. 이 문제의 가중치 범위(-1,000,000~1,000,000)에서는 실제로 문제가 되지 않는다. (2) E가 최대 약 20만이므로 int[] 객체 리스트 대신 간선을 int 배열 3개에 저장하고 인덱스를 정렬하거나, 우선순위 큐를 쓰면 객체 생성 비용을 줄일 수 있지만 필수는 아니다. 그 외에는 들여쓰기(탭/공백 혼용)를 정리하고, find를 재귀 대신 반복문으로 바꾸면 깊은 재귀 걱정이 사라진다. 랭크 기반 합치기를 쓰므로 현재도 재귀 깊이는 O(log V)라 안전하다.

## 🎯 다음 추천 문제

SWEA에서 MST를 이어서 연습하려면 '최소 스패닝 트리', '하나로' 같은 키워드로 검색해 보자. 하나로는 좌표 기반 간선 비용 계산이 필요한 크루스칼/프림 문제다. 문제 번호는 확실하지 않아 적지 않는다.

## 🏷️ 태그

mst, kruskal, union-find, graph

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
    static int[] parents;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            parents = new int[V + 1];
            rank = new int[V + 1];
            for (int i = 1; i <= V; i++) {
                parents[i] = i;
            }

            int[][] edges = new int[E][3];
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                edges[i][0] = Integer.parseInt(st.nextToken());
                edges[i][1] = Integer.parseInt(st.nextToken());
                edges[i][2] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

            long result = 0;
            int cnt = 0;
            for (int[] edge : edges) {
                if (union(edge[0], edge[1])) {
                    result += edge[2];
                    if (++cnt == V - 1) break;
                }
            }
            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.print(sb);
    }

    static boolean union(int a, int b) {
        int x = find(a);
        int y = find(b);
        if (x == y) return false;
        if (rank[x] < rank[y]) {
            parents[x] = y;
        } else if (rank[x] > rank[y]) {
            parents[y] = x;
        } else {
            parents[y] = x;
            rank[x]++;
        }
        return true;
    }

    static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }
}
```
