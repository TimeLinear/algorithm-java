# 🤖 AI 분석

## 💡 접근 방식

비트마스크를 사용하여 N-Queen 문제를 해결. 행과 열, 대각선에 각각 진입 여부를 비트로 표현, 재귀를 통해 모든 위치에 퀸을 배치.

## ⏱️ 시간 복잡도

O(N!) — N개의 퀸을 배치하는 방법을 구하는 것으로, N!의 경우의 수를 탐색. 하지만 비트마스크로 대각선 제약을 효과적으로 처리해 효율성을 높임.

## 📦 공간 복잡도

O(N) — 재귀 호출의 깊이가 최대 N까지 올라가며 스택 메모리 필요. 비트마스크 외에는 추가적인 자료구조 필요 없음.

## 🔧 개선 사항

1) 코드 가독성을 높이기 위해 각 인자를 의미 있는 이름으로 바꾸는 것이 좋습니다. 예: cols -> occupiedCols, main -> occupiedMainDiagonal 등.
2) 그리드 정보를 배열로 저장하여 현재 상태를 직관적으로 파악할 수 있도록 합니다.
3) 테스트 케이스 출력을 sys.stdout 대신 BufferedWriter로 처리하여 성능을 낮출 수 있습니다.

## 🎯 다음 추천 문제

SW Expert Academy 1516번 - 롤러코스터 | N-Queen과 유사한 백트래킹 기법을 활용한 컨트롤 문제로 확장.

## 🏷️ 태그

backtracking, bit-manipulation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

class Solution
{
    static int N, cnt, FULL;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            FULL = (1 << N) - 1;
            cnt = 0;
            
            setQueen(0, 0, 0, 0);
            bw.write('#' + tc + ' ' + cnt + '\n');
        }
        bw.flush();
    }

    static void setQueen(int row, int occupiedCols, int occupiedMainDiagonal, int occupiedSubDiagonal) {
        if (row == N) {
            ++cnt;
            return;
        }

        int available = FULL & ~(occupiedCols | occupiedMainDiagonal | occupiedSubDiagonal);
        
        while (available != 0) {
            int bit = available & (-available);
            available -= bit;
            
            setQueen(row + 1, occupiedCols | bit, (occupiedMainDiagonal | bit) << 1, (occupiedSubDiagonal | bit) >> 1);
        }
    }
}
```
