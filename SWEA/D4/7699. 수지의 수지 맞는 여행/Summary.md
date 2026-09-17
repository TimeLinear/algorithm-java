# 🤖 AI 분석

## 💡 접근 방식

DFS를 사용하여 격자에서 이동하며 방문한 알파벳을 비트 마스크로 관리하여 최대 방문 개수 계산.

## ⏱️ 시간 복잡도

O(4^(R*C)) — 각 정점에서 최대 4 방향으로 진행하며 모든 경로를 탐색, 최악의 경우 격자 크기와 연관된 지수적 복잡도. 그러나 가변적인 조합으로 비트 마스크로 방문 알파벳 수를 조절함.

## 📦 공간 복잡도

O(R*C) — 재귀 호출 깊이의 최악 경우 수에 따라 스택 공간 사용량이 증가하지만, 비트 마스크를 전역 변수로 사용해 추가 메모리 소비는 없다.

## 🔧 개선 사항

1) DFS에 방문 여부 관리를 위해 flag를 배열이 아닌 Set<Character>로 변경하면 코드 가독성이 향상됨. 
2) 불필요한 비트 연산을 줄이기 위해 visited 배열을 관리하며 간단하게 이동 경계를 체크.
3) 메서드의 중복된 코드 정리를 통해 읽기 쉽게 개선할 수 있음.

## 🎯 다음 추천 문제

SW Expert Academy 7202 - 알파벳 개수 세기 | 비트 마스크 & DFS 연습에 이어, 다양한 조합 문제로 확장할 수 있어 좋음.

## 🏷️ 태그

 dfs, backtracking, implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Solution {
    static final int MAX_SIZE = 20;
    static final int[] dr = {0, 1, 0, -1};
    static final int[] dc = {1, 0, -1, 0};
    static int R, C;
    static char[][] map = new char[MAX_SIZE + 1][MAX_SIZE + 1];
    static int maxCnt;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String[] rc = br.readLine().split(" ");
            R = Integer.parseInt(rc[0]);
            C = Integer.parseInt(rc[1]);
            maxCnt = 0;

            for (int i = 1; i <= R; i++) {
                String line = br.readLine();
                for (int j = 1; j <= C; j++) {
                    map[i][j] = line.charAt(j - 1);
                }
            }

            Set<Character> visited = new HashSet<>();
            visited.add(map[1][1]);
            dfs(1, 1, visited);
            sb.append('#').append(tc).append(' ').append(maxCnt).append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int r, int c, Set<Character> visited) {
        maxCnt = Math.max(maxCnt, visited.size());
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr > R || nr < 1 || nc > C || nc < 1 || visited.contains(map[nr][nc])) continue;

            visited.add(map[nr][nc]);
            dfs(nr, nc, visited);
            visited.remove(map[nr][nc]);
        }
    }
}
```
