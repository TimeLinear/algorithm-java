# 🤖 AI 분석

## 💡 접근 방식

주어진 8개의 암호 숫자를 큐처럼 순환배열로 수정하며, 최소값에 따라 감소 처리 후 결과 출력.

## ⏱️ 시간 복잡도

O(N*M) — 8개의 숫자를 M번 반복 처리하며, 각 반복마다 N(8)회의 최소값 검색이 필요. NxM 근사치로 볼 수 있음.

## 📦 공간 복잡도

O(1) — 입력된 숫자 배열 외에 추가 공간을 사용하지 않음; 상수 공간 유지.

## 🔧 개선 사항

1) 최소값 탐색을 단순 선형 검색에서 힙을 사용하여 O(log N) 탐색으로 개선 가능.
2) sub 변수를 재귀적으로 사용할 필요 없이, 연속적인 감소 로직을 직접 구현해 가지치기 회수를 최소화하여 코드를 직관적으로 개선 가능.

## 🎯 다음 추천 문제

백준 1240번 - 단체사진 | 큐와 순환 배열 사용에 대한 더 복잡한 로직을 학습하기 적합.

## 🏷️ 태그

implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    static final int N = 8;
    static int[] nums = new int[N];

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            String[] input = in.readLine().split();
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(input[i + 1]);
            }

            int sub = 1;
            while (true) {
                int min = Integer.MAX_VALUE;
                for (int num : nums) {
                    if (num != 0 && num < min) {
                        min = num;
                    }
                }
                if (min == Integer.MAX_VALUE) break;

                int cycle = (min - 1) / 15;
                if (cycle > 0) {
                    for (int i = 0; i < N; i++) {
                        nums[i] -= 15 * cycle;
                    }
                    continue;
                }

                for (int i = 0; i < N; i++) {
                    if (nums[i] != 0) {
                        nums[i] -= sub;
                        if (nums[i] < 0) nums[i] = 0;
                        break;
                    }
                }
                sub = (sub % 5) + 1;
            }

            sb.append('#').append(tc).append(' ');
            for (int num : nums) {
                sb.append(num).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
```
