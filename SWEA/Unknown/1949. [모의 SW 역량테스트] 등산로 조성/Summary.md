# 🤖 AI 분석

## 💡 접근 방식

시작점에서 DFS를 활용해 등산로 길이를 계산. 높이차가 K 이하인 경우 평지에서 깎으며 진행 후 최대 길이 기록.

## ⏱️ 시간 복잡도

O(N^2 * DFS) — 각 시작점에서 DFS를 통해 최대 N^2 번 방문 가능, 깊이가 K까지 갈 수 있어 최악의 경우 O(N^2 * K)이다.

## 📦 공간 복잡도

O(N^2) — visited 배열과 최대 깊이로 쓰이는 스택 공간이 따르며 K 사용 시 O(N) 추가 공간 필요.

## 🔧 개선 사항

1) visited 배열에 비트를 직접 사용해 공간 절약: boolean 배열 대신 int 배열과 비트 마스킹 활용.
2) 불필요한 깊이는 따로 트리밍 (depth 한계 설정 가능).
3) DFS 최적화: 방문 시 들어가기 전 미리 조건 체크하여 무분별한 재귀 호출 방지.

## 🎯 다음 추천 문제

SW Expert 1948번 - 사각형 방 | DFS를 활용해 모든 경로를 탐색하는 유사한 기법으로 추가 학습 가능.

## 🏷️ 태그

dfs, implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Solution {
    static int N, K;
    static int[][] map = new int[8][8];
    static int maxLen;
    static boolean[][] visited;
    static final int[] DY = {-1, 1, 0, 0};
    static final int[] DX = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        StringBuilder sb = new StringBuilder();

        in.nextToken();
        int T = (int) in.nval;

        for(int tc = 1; tc <= T; tc++) {
            in.nextToken();
            N = (int) in.nval;
            in.nextToken();
            K = (int) in.nval;

            maxLen = 0;
            visited = new boolean[N][N];
            int maxVal = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    in.nextToken();
                    map[i][j] = (int) in.nval;
                    if (map[i][j] > maxVal) maxVal = map[i][j];
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
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
        if (maxLen < curLen) maxLen = curLen;
        visited[y][x] = true;

        for(int d = 0; d < 4; d++) {
            int ny = y + DY[d];
            int nx = x + DX[d];

            if (ny >= N || ny < 0 || nx >= N || nx < 0 || visited[ny][nx]) continue;

            if (map[ny][nx] < map[y][x]) {
                dfs(ny, nx, curLen + 1, k_used);
            } else if (map[ny][nx] - K < map[y][x] && !k_used) {
                map[ny][nx] = map[y][x] - 1;
                dfs(ny, nx, curLen + 1, true);
                map[ny][nx] = map[ny][nx] + 1;
            }
        }
        visited[y][x] = false;
    }
}
```
