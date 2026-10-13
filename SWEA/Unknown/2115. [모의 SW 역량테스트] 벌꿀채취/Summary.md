# 🤖 AI 분석

## 💡 접근 방식

각 행에서 길이 M짜리 모든 구간에 대해 부분집합 비트마스크(2^M)를 전부 돌려 합이 C 이하인 경우의 제곱합 최댓값을 profit[row][col]에 미리 계산해 둔다. 그다음 두 일꾼의 시작 위치를 모두 열거한다. 같은 행이면 두 번째 시작 열을 j+m 이상으로 제한해 겹침과 중복 조합을 막고, 다른 행이면 제한 없이 profit 합의 최댓값을 구한다.

## ⏱️ 시간 복잡도

O(N^2 * 2^M * M + N^4) - 구간별 부분집합 탐색 N*(N-M+1)*2^M*M번과 두 구간 쌍 열거 약 O(N^4)번이며, N≤10, M≤5라 충분히 빠르다.

## 📦 공간 복잡도

O(N^2) - map과 profit 배열만 사용하고, 구간마다 만드는 임시 배열은 크기 M이다.

## 🔧 개선 사항

로직과 경계 조건(같은 행 겹침 방지, 다른 행 처리)은 정확하고 제한 범위에서 성능도 충분하다. 사소한 정리 사항은 다음과 같다. 첫째, calcProfit의 반환값을 쓰지 않으므로 void로 바꾼다. 둘째, Arrays.copyOfRange로 임시 배열을 만들 필요 없이 map[row][col+i]를 직접 읽으면 된다. 셋째, 라벨 continue Outer 대신 내부 루프에서 break하고 결과를 버리는 방식이 가독성에 더 낫다. 넷째, import java.io.*로 정리할 수 있다.

## 🎯 다음 추천 문제

SWEA 2117 [모의 SW 역량테스트] 홈 방범 서비스 - 완전탐색과 구현 유형으로 이어서 풀기 좋다.

## 🏷️ 태그

brute-force, bitmask, implementation

## ✨ 모범 답안

```java
import java.io.*;

class Solution {
    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n, m, c;
    static int[][] map, profit;

    public static void main(String[] args) throws IOException {
        int T = nextInt();
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            n = nextInt();
            m = nextInt();
            c = nextInt();

            map = new int[n][n];
            profit = new int[n][n - m + 1];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = nextInt();
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= n - m; j++) {
                    calcProfit(i, j);
                }
            }

            int maxProfit = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= n - m; j++) {
                    for (int k = i; k < n; k++) {
                        int start = (k == i) ? j + m : 0;
                        for (int l = start; l <= n - m; l++) {
                            maxProfit = Math.max(maxProfit, profit[i][j] + profit[k][l]);
                        }
                    }
                }
            }

            sb.append('#').append(tc).append(' ').append(maxProfit).append('\n');
        }
        System.out.print(sb);
    }

    static void calcProfit(int row, int col) {
        int best = 0;
        for (int flag = 0; flag < (1 << m); flag++) {
            int sum = 0, cur = 0;
            for (int i = 0; i < m; i++) {
                if ((flag & (1 << i)) == 0) continue;
                int v = map[row][col + i];
                sum += v;
                if (sum > c) {
                    cur = -1;
                    break;
                }
                cur += v * v;
            }
            if (cur > best) best = cur;
        }
        profit[row][col] = best;
    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
}

```
