# 🤖 AI 분석

## 💡 접근 방식

BFS 기반의 미로 탐색으로 시작점에서 목적지까지의 경로를 탐색하여 도달 여부를 판별.

## ⏱️ 시간 복잡도

O(N^2) — 최대 N^2 크기의 미로를 탐색하며, BFS는 모든 노드를 한 번씩 방문하게 된다. 크기 16x16일 경우 상수로 간주 가능.

## 📦 공간 복잡도

O(N) — 큐(q)와 맵(map)을 저장하기 위한 공간 사용. 크기 16x16의 boolean 배열과 256개의 정수 공간을 가진 큐.

## 🔧 개선 사항

1) 변수 이름을 더 명확하게 수정하여 가독성을 높임: q -> queue, curY -> currentY 등
2) map[x][y] 확인 시 미리 bounds 체크하여 불필요한 배열 접근 회피
3) StringBuilder 대신 ArrayList와 join() 사용으로 성능 삭제, 예외 처리 개선

## 🎯 다음 추천 문제

SWEA 1227번 - 미로2 | 비슷한 미로 탐색 문제에서 경로의 길이를 반환하도록 확장하여 BFS에 대한 이해도를 높이기.

## 🏷️ 태그

bfs, graph

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Solution {
    static final int SIZE = 16;
    static final int[] DY = {-1, 1, 0, 0};
    static final int[] DX = {0, 0, -1, 1};

    static boolean[][] map = new boolean[SIZE][SIZE];
    static int[] queue = new int[SIZE * SIZE];
    static int head, tail;
    static int startY, startX, destY, destX;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            br.readLine(); // Read and ignore test case number

            head = 0;
            tail = 0;
            result = 0;

            for (int i = 0; i < SIZE; i++) {
                String line = br.readLine();
                for (int j = 0; j < SIZE; j++) {
                    char cell = line.charAt(j);
                    if (cell == '2') {
                        startY = i;
                        startX = j;
                    } else if (cell == '3') {
                        destY = i;
                        destX = j;
                    }
                    map[i][j] = cell != '1';
                }
            }

            queue[tail++] = startY * SIZE + startX;
            map[startY][startX] = false;

            while (head != tail) {
                int cur = queue[head++];
                int curY = cur / SIZE;
                int curX = cur % SIZE;

                for (int d = 0; d < DY.length; d++) {
                    int ny = curY + DY[d];
                    int nx = curX + DX[d];

                    if (ny >= 0 && ny < SIZE && nx >= 0 && nx < SIZE && map[ny][nx]) {
                        if (ny == destY && nx == destX) {
                            result = 1;
                            break;
                        }
                        queue[tail++] = ny * SIZE + nx;
                        map[ny][nx] = false;
                    }
                }
            }
            System.out.println('#' + tc + ' ' + result);
        }
    }
}
```
