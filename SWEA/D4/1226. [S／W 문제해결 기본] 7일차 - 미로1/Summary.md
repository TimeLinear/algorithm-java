# 🤖 AI 분석

## 💡 접근 방식

 BFS를 이용하여 미로에서 시작점(2)과 도착점(3) 간의 경로를 탐색. 큐를 활용한 레벨 순회 구조.

## ⏱️ 시간 복잡도

O(N^2) — 최대 16x16 크기이므로, 모든 셀에 대해 방문 검사하며 조건 체크. 전체 큐 처리 시 256개의 셀을 검사하게 되어 결과적으로 O(N^2)으로 나타남.

## 📦 공간 복잡도

O(N) — 최대 256 클러스터의 셀을 큐에 저장. 공간이 제한적이나 고정 사전 할당을 고려해 O(1)로도 평가 가능.

## 🔧 개선 사항

1) 큐를 배열 대신 Queue<T>를 사용하여 동적 크기 처리. 2) 방문 확인을 boolean 배열이 아닌 int 배열로 하여 '0'을 통해 상수 저장(방문 체킹 필요 시) 가능.

## 🎯 다음 추천 문제

SWEA 1267번 - 이동하기 | BFS로 미로 탐색기 능력을 개선하며 다양한 경로 및 장애물 처리 훈련.

## 🏷️ 태그

bfs, implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static final int SIZE = 16;
    static final int[] DY = {-1, 1, 0, 0};
    static final int[] DX = {0, 0, -1, 1};
    static boolean[][] map = new boolean[SIZE][SIZE];
    static int destY, destX; // y, x

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            sb.append('#').append(br.readLine().trim()).append(' ');

            // Initialize map and find start and end points.
            for (int i = 0; i < SIZE; i++) {
                String str = br.readLine();
                for (int j = 0; j < SIZE; j++) {
                    char cell = str.charAt(j);
                    if (cell == '2') {
                        map[i][j] = true; // Start point
                    } else if (cell == '3') {
                        destY = i;
                        destX = j;
                    }
                    map[i][j] = cell == '0' || cell == '3';
                }
            }

            // Perform BFS
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0, 0}); // Start from (0,0)
            map[0][0] = false; // Mark as visited
            int result = 0;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curY = cur[0];
                int curX = cur[1];

                for (int d = 0; d < DY.length; d++) {
                    int ny = curY + DY[d];
                    int nx = curX + DX[d];

                    if (ny >= SIZE || ny < 0 || nx >= SIZE || nx < 0 || !map[ny][nx])
                        continue;

                    if (ny == destY && nx == destX) {
                        result = 1;
                        break;
                    }

                    queue.offer(new int[]{ny, nx});
                    map[ny][nx] = false; // Mark as visited
                }
                if (result == 1) break;
            }

            sb.append(result).append(System.lineSeparator());
        }
        System.out.print(sb);
    }
}
```
