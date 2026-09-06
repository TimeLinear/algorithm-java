# 🤖 AI 분석

## 💡 접근 방식

재귀 DFS를 통해 전선 연결 가능한 코어를 탐색. 코어 수를 최대화하고 최단 전선 길이를 기록하는 백트래킹 방식.

## ⏱️ 시간 복잡도

O(2^K * N²) — 각 코어에 대해 최대 4가지 방향으로 DFS를 시도, 최악의 경우 K 개의 코어가 전선 연결에 사용되며 각 연결에 대해 도달 가능 여부를 N²로 확인.

## 📦 공간 복잡도

O(N²) — 맵 정보를 저장하는 12x12 배열과 코어 위치를 저장하는 리스트 때문.

## 🔧 개선 사항

1) 'attach' 메서드를 개선해 중복 코드를 줄이고(상태 변경을 재사용) 맵의 상태를 반환하는 방식으로 단순화.
2) 코어 수와 길이를 트래킹하는 변수를 통해 초기화 및 복원 로직을 간소화. 
3) StreamTokenizer 대신 Scanner를 사용하면 편리하고 가독성이 향상됨. 
4) 재귀 깊이를 최적화하여 방문한 코어의 길이가 최대일 때 조기에 탐색 종료.

## 🎯 다음 추천 문제

SW Expert Academy 1768번 - 가위 바위 보 | 백트래킹 기법을 사용해 문제를 더욱 깊이 있게 다루는 연습.

## 🏷️ 태그

dfs, backtracking

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class Solution {
    static int N;
    static int[][] map = new int[12][12];
    static ArrayList<int[]> core = new ArrayList<>();
    static final int[] DY = {-1, 1, 0, 0};
    static final int[] DX = {0, 0, -1, 1};
    static int lenSum = Integer.MAX_VALUE, coreSum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            core.clear();
            lenSum = Integer.MAX_VALUE;
            coreSum = 0;

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                    if (map[i][j] == 1) {
                        if (i > 0 && i < N - 1 && j > 0 && j < N - 1) {
                            core.add(new int[] {i, j});
                        }
                    }
                }
            }
            dfs(0, 0, 0);
            sb.append('#').append(tc).append(' ').append(lenSum).append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int cIdx, int cCnt, int len) {
        if (cIdx >= core.size()) {
            if (coreSum < cCnt || (coreSum == cCnt && lenSum > len)) {
                coreSum = cCnt;
                lenSum = len;
            }
            return;
        }

        int[] cur = core.get(cIdx);
        int y = cur[0];
        int x = cur[1];

        dfs(cIdx + 1, cCnt, len);

        for (int d = 0; d < 4; d++) {
            int dLen = tryAttach(y, x, d);
            if (dLen != -1) {
                dfs(cIdx + 1, cCnt + 1, len + dLen);
                undoAttach(y, x, d, dLen);
            }
        }
    }

    static int tryAttach(int y, int x, int d) {
        int ny = y + DY[d];
        int nx = x + DX[d];
        int dLen = 0;
        while (ny < N && ny >= 0 && nx < N && nx >= 0) {
            if (map[ny][nx] != 0) return -1;
            map[ny][nx] = 2;
            dLen++;
            ny += DY[d];
            nx += DX[d];
        }
        return dLen;
    }

    static void undoAttach(int y, int x, int d, int dLen) {
        int ny = y + DY[d];
        int nx = x + DX[d];
        for (int i = 0; i < dLen; i++) {
            map[ny][nx] = 0;
            ny += DY[d];
            nx += DX[d];
        }
    }
}
```
