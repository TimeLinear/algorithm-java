# 🤖 AI 분석

## 💡 접근 방식

주어진 명령어에 따라 플레이어의 방향을 조정하고, 주위 환경과 충돌 여부를 검사한 후 이동 및 사격을 실행.

## ⏱️ 시간 복잡도

O(N) — N은 명령어 길이로, 각 명령에 대해 한 번씩만 처리하므로 선형 시간.

## 📦 공간 복잡도

O(H * W) — 최대 H*W의 필드와 N개의 명령어를 저장할 배열 사용.

## 🔧 개선 사항

1) 방향 이동 및 플레이어 업데이트를 선명하게 하는 전체 방향 업데이트 로직 최적화.
2) shoot()와 방향 판별을 메서드로 통합.
3) StringBuilder 대신 char[]로 결과를 직접 출력하여 메모리 사용 최적화.
예시: 처리 최적화를 위해 shoot와 방향 조절을 한 번에 처리하는 메서드로 통합.

## 🎯 다음 추천 문제

SWEA 1874번 - 스택 수열 | Stack을 활용하여 문제 해결 논리를 단순화하고, 방어적으로 상태를 관리하는 연습.

## 🏷️ 태그

implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static final char[] player = {'^', 'v', '<', '>'};
    static int H, W, cy, cx, dir;
    static char[][] field = new char[20][20];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            for (int i = 0; i < H; i++) {
                field[i] = br.readLine().toCharArray();
                for (int j = 0; j < W; j++) {
                    if (field[i][j] == '^' || field[i][j] == 'v' || field[i][j] == '<' || field[i][j] == '>') {
                        cy = i;
                        cx = j;
                        dir = getDirection(field[i][j]);
                    }
                }
            }

            char[] cmds = br.readLine().toCharArray();
            for (char cmd : cmds) {
                if (cmd == 'S') {
                    shoot();
                } else {
                    dir = getDirection(cmd);
                    move();
                }
            }

            sb.append('#').append(tc).append(' ');
            for (int i = 0; i < H; i++) {
                sb.append(field[i]).append('\n');
            }
        }
        System.out.print(sb);
    }

    static void move() {
        int ny = cy + dy[dir];
        int nx = cx + dx[dir];
        if (ny >= 0 && ny < H && nx >= 0 && nx < W && field[ny][nx] == '.') {
            field[cy][cx] = '.';
            cy = ny;
            cx = nx;
            field[cy][cx] = player[dir];
        }
    }

    static void shoot() {
        int ny = cy + dy[dir];
        int nx = cx + dx[dir];
        while (ny >= 0 && ny < H && nx >= 0 && nx < W) {
            if (field[ny][nx] == '*') {
                field[ny][nx] = '.';
                break;
            } else if (field[ny][nx] == '#') {
                break;
            }
            ny += dy[dir];
            nx += dx[dir];
        }
    }

    static int getDirection(char cmd) {
        switch (cmd) {
            case 'U': return 0;
            case 'D': return 1;
            case 'L': return 2;
            case 'R': return 3;
            default: return dir;
        }
    }
}
```
