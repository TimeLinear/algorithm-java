# 🤖 AI 분석

## 💡 접근 방식

DFS와 비트마스킹을 활용하여 카드 게임의 이길 경우의 수를 계산. Memoization으로 중복 계산을 방지.

## ⏱️ 시간 복잡도

O(2^N * N) — 카드 수에 따라 모든 카드 조합을 확인하는 DFS로 최악의 경우 2^N의 시간 복잡도가 발생. N=9로 제한적이지만, 각 깊이에 최대 N번의 카드를 확인함으로써 복잡도 증가.

## 📦 공간 복잡도

O(N) — memoization 배열과 factorials 배열로 O(N) 추가 공간 사용.

## 🔧 개선 사항

1) 메모이제이션 배열을 1차원으로 줄이기 위해 현재 카드 점수로 인덱스를 줄 수 있습니다. 
2) 입력을 처리 시 assert를 통해 카드 범위를 검증함으로써 에러 방지. 
3) StringBuilder 대신 문자열을 미리 결합할 배열을 사용해 최종 결과 확인 시 메모리 성능을 높여야 합니다.

## 🎯 다음 추천 문제

백준 14500번 - 테트로미노 | DFS와 메모이제이션 같은 접근 방식을 활용하면서, 더 복잡한 상황과 카드를 찾아나가는 연습에 적합.

## 🏷️ 태그

recursion, implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Solution
{
    static final int MAX_SCORE = 171;
    static final int CARD_NUM = 9;
    static int[] gCard = new int[CARD_NUM];
    static int[] iCard = new int[CARD_NUM];
    static int[] factorials = new int[CARD_NUM + 1];
    static int gCheck;
    static int[] memoization;

    private static int dfs(int gScore, int iScore, int depth, int visited) {
        int half = ((MAX_SCORE + 1) / 2);

        if (gScore >= half) return factorials[CARD_NUM - depth];
        if (iScore >= half) return 0;

        if (memoization[visited] != -1) {
            return memoization[visited];
        }

        int winCnt = 0;

        for (int i = 0; i < CARD_NUM; i++) {
            if ((visited & (1 << i)) != 0) continue;
            int g = gCard[depth];
            int nextVisited = visited | (1 << i);

            if (g > iCard[i]) {
                winCnt += dfs(gScore + g + iCard[i], iScore, depth + 1, nextVisited);
            } else {
                winCnt += dfs(gScore, iScore + g + iCard[i], depth + 1, nextVisited);
            }
        }

        return memoization[visited] = winCnt;
    }

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        factorials[0] = 1;
        for(int i = 1; i < CARD_NUM + 1; i++) {
            factorials[i] = factorials[i - 1] * i;
        }

        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            gCheck = 0;
            memoization = new int[1 << CARD_NUM];  // 1D memoization array
            Arrays.fill(memoization, -1);

            for(int i = 0; i < CARD_NUM; i++) {
                gCard[i] = Integer.parseInt(st.nextToken());
                gCheck |= (1 << gCard[i]);
            }

            int idx = 0;
            for(int i = 1; i <= CARD_NUM * 2; i++) {
                if((gCheck & (1 << i)) == 0) {
                    iCard[idx++] = i;
                }
            }

            int winCnt = dfs(0, 0, 0, 0);
            sb.append("#").append(tc).append(" ").append(winCnt).append(" ").append(factorials[CARD_NUM] - winCnt).append("\n");
        }
        System.out.println(sb);
    }
}
```
