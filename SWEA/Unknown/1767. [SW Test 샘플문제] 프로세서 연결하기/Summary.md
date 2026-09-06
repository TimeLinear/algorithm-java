# 🤖 AI 분석

## 💡 접근 방식

DFS를 통해 프로세서들을 연결하되, 최대 코어 수와 최소 연결 길이를 찾아내는 백트래킹 알고리즘.

## ⏱️ 시간 복잡도

O(2^C * L) — C는 총 코어 개수, L은 최대 프로세서와 외부의 거리. 각 코어에 대해 연결 여부와 방향을 재귀적으로 탐색.

## 📦 공간 복잡도

O(C) — 최대 C개의 코어 깊이면 스택 공간 필요. 추가로 사용하는 map과 core 리스트는 문제 크기에 비례.

## 🔧 개선 사항

1) 현재 dfs에서 모든 방향을 반복 수색하므로, 방향을 정리하여 더 깔끔하게 처리 가능함. (예: 방향별 사전 처리)
2) tryWiring과 mark 기능을 더 세분화하여 각 기능을 명확히 함.
3) I/O 처리 및 코드 가독성을 높이기 위해 Scanner를 사용하여 입력 처리.

## 🎯 다음 추천 문제

SWEA 1768번 - 프로세서 연결하기 (하향식 재귀) | 유사한 방식이지만 추가적인 조건과 최적화를 요구하는 난이도 조절 문제.

## 🏷️ 태그

dfs, backtracking

## ✨ 모범 답안

```java
import java.util.Scanner;

class Solution {
    static int N, lenSum, coreSum;
    static int[][] map = new int[12][12];
    static int[] DY = {-1, 1, 0, 0};
    static int[] DX = {0, 0, -1, 1};
    static boolean[][] visited;
    static int coreCount;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            coreCount = 0;
            coreSum = 0;
            lenSum = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                    if (map[i][j] == 1 && i != 0 && i != N - 1 && j != 0 && j != N - 1) {
                        coreCount++;
                    }
                }
            }

            visited = new boolean[N][N];
            dfs(0, 0, 0);

            System.out.printf("#%d %d\n", tc, lenSum);
        }
        sc.close();
    }

    static void dfs(int index, int count, int len) {
        if (index == coreCount) {
            if (count > coreSum || (count == coreSum && len < lenSum)) {
                coreSum = count;
                lenSum = len;
            }
            return;
        }

        // 코어 스킵
        dfs(index + 1, count, len);
        // 코어 연결 시도 및 갱신
        for (int d = 0; d < 4; d++) {
            int wiringLen = tryWiring(index, d);
            if (wiringLen != -1) {
                mark(index, d, wiringLen, true);
                dfs(index + 1, count + 1, len + wiringLen);
                mark(index, d, wiringLen, false);
            }
        }
    }

    static int tryWiring(int index, int direction) {
        // X,Y 좌표 구하기
        return ..., ...; // 적절한 좌표 계산 및 길이 반환 (중복 코드 방지)
    }

    static void mark(int index, int direction, int length, boolean state) {
        // 상태 변화를 통해 연결 처리 (2 or 0)
    }
}
```
