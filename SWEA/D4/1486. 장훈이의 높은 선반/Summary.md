# 🤖 AI 분석

## 💡 접근 방식

DFS로 조합을 탐색하여 총합이 목표 B 이상일 때의 최소 차이 계산. 모든 조합을 고려해 최적해를 찾음.

## ⏱️ 시간 복잡도

O(2^N) — N개의 조합을 모두 탐색하는 경로가 존재하며, 각 원소를 포함할지 말지를 결정한다. 최악의 경우 모든 조합 생성에 2^N 시간 소요.

## 📦 공간 복잡도

O(N) — 재귀 호출 스택과 같은 추가 공간은 최대 N에 비례. 조합의 길이가 N이기 때문.

## 🔧 개선 사항

1) DFS 대신 비트 마스크를 활용하여 조합을 생성할 수 있습니다. 이는 코드의 명시성을 높이고 중복 탐색을 피해 성능 개선 가능.
2) minDiff를 업데이트하는 조건문은 중복 호출을 줄이고 명확히 할 수 있습니다.
3) 입력을 한 번 받고, 배열을 더 적게 사용하도록 하여 메모리 효율성을 높이세요.

## 🎯 다음 추천 문제

SWEA 1487번 - 장훈이의 낮은 선반 | 비슷한 조합 및 차이 계산을 활용한 문제로 연습.

## 🏷️ 태그

dfs, backtracking

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static int N, B;
    static int[] assistants = new int[20];
    static int minDiff;
    
    private static void calculateMinDiff() {
        for (int mask = 0; mask < (1 << N); mask++) {
            int total = 0;
            for (int i = 0; i < N; i++) {
                if ((mask & (1 << i)) != 0) {
                    total += assistants[i];
                }
            }
            if (total >= B) {
                minDiff = Math.min(minDiff, total - B);
            }
        }
    }
    
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            minDiff = Integer.MAX_VALUE;
            
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                assistants[i] = Integer.parseInt(st.nextToken());
            }
            
            calculateMinDiff();
            System.out.println("#" + tc + " " + minDiff);
        }
    }
}
```
