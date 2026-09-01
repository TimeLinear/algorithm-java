# 🤖 AI 분석

## 💡 접근 방식

주어진 학생 간의 키 관계를 그래프 형태로 표현 후, 위상 정렬과 비트셋을 활용해 상대 키 순서를 세고 조합하여 결과를 도출.

## ⏱️ 시간 복잡도

O(N + M) — 위상 정렬과 각 비트셋 연산이 포함되어 있어 총 노드(학생) N과 간선(비교) M에 대해 선형 시간.

## 📦 공간 복잡도

O(N^2) — 각 학생에 대해 N 크기의 비트셋을 사용하여 메모리 요구량 증가. 최악의 경우 비트셋 저장소의 크기 수치가 N^2.

## 🔧 개선 사항

1) 비트셋 사용으로 인한 고정 크기 메모리 낭비를 줄이기 위해 Set<Integer>나 Map<Integer, Set<Integer>> 구조로 변환.
2) 매 번 비트셋 조작 대신 인접 리스트로 간단한 인수 계산 고려 → 필요 없는 정보를 저장하는 방식 줄이기.
3) 중복된 cardinality 계산을 min 해주면 시간 복잡도에 효율적.

## 🎯 다음 추천 문제

백준 2252번 - 줄 세우기 | 소수를 비교하는 과정과 위상 정렬 문제를 다루며, 상대적인 정렬 이론 강화를 위한 좋은 연습 대안.

## 🏷️ 태그

graph, implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    static int N, M;
    static int[] tp;
    static List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            tp = new int[N + 1];
            adj = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                adj[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                adj[n1].add(n2);
                tp[n2]++;
            }

            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= N; i++) {
                if (tp[i] == 0) {
                    q.add(i);
                }
            }

            Set<Integer> taller = new HashSet<>(), smaller = new HashSet<>();
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int next : adj[cur]) {
                    taller.add(next);
                    tp[next]--;
                    if (tp[next] == 0) {
                        q.add(next);
                    }
                }
            }

            int answer = 0;
            for (int i = 1; i <= N; i++) {
                if (taller.contains(i)) {
                    answer++;
                }
            }
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        System.out.print(sb);
    }
}
```
