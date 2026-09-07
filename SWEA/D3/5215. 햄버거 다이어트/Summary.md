# 🤖 AI 분석

## 💡 접근 방식

DFS를 통해 모든 음식 조합을 탐색, 제한 칼로리 내에서 최대 맛을 찾음. 각 음식에 대해 선택 여부를 결정.

## ⏱️ 시간 복잡도

O(2^N) — N개의 음식을 선택/미선택할 수 있는 모든 조합 탐색. N=20이면 최대 1,048,576 경우의 수로 비효율적.

## 📦 공간 복잡도

O(N) — 재귀 호출에 의한 스택 깊이, 일부 변수들을 저장. 그러나 주어진 음식 및 칼로리 데이터는 고정적이므로 여기에 치우침.

## 🔧 개선 사항

1) DFS 대신 비트마스크 조합을 사용하여 조합 수를 효율적으로 처리할 수 있습니다.
2) 음식 선택 시, 이미 선택된 칼로리를 재계산할 필요 없이 결과를 모아서 바로 출력하는 방식으로 변경.
3) 맛과 칼로리를 한 번에 처리하기 위한 테이블을 이용하여, 중복 계산을 줄이고 각 조합에 대한 계산을 직접적으로 최적화.

## 🎯 다음 추천 문제

백준 14889번 - 스타트와 링크 | DFS를 활용한 조합 생성 문제 with 최적화 연습.

## 🏷️ 태그

dfs, implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    static int N, L;
    static int[][] food;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            String[] inputs = in.readLine().split();
            N = Integer.parseInt(inputs[0]);
            L = Integer.parseInt(inputs[1]);
            food = new int[N][2];
            result = 0;
            
            for (int i = 0; i < N; i++) {
                inputs = in.readLine().split();
                food[i][0] = Integer.parseInt(inputs[0]);
                food[i][1] = Integer.parseInt(inputs[1]);
            }
            calculateMaxTaste(0, 0, 0);
            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.print(sb);
    }

    static void calculateMaxTaste(int idx, int taste, int kalory) {
        if (kalory > L) return;
        result = Math.max(result, taste);
        for (int i = idx; i < N; i++) {
            calculateMaxTaste(i + 1, taste + food[i][0], kalory + food[i][1]);
        }
    }
}
```
