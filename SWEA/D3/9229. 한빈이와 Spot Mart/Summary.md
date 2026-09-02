# 🤖 AI 분석

## 💡 접근 방식

정렬된 스낵 배열에서 투 포인터 기법으로 두 스낵의 합을 탐색하여 최대 합을 구함. 이진 탐색적 접근으로 효율적으로 해결.

## ⏱️ 시간 복잡도

O(N log N) — N개의 스낵을 정렬하는 데 O(N log N) 소요. 투 포인터 검사를 O(N), 전체 복잡도는 O(N log N).

## 📦 공간 복잡도

O(N) — 스낵 목록 배열을 저장하기 위한 공간. 추가 변수를 제외한 크기 전용.

## 🔧 개선 사항

입력 크기가 커질 수 있으므로, 배열 크기를 N으로 설정 후 0부터 N-1까지 활용하는 방식으로 바꾸어 메모리 점유를 최소화.
1) snack 배열을 선언할 때 size N이 아닌 size N+1에서 size N으로 변경
2) result 변수를 사용해 구한 값을 확인하는 방식으로 바꿔서 불필요한 비교 줄이기

## 🎯 다음 추천 문제

SW Expert Academy 9228번 - 간단한 2차원 배열 문제 | 비슷한 접근 방식을 연습할 수 있습니다.

## 🏷️ 태그

array, two-pointers, sorting

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
    static int N, M, result;
    static int[] snack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            snack = new int[N];
            result = -1;

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                snack[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(snack);

            int p1 = 0, p2 = N - 1;
            while(p1 < p2) {
                int curW = snack[p1] + snack[p2];
                if (curW > M) {
                    p2--;
                } else {
                    result = Math.max(result, curW);
                    p1++;
                }
            }

            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.print(sb);
    }
}
```
