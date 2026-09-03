import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution
{
	static final int SIZE = 16;
	static final int[] DY = {-1, 1, 0, 0};
	static final int[] DX = {0, 0, -1, 1};
	
	static boolean[][] map = new boolean[SIZE][SIZE];
	static int[] q = new int[SIZE * SIZE];
	static int head, tail;
	static int startY, startX, destY, destX; // y, x
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= 10; tc++) {
			// 불필요한 테스트케이스 번호 입력 처리
			sb.append('#').append(br.readLine().trim()).append(' ');
			
			head = 0;
			tail = 0;
			
			result = 0;
			
			for(int i = 0; i < SIZE; i++) {
				String line = br.readLine();
				for(int j = 0; j < SIZE; j++) {
					char cell = line.charAt(j);
					if (cell == '2') {
						startY = i;
						startX = j;
					} else if (cell == '3') {
						destY = i;
						destX = j;
					}
					// map의 true는 길, false는 그 외
					map[i][j] = cell != '1';
				}
			}
			
			q[tail++] = startY * SIZE + startX;
			map[startY][startX] = false;
			
			outer:
			while(head != tail) {
				int cur = q[head++];
				int curY = cur / SIZE; // cur은 16 * y + x. 이 때 x는 16 미만
				int curX = cur % SIZE;
				
				int ny, nx;
				for(int d = 0; d < DY.length; d++) {
					ny = curY + DY[d];
					nx = curX + DX[d];
					
					if(ny >= SIZE - 1 || ny < 1 || nx >= SIZE + 1 || nx < 1 || !map[ny][nx])
						continue;
					
					if (ny == destY && nx == destX) {
						result = 1;
						break outer;
					}
					
					q[tail++] = ny * SIZE + nx;
					map[ny][nx] = false;
				}
			}
			sb.append(result).append(System.lineSeparator());
		}
		System.out.print(sb);
	}
}