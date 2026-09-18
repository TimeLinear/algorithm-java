# 🤖 AI 분석

## 💡 접근 방식

행렬 제곱을 이용해 피보나치 수열을 효율적으로 계산. log(n) 시간 복잡도로 n번째 피보나치 수를 구함.

## ⏱️ 시간 복잡도

O(log n) — 행렬 거듭제곱 계산을 이분법적으로 진행하여 n이 커도 극적으로 빠른 계산이 가능.

## 📦 공간 복잡도

O(1) — 상수 크기 행렬만을 사용하여 공간 복잡도가 작음. 결과 저장은 별도 구조 사용 안 함.

## 🔧 개선 사항

1) BufferedReader와 StreamTokenizer 대신 Scanner 사용으로 코드 가독성 향상
2) StringBuilder에 결과 저장 후 한 번에 출력하는 대신 반복 출력으로 변경 시 성능 저하 있음.
3) modulo 연산 효율을 잃지 않도록 재작성: 매트릭스 곱셈 과정에서 변수 타입 주의.

## 🎯 다음 추천 문제

백준 11444번 - 피보나치 수 6 | 행렬 거듭제곱과 모듈로 연산을 체계적으로 학습하는 연습 단계.

## 🏷️ 태그

math, dynamic-programming

## ✨ 모범 답안

```java
import java.util.Scanner;

public class Main {
    static final int MOD = 10000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int N = scanner.nextInt();
            if (N == -1) break;
            System.out.println(fiboMod(N));
        }
        scanner.close();
    }

    static long[][] matpow(long[][] M, int n) {
        long[][] result = {{1, 0}, {0, 1}};
        long[][] base = {{M[0][0], M[0][1]}, {M[1][0], M[1][1]}};

        while(n > 0) {
            if ((n & 1) == 1) {
                result = matMulti(result, base);
            }
            base = matMulti(base, base);
            n >>= 1;
        }
        return result;
    }

    static long[][] matMulti(long[][] A, long[][] B) {
        long[][] C = new long[2][2];
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                long sum = 0;
                for(int k = 0; k < 2; k++) {
                    sum = (sum + A[i][k] * B[k][j] % MOD) % MOD;
                }
                C[i][j] = sum;
            }
        }
        return C;
    }

    static long fiboMod(int n) {
        long[][] M = {{1, 1}, {1, 0}};
        long[][] result = matpow(M, n);
        return result[0][1];
    }
}
```
