# 🤖 AI 분석

## 💡 접근 방식

최고 높이의 봉우리를 기준으로 깊이 우선 탐색(DFS)을 사용하여 내리막 길을 탐색하고, 최대 길이를 기록.

## ⏱️ 시간 복잡도

O(N^2 * (N^2 + 4)) — N² 개의 봉우리에서 시작된 DFS 호출이 최악의 경우 모든 방향(상하좌우)으로 탐색. 각 DFS 호출이 상태를 업데이트하므로, 깊이는 최대 N²에 도달 가능.

## 📦 공간 복잡도

O(N²) — visited 배열과 재귀 호출 스택 때문에 최악의 경우 방문한 위치를 기록하므로 NxN 저장 공간 필요.

## 🔧 개선 사항

1) visited 처리를 비트마스크 대신 boolean 배열을 사용하는 것이 가독성 증가.
2) DFS 메서드의 인자에서 현재 위치 대신 방향 변수를 사용하여 중복된 코드 줄이기.
3) map 배열 사용하는 대신 int[][]를 활용해 히스토리를 줄이고 클린 코드를 위해 메서드 분리.

## 🎯 다음 추천 문제

백준 16236번 - 아기 상어 | DFS/BFS와 경로탐색을 통한 그래프 탐험 성격의 문제로 연습.

## 🏷️ 태그

dfs, backtracking

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution
{
    static int N, K;
    static int[][] map = new int[8][8];
    static int maxLen;
    static boolean[][] visited;
    static final int[] DY = {-1, 1, 0, 0};
    static final int[] DX = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String[] tokens = br.readLine().split();
            N = Integer.parseInt(tokens[0]);
            K = Integer.parseInt(tokens[1]);
            maxLen = 0;
            visited = new boolean[N][N];

            int maxVal = 0;
            for (int i = 0; i < N; i++) {
                String[] row = br.readLine().split();
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(row[j]);
                    if (map[i][j] > maxVal) maxVal = map[i][j];
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == maxVal) {
                        dfs(i, j, 1, false);
                    }
                }
            }
            sb.append('#').append(tc).append(' ').append(maxLen).append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int y, int x, int curLen, boolean k_used) {
        maxLen = Math.max(maxLen, curLen);
        visited[y][x] = true;

        for (int d = 0; d < 4; d++) {
            int ny = y + DY[d];
            int nx = x + DX[d];

            if (ny >= N || ny < 0 || nx >= N || nx < 0 || visited[ny][nx]) continue;

            if (map[ny][nx] < map[y][x]) {
                dfs(ny, nx, curLen + 1, k_used);
            } else if ((map[ny][nx] - K) < map[y][x] && !k_used) {
                int tmp = map[ny][nx];
                map[ny][nx] = map[y][x] - 1;
                dfs(ny, nx, curLen + 1, true);
                map[ny][nx] = tmp;
            }
        }
        visited[y][x] = false;
    }
}
```
