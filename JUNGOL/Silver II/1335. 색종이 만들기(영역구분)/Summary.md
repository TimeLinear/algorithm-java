# 🤖 AI 분석

## 💡 접근 방식

2차원 배열과 누적합을 활용하여 색종이를 재귀적으로 분할하며 색깔 수를 카운트하는 방식.

## ⏱️ 시간 복잡도

O(N^2) — 전체 영역에 대해 O(N²)에서 누적합 계산이 선형적으로 수행되고, 분할 과정은 log(N) 깊이지만 각 분할 영역에서는 항상 O(1) 연산이 진행되어 전략적으로도 O(N²)로 제한됨.

## 📦 공간 복잡도

O(N²) — 2차원 배열 형태의 map과 psum 배열이 사용되므로 공간 복잡도 또한 O(N²).

## 🔧 개선 사항

1) StreamTokenizer보다 BufferedReader를 사용한 직접 입력 방식으로 간결하게 개선 가능.
2) psum 배열 사용을 생략하고, divide 함수 내에서 직접 하나의 recusive layout을 변형하여 색깔 카운트로 구현하여 공간 복잡도를 줄일 수 있음.

## 🎯 다음 추천 문제

백준 2630번 - 색종이 - 같은 기본적인 재귀적 색상 분할 문제로 코드 구조를 연습할 수 있으니 추천.

## 🏷️ 태그

divide-and-conquer, recursion

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static boolean[][] map;
    static int blue, white;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        map = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            String[] line = br.readLine().trim().split(" ");
            for(int j = 0; j < N; j++) {
                map[i][j] = line[j].equals("1");
            }
        }
        divide(0, 0, N);
        System.out.println(white);
        System.out.println(blue);
    }

    static void divide(int y, int x, int size) {
        if (size == 1) {
            if (map[y][x]) blue++;
            else white++;
            return;
        }

        int count = 0;
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (map[i][j]) count++;
            }
        }

        if (count == 0) white++;
        else if (count == size * size) blue++;
        else {
            int half = size / 2;
            divide(y, x, half);
            divide(y + half, x, half);
            divide(y, x + half, half);
            divide(y + half, x + half, half);
        }
    }
}
```
