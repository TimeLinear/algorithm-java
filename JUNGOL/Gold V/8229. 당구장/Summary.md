# 🤖 AI 분석

## 💡 접근 방식

주어진 방향 그래프를 따라 최댓값을 찾아가는 DFS와 할당 기반 소위 '사이클 형성' 탐색. 각 노드에서의 누적 거리 계산.

## ⏱️ 시간 복잡도

O(N) — 각 노드는 한 번 방문되며 노드 간 사이클을 체크하면서 경로를 추적하므로 선형 시간 범위.

## 📦 공간 복잡도

O(N) — 추가적으로 사용한 배열(상태, 거리 등)에 대해 최대 N 크기를 할당하여 선형 공간 필요.

## 🔧 개선 사항

1) 상황에 따라 직접적으로 long 형태의 dist 배열을 사용하였으나, 메모리 절약을 위해 필요 시 byte 단위로 절약할 수 있음. 
2) StreamTokenizer 대신 BufferedReader와 StringTokenizer를 사용하여 I/O 속도를 개선할 수 있음.
3) 상태 관리를 위한 state 배열은 Sparse 처리 방식으로 변환할 수 있음. 필요시 HashMap을 사용하여 메모리 절약.

## 🎯 다음 추천 문제

백준 11403번 - 경로 찾기 | 그래프 탐색 기초 연습 후 유사한 DFS/그래프 최적화 문제로 연습하기 좋은 문제.

## 🏷️ 태그

graph, dfs

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long X;
    static int[] p, a;
    static long[] dist;
    static int[] state;
    static int maxNum;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        X = Long.parseLong(tokenizer.nextToken());

        p = new int[N + 1];
        a = new int[N + 1];
        dist = new long[N + 1];
        state = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            a[i] = Integer.parseInt(reader.readLine());
        }
        for (int i = 1; i <= N; i++) {
            p[i] = Integer.parseInt(reader.readLine());
        }

        maxNum = -1;
        int[] stack = new int[N];

        for (int start = 1; start <= N; start++) {
            if (state[start] != 0)
                continue;

            int top = 0;
            int cur = start;
            
            while (cur != -1 && state[cur] == 0) {
                state[cur] = 1;
                stack[top++] = cur;
                cur = p[cur];
            }

            long base;
            boolean valid;
            if (cur == -1) {
                base = 0;
                valid = true;
            } else if (state[cur] == 2) {
                base = dist[cur];
                valid = true;
            } else {
                base = -1;
                valid = false;
            }

            for (int k = top - 1; k >= 0; k--) {
                int node = stack[k];
                if (valid) {
                    base += a[node];
                    dist[node] = base;
                    state[node] = 2;
                } else {
                    state[node] = 3;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (state[i] == 2 && dist[i] <= X) {
                maxNum = Math.max(maxNum, i);
            }
        }

        System.out.println(maxNum);
    }
}
```
