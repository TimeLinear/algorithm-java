# 🤖 AI 분석

## 💡 접근 방식

2차원 배열을 사용하여 색종이 정보를 누적합으로 저장한 후, 재귀적 분할 방식으로 4진 트리 형태로 결과 생성

## ⏱️ 시간 복잡도

O(N^2) — 격자 전체를 한 번 순회하여 누적합을 계산하고, 개별 색종이에 대해 최대 log(N) 깊이의 재귀 호출, 결국 O(N^2)로 고정됨.

## 📦 공간 복잡도

O(N^2) — 누적합 저장을 위한 psum 배열과 색종이 정보를 위한 map 배열을 각각 N×N 크기로 사용.

## 🔧 개선 사항

1) StreamTokenizer는 불필요하게 복잡하므로, Scanner 클래스를 사용하여 입력을 간단하게 처리하도록 개선.
2) sb.append() 대신 StringBuilder를 줄이면 메모리 사용량을 조금 더 효율적으로 줄일 수 있음.
3) if 조건문을 배열로 단순화하여 가독성과 성능 개선.

## 🎯 다음 추천 문제

백준 2630번 - 색종이 만들기 | 색종이를 재귀적으로 나누어 관리하는 문제로, 색상 구별을 더 간단히 처리할 수 있게 연습.

## 🏷️ 태그

divide-and-conquer, dynamic-programming

## ✨ 모범 답안

```java
import java.util.Scanner;

public class Main {
    static int N;
    static boolean[][] map;
    static int[][] psum;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new boolean[N][N];
        psum = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt() == 1;
                psum[i + 1][j + 1] = psum[i][j + 1] + psum[i + 1][j] - psum[i][j] + (map[i][j] ? 1 : 0);
            }
        }

        divide(0, 0, N);
        System.out.print(sb);
    }

    static void divide(int y, int x, int size) {
        if (size <= 0) return;
        int sum = psum[y + size][x + size] - psum[y][x + size] - psum[y + size][x] + psum[y][x];

        if (sum == 0) {
            sb.append(0);
        } else if (sum == size * size) {
            sb.append(1);
        } else {
            sb.append('X');
            int half = size / 2;
            divide(y, x, half);
            divide(y, x + half, half);
            divide(y + half, x, half);
            divide(y + half, x + half, half);
        }
    }
}
```
