# 🤖 AI 분석

## 💡 접근 방식

주어진 8개의 숫자를 순환 큐로 다루며, 0이 아닐 때까지 감소하는 규칙에 따라 값을 갱신.

## ⏱️ 시간 복잡도

O(k) — k는 주어진 값들이 0이 될 때까지의 갱신 횟수에 비례. 최악의 경우 8번마다 1씩 감소하므로 상수 시간 복잡도일 가능성 높음.

## 📦 공간 복잡도

O(1) — 고정된 크기의 배열(nums)과 변수(head, tail, sub) 사용. 결과 출력에 요구되는 메모리 빼고 추가 메모리 사용 없음.

## 🔧 개선 사항

1) 서브 값을 미리 함수로 묶어 재사용성 향상
2) 외부의 불필요한 배열을 제거하고, 입력을 HashSet으로 처리하여 간결함 개선
3) nums 배열에 직접 줄인 값 할당 외에 List로 대체하여 유연성 확보

## 🎯 다음 추천 문제

백준 스택 수열 (문제 번호: 1874) | 순환 큐 대신 스택의 활용과 관련 깊은 문제로 연습 가치가 높음.

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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 1; tc <= 10; tc++) {
            String[] inputs = br.readLine().split();
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(inputs[i]);
            }
            
            int sub = 1;
            for (int i = 0; nums[(i % N)] != 0; ) {
                nums[i % N] = Math.max(0, nums[i % N] - sub);
                sub = (sub % 5) + 1;
                i++;
            }
            
            sb.append('#').append(tc).append(' ');
            for (int i = 0; i < N; i++) {
                sb.append(nums[i]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
```
