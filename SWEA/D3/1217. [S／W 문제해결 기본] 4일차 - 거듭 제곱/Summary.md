# 🤖 AI 분석

## 💡 접근 방식

주어진 N의 E 제곱을 구하는 데 반복 곱셈을 사용한 단순 구현 방식.

## ⏱️ 시간 복잡도

O(E) — E가 입력된 지수로, E번 반복하여 N을 곱하는 방식이므로 선형 시간 증가.

## 📦 공간 복잡도

O(1) — 추가적인 저장공간을 거의 사용하지 않으며, 변수 몇 개만 사용.

## 🔧 개선 사항

1) 지수를 줄이는 제곱법 적용하여 시간 복잡도를 O(log E)로 개선.
2) 재귀 또는 반복적으로 효율적인 방법 사용, 예를 들어 제곱을 반으로 나누는 방법 등.

## 🎯 다음 추천 문제

SWEA 1218번 - 괄호 짝짓기 | 스택을 활용하는 문제로, 유사한 자가복잡성을 다루는 훈련.

## 🏷️ 태그

math, implementation

## ✨ 모범 답안

```java
import java.util.Scanner;

class Solution {
    static int N, E;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int tc = 1; tc <= 10; tc++) {
            sc.nextInt();
            N = sc.nextInt();
            E = sc.nextInt();
            int result = power(N, E);
            System.out.printf("#%d %d%n", tc, result);
        }
    }

    static int power(int base, int exp) {
        if (exp == 0) return 1;
        if (exp == 1) return base;
        int half = power(base, exp / 2);
        return (exp % 2 == 0) ? half * half : half * half * base;
    }
}
```
