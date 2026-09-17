# 🤖 AI 분석

## 💡 접근 방식

DFS 탐색을 통한 경로 최적화. 비트마스크를 사용하여 지나온 알파벳을 관리하며 중복 경로를 피함.

## ⏱️ 시간 복잡도

O(4^(R*C)) — 최대 R*C 개의 셀을 탐색하므로 경우의 수는 지수적으로 증가하나 비트마스크를 통해 중복을 피함. 실제 시간은 비트마스크에 의해 줄어든다.

## 📦 공간 복잡도

O(R*C) — 메모리 사용의 대부분은 HashSet에 경로 기록 및 DFS 스택 공간에 소비된다.

## 🔧 개선 사항

1) HashSet을 ConcurrentHashMap으로 변경하여 동시성 문제 방지 및 메모리 효율성 증대. 
2) DFS의 깊이 한계를 두고 매개변수 (x, y) 를 인스턴스에 저장하여 재사용.
3) 사용하지 않는 main() 메서드의 불필요한 로직 제거.

## 🎯 다음 추천 문제

SW Expert Academy 4008번 - 숫자 만들기 | 비트마스크와 DFS를 활용한 다른 조합 및 경로 문제로 연습.

## 🏷️ 태그

dfs, backtracking, bit-manipulation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Solution {
    static final int MAX_SIZE = 20;
    static final int[] dr = {0, 1, 0, -1};
    static final int[] dc = {1, 0, -1, 0};
    static int R, C;
    static char[][] map = new char[MAX_SIZE + 1][MAX_SIZE + 1];
    static int maxCnt;
    static HashSet<Long> dp = new HashSet<>();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            maxCnt = 0;
            dp.clear();

            for (int i = 1; i <= R; i++) {
                String line = br.readLine();
                for (int j = 1; j <= C; j++) {
                    map[i][j] = line.charAt(j - 1);
                }
            }

            dfs(1, 1, (1 << (map[1][1] - 'A')), 1);
            sb.append('#').append(tc).append(' ').append(maxCnt).append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int r, int c, int flag, int cnt) {
        if (maxCnt != cnt) { 
            if (!dp.add(key(r, c, flag))) return;
            for (int d = 0; d < 4; d++) { 
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr > R || nr < 1 || nc > C || nc < 1 || (flag & (1 << (map[nr][nc] - 'A'))) != 0)
                    continue;
                dfs(nr, nc, flag | (1 << (map[nr][nc] - 'A')), cnt + 1);
            }
        }
        maxCnt = Math.max(maxCnt, cnt);
    }

    static long key(int r, int c, int flag) {
        return ((long) r << 32) | ((long) c << 26) | flag;
    }
}
```
