# 🤖 AI 분석

## 💡 접근 방식

주어진 수열에서 최장 증가 부분 수열(LIS) 길이를 이진 탐색을 활용하여 구함. 기초 배열 순회와 lowerBound로 조정.

## ⏱️ 시간 복잡도

O(N log N) — N개의 수를 탐색하며 각 탐색에서 이진 검색(lowerBound) 수행. 이진 검색 비용은 log N, 총 N값에 대해 고려하여 O(N log N)이다.

## 📦 공간 복잡도

O(N) — 결과를 저장하기 위한 c 배열과 nums 배열을 사용. 각 배열의 최대 크기가 N에 비례.

## 🔧 개선 사항

1) lowerBound 메소드를 일반적인 이진 검색 표기법으로 가독성을 개선. 
2) nextInt() 대신 Scanner를 사용하면 I/O가 간결해짐. 
3) 필요 변수의 초기화와 선언을 for-loop 내부로 이동하여 가독성 향상. 
4) 제한된 자료구조 활용으로 메모리 점유를 최소화. 

예시: \nstatic int nextInt() { return scanner.nextInt();}

## 🎯 다음 추천 문제

백준 9251번 - LCS | LCS(최장 공통 부분 수열) 문제를 통해 LIS 알고리즘과 유사한 방식으로 해결할 수 있어 추가 학습에 도움됨.

## 🏷️ 태그

array, dynamic-programming

## ✨ 모범 답안

```java
import java.util.Scanner;
import java.util.Arrays;

class Solution {
    static Scanner scanner = new Scanner(System.in);
    static int[] nums, c;

    public static void main(String[] args) {
        int T = scanner.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int n = scanner.nextInt();
            nums = new int[n];
            c = new int[n];

            for (int i = 0; i < n; i++) {
                nums[i] = scanner.nextInt();
            }

            int size = 0;
            for (int i = 0; i < n; i++) {
                int insertIdx = lowerBound(size, nums[i]);
                c[insertIdx] = nums[i];
                if (insertIdx == size) size++;
            }
            sb.append('#').append(tc).append(' ').append(size).append('\n');
        }
        System.out.print(sb);
    }

    static int lowerBound(int end, int target) {
        int cur = 0;
        while (cur < end) {
            int mid = (cur + end) / 2;
            if (c[mid] >= target) {
                end = mid;
            } else {
                cur = mid + 1;
            }
        }
        return end;
    }
}
```
