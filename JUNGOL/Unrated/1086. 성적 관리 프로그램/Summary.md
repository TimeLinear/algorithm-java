# 🤖 AI 분석

## 💡 접근 방식

학생 성적을 입력받고 총점, 평균, 최대/최소를 계산 후 막대그래프 형태로 출력하는 프로그램.

## ⏱️ 시간 복잡도

O(N) — N은 과목 수로, 총 7개 과목에 대한 입력과 계산 모두 선형적으로 수행됨. 전체 과정에서 정적인 복잡성을 보임.

## 📦 공간 복잡도

O(1) — 입력 배열(scores)와 결과 배열(values) 외 추가적인 저장 공간을 요구하지 않음.

## 🔧 개선 사항

1) 점수 입력을 함수로 분리하여 코드 구조를 개선 (재사용성 ↑).
2) AVG 출력부분에서 decimal place를 원하지 않으면 Math.floor()를 활용하여 정확히 반영.
3) 그래프 출력 과정에서 불필요한 StringBuilder 인스턴스를 줄이기 위해 append()를 재사용.

예시:
int avg = (int)Math.floor((double)tot / scores.length);

## 🎯 다음 추천 문제

백준 10950번 - A+B - 3 | 성적 관리와 유사한 입출력 및 리스트의 합을 이용한 간단한 문제.

## 🏷️ 태그

implementation

## ✨ 모범 답안

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = inputScores(sc);

        int tot = 0;
        int max = scores[0];
        int min = scores[0];
        for (int v : scores) {
            tot += v;
            if (v > max) max = v;
            if (v < min) min = v;
        }
        int avg = (int)Math.floor((double)tot / scores.length);

        System.out.printf("TOT : %d \nAVG : %d \nMAX : %d \nMIN : %d \n", tot, avg, max, min);

        char[] labels = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        int[] values = {scores[0], scores[1], scores[2], scores[3], scores[4], scores[5], scores[6], avg, max, min};

        printGraph(values, labels);
        sc.close();
    }

    private static int[] inputScores(Scanner sc) {
        int[] scores = new int[7];
        for (int i = 0; i < scores.length; i++) {
            while (true) {
                int v = sc.nextInt();
                if (v >= 0 && v <= 100) {
                    scores[i] = v;
                    break;
                }
            }
        }
        return scores;
    }

    private static void printGraph(int[] values, char[] labels) {
        for (int row = 100; row >= 10; row -= 10) {
            System.out.printf("%3d", row);
            for (int v : values) {
                int height = (v / 10) * 10;
                System.out.print(height >= row ? "   *" : "    ");
            }
            System.out.println();
        }

        System.out.print("   ");
        for (char c : labels) {
            System.out.print("   ").print(c);
        }
        System.out.println();
    }
}
```
