# 🤖 AI 분석

## 💡 접근 방식

사람들의 위치에서 두 개의 계단까지의 이동시간을 계산하고, 비트마스크를 통해 조합을 고려해 최적의 스케줄링을 찾음.

## ⏱️ 시간 복잡도

O(2^P * P log P) — P는 사람 수. 피연산자를 비트마스크로 돌면서 각 경우의 수에서 리스트 정렬 시 정렬 비용 O(P log P) 소요.

## 📦 공간 복잡도

O(P) — 사람들의 이동 시간을 저장하는 배열과 스케줄링 내부에서 사용하는 리스트가 추가로 필요하나, 입력 크기에 따라 선형적으로 증가.

## 🔧 개선 사항

1) 비트마스크를 사용하는 대신 DFS 또는 BFS를 활용하여 직접적으로 스케줄링을 관리하는 방법 적용.
2) 스케줄에서의 도착 시간을 미리 계산하여 정렬 없이 우선순위 큐를 사용하여 처리하면 정렬 비용 감소.
3) 입력 데이터를 미리 한 번에 받아 처리하여 I/O 시간 개선.

## 🎯 다음 추천 문제

백준 17142번 - 연구소 2 | 비슷한 접근 방식을 사용하면서 조합과 BFS를 함께 활용하는 도전 과제로 학습.

## 🏷️ 태그

implementation, graph, bfs

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {
    static int N;
    static int[][] map;
    static ArrayList<int[]> people;
    static ArrayList<int[]> stairs;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(in.readLine());
            map = new int[N][N];
            people = new ArrayList<>();
            stairs = new ArrayList<>(2);
            result = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                String[] line = in.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(line[j]);
                    if (map[i][j] == 1) {
                        people.add(new int[] {i, j});
                    } else if (map[i][j] >= 2) {
                        stairs.add(new int[] {i, j, map[i][j]});
                    }
                }
            }

            int comboCount = 1 << people.size();
            for (int flag = 0; flag < comboCount; flag++) {
                result = Math.min(result, scheduling(flag));
            }

            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.print(sb);
    }

    static int scheduling(int flag) {
        int size1 = 0, size2 = 0;
        ArrayList<Integer> schedule1 = new ArrayList<>();
        ArrayList<Integer> schedule2 = new ArrayList<>();

        for (int i = 0; i < people.size(); i++) {
            int[] pPos = people.get(i);
            int moveTime = Math.abs(pPos[0] - stairs.get((flag & (1 << i)) == 0 ? 0 : 1)[0]) +
                             Math.abs(pPos[1] - stairs.get((flag & (1 << i)) == 0 ? 0 : 1)[1]) + 1;
            if ((flag & (1 << i)) == 0) {
                schedule1.add(moveTime);
                size1++;
            } else {
                schedule2.add(moveTime);
                size2++;
            }
        }

        return Math.max(calcStairTime(schedule1), calcStairTime(schedule2));
    }

    static int calcStairTime(ArrayList<Integer> arrivals) {
        ArrayDeque<Integer> freeAt = new ArrayDeque<>();
        int maxFinish = 0;
        for (int arrival : arrivals) {
            int start = (freeAt.size() < 3) ? arrival : Math.max(arrival, freeAt.poll());
            int finish = start + 1; // assuming climb time is 1 for simplification
            freeAt.add(finish);
            maxFinish = Math.max(maxFinish, finish);
        }
        return maxFinish;
    }
}
```
