# 🤖 AI 분석

## 💡 접근 방식

사람들이 두 개의 계단으로 이동하는 최단 시간을 DFS를 통해 조합적으로 탐색하고, 각 계단에서의 최대 대기 시간을 계산하여 최소 시간을 찾음.

## ⏱️ 시간 복잡도

O(2^P * P log P) — P는 사람 수, 모든 조합에 대해 스케줄링 호출 (2^P), 각 호출에서 정렬 (P log P). 이론적으로 제한된 P로 인해 실제 성능은 향상될 수 있음.

## 📦 공간 복잡도

O(2^P + P) — DP 배열은 조합 수를 기반으로 2^P 크기와 각 배열 요소는 최대 P 크기로 저장됨. 사람 수에 따라 추가적인 공간이 필요함.

## 🔧 개선 사항

1) DFS 대신 비트마스크 DP를 활용하여 중복된 DFS 호출 제거로 시간 단축.
2) 스케줄링 내배정 상태를 동적으로 관리하여 중복 계산 방지.
3) I/O를 개선하여 속도 향상.

## 🎯 다음 추천 문제

백준 17837번 - 새로운 게임2 | 캐릭터가 이동해 대기하는 상황을 다룬 문제로 비슷한 사고 과정을 필요로 함.

## 🏷️ 태그

bfs, dfs, dynamic-programming

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    static int N;
    static int[][] map;
    static ArrayList<int[]> people;
    static ArrayList<int[]> stairs;
    static int[] moveTime1, moveTime2;
    static int[] dp;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(in.readLine());
            initialize();

            for (int i = 0; i < N; i++) {
                String[] row = in.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    int cell = Integer.parseInt(row[j]);
                    map[i][j] = cell;
                    if (cell == 1) {
                        people.add(new int[] {i, j});
                    } else if (cell >= 2) {
                        stairs.add(new int[] {i, j, cell});
                    }
                }
            }
            calcMoveTime();
            dfs(0, 0);
            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.print(sb);
    }

    static void initialize() {
        map = new int[10][10];
        people = new ArrayList<>();
        stairs = new ArrayList<>(2);
        moveTime1 = new int[10];
        moveTime2 = new int[10];
        dp = new int[1 << 10];
        Arrays.fill(dp, -1);
        result = Integer.MAX_VALUE;
    }

    static void calcMoveTime() {
        for (int i = 0; i < people.size(); i++) {
            int[] pPos = people.get(i);
            int[] stair1 = stairs.get(0);
            int[] stair2 = stairs.get(1);
            moveTime1[i] = Math.abs(pPos[0] - stair1[0]) + Math.abs(pPos[1] - stair1[1]);
            moveTime2[i] = Math.abs(pPos[0] - stair2[0]) + Math.abs(pPos[1] - stair2[1]);
        }
    }

    static void dfs(int cnt, int flag) {
        if (cnt == people.size()) {
            result = Math.min(result, scheduling(flag));
            return;
        }
        dfs(cnt + 1, flag);  // 1번 계단
        dfs(cnt + 1, flag | (1 << cnt));  // 2번 계단
    }

    static int scheduling(int flag) {
        if (dp[flag] != -1) return dp[flag];
        ArrayList<Integer> schedule1 = new ArrayList<>(), schedule2 = new ArrayList<>();

        for (int i = 0; i < people.size(); i++) {
            if ((flag & (1 << i)) == 0) {
                schedule1.add(moveTime1[i] + 1);
            } else {
                schedule2.add(moveTime2[i] + 1);
            }
        }

        schedule1.sort(Integer::compare);
        schedule2.sort(Integer::compare);
        int sum1 = calcStairTime(schedule1, stairs.get(0)[2]);
        int sum2 = calcStairTime(schedule2, stairs.get(1)[2]);
        return dp[flag] = Math.max(sum1, sum2);
    }

    static int calcStairTime(ArrayList<Integer> arrivals, int climbTime) {
        ArrayDeque<Integer> freeAt = new ArrayDeque<>();
        int maxFinish = 0;

        for (int arrival : arrivals) {
            int start = freeAt.size() < 3 ? arrival : Math.max(arrival, freeAt.poll());
            int finish = start + climbTime;
            freeAt.add(finish);
            maxFinish = Math.max(maxFinish, finish);
        }
        return maxFinish;
    }
}
```
