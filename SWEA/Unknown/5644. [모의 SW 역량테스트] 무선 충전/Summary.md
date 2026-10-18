# 🤖 AI 분석

## 💡 접근 방식

사용자 A와 B의 이동 경로를 기반으로 충전 패턴을 분석하여 최대 충전량을 계산하는 알고리즘.

## ⏱️ 시간 복잡도

O(M * A²) — 사용자가 M회 이동하기 위해 각 이동마다 배터리 A개에 대해 충전 여부를 체크하며, 각 배터리와 각 사용자 간의 거리 계산이 포함될 때 약 O(MA)로 예상, 하지만 비트 마스킹으로 인한 최적화로 O(MA) 범주.

## 📦 공간 복잡도

O(A) — 배터리 정보는 최대 8개로 상정되어 있으며, 필요 메모리 소모는 이러한 배터리 정보를 위해 차지하는 정도.

## 🔧 개선 사항

1) 코드 가독성 향상: 메서드와 변수명에 설명 추가 필요.
2) 한 번의 반복 내에서 최대 충전량을 결정하는 로직을 간소화할 수 있으며, 주석을 덧붙여 가독성을 높임.
3) Bit manipulation 대신 배열 활용 방법을 도입해 접근성 증가.

## 🎯 다음 추천 문제

SWEA 15683번 - 감시 | 해당 문제의 방향 탐색 및 효율적인 자원 활용 전략을 훈련하기에 적합.

## 🏷️ 태그

implementation, math

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {
    static final int[] DY = {0, -1, 0, 1, 0};
    static final int[] DX = {0, 0, 1, 0, -1};
    static int M, A;
    static int[] moveA, moveB, batteryPower;
    static int[][] bMap;
    static int[] uA, uB;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String[] input = in.readLine().split();
            M = Integer.parseInt(input[0]);
            A = Integer.parseInt(input[1]);

            moveA = new int[M];
            moveB = new int[M];
            batteryPower = new int[A];
            bMap = new int[11][11];
            uA = new int[] {1, 1}; // A의 초기 위치
            uB = new int[] {10, 10}; // B의 초기 위치

            String[] movesA = in.readLine().split();
            String[] movesB = in.readLine().split();
            for (int i = 0; i < M; i++) {
                moveA[i] = Integer.parseInt(movesA[i]);
                moveB[i] = Integer.parseInt(movesB[i]);
            }

            initializeBattery(in);

            int result = 0;
            for (int i = 0; i <= M; i++) {
                if (i > 0) {
                    uA[0] += DY[moveA[i - 1]];
                    uA[1] += DX[moveA[i - 1]];
                    uB[0] += DY[moveB[i - 1]];
                    uB[1] += DX[moveB[i - 1]];
                }
                result += accumulatePower();
            }
            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.print(sb);
    }

    static void initializeBattery(BufferedReader in) throws IOException {
        for (int i = 0; i < A; i++) {
            String[] batteryInfo = in.readLine().split();
            int bX = Integer.parseInt(batteryInfo[0]);
            int bY = Integer.parseInt(batteryInfo[1]);
            int bC = Integer.parseInt(batteryInfo[2]);
            int bP = Integer.parseInt(batteryInfo[3]);
            batteryPower[i] = bP;
            setBatteryMap(bY, bX, bC, i);
        }
    }

    static void setBatteryMap(int bY, int bX, int bC, int idx) {
        for (int j = Math.max(0, bY - bC); j <= Math.min(10, bY + bC); j++) {
            for (int k = Math.max(0, bX - bC); k <= Math.min(10, bX + bC); k++) {
                if (Math.abs(bY - j) + Math.abs(bX - k) <= bC) {
                    bMap[j][k] |= (1 << idx);
                }
            }
        }
    }

    static int accumulatePower() {
        int uAB = bMap[uA[0]][uA[1]];
        int uBB = bMap[uB[0]][uB[1]];

        int powA = getMaxPower(uAB);
        int powB = getMaxPower(uBB);

        if ((uAB & uBB) != 0) { // 같은 배터리를 이용한 경우
            return powA + powB - Math.max(powA, powB);
        }
        return powA + powB;
    }

    static int getMaxPower(int userBatteryBit) {
        int maxPower = 0;
        while (userBatteryBit != 0) {
            int idx = Integer.numberOfTrailingZeros(userBatteryBit);
            maxPower = Math.max(maxPower, batteryPower[idx]);
            userBatteryBit &= ~(1 << idx);
        }
        return maxPower;
    }
}
```
