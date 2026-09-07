# 🤖 AI 분석

## 💡 접근 방식

2D 맵에서 집 위치를 파악하고, 각 중심점에서의 홈 수를 계산하여 가능한 최대 수익을 확보하는 알고리즘.

## ⏱️ 시간 복잡도

O(N^4) — 집의 분포를 기반으로 4중 루프: N×N 중심점 × N×N 맵 확인; N=20이면 약 1,600,000 연산.

## 📦 공간 복잡도

O(N) — 링 별로 집 수를 세기 위한 배열을 사용, but O(kMax) 이므로 최악은 O(20).

## 🔧 개선 사항

1) 거리 계산 시 Math.abs를 사용하기보다는 대칭성을 활용한 조건식을 통해 계산을 줄일 수 있습니다.
2) 현재 반복 체크에서 home count(reduce duplicate) 및 break 사용을 고려하여 불필요한 계산을 줄일 수 있습니다.

## 🎯 다음 추천 문제

SWEA 1953번 - 탈주범 검거 | 그래프 탐색 및 최적 경로를 찾는 문제로, 효율적 계산법을 연습하는 데 적합하다.

## 🏷️ 태그

implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

class Solution {
    static boolean[][] map = new boolean[20][20];
    static int N, M;
    static int[] profitBorder;
    static int maxHome;

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        StringBuilder sb = new StringBuilder();

        in.nextToken();
        int T = (int) in.nval;

        for (int tc = 1; tc <= T; tc++) {
            in.nextToken();
            N = (int) in.nval;
            in.nextToken();
            M = (int) in.nval;

            int kMax = 2 * N - 1;
            profitBorder = new int[kMax + 1];
            maxHome = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    in.nextToken();
                    map[i][j] = (int) in.nval == 1;
                }
            }

            for (int i = 1; i <= kMax; i++) {
                int cost = i * i + (i - 1) * (i - 1);
                profitBorder[i] = (cost + M - 1) / M; // Ceil effect
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!map[i][j]) continue;
                    int[] ringCnt = new int[kMax];
                    for (int y = 0; y < N; y++) {
                        for (int x = 0; x < N; x++) {
                            if (map[y][x]) {
                                int dist = Math.abs(y - i) + Math.abs(x - j);
                                if (dist < kMax) ringCnt[dist]++;
                            }
                        }
                    }

                    int sum = 0;
                    for (int k = 1; k <= kMax; k++) {
                        sum += ringCnt[k - 1];
                        if (sum >= profitBorder[k]) {
                            maxHome = Math.max(maxHome, sum);
                        }
                    }
                }
            }
            sb.append('#').append(tc).append(' ').append(maxHome).append('\n');
        }
        System.out.print(sb);
    }
}
```
