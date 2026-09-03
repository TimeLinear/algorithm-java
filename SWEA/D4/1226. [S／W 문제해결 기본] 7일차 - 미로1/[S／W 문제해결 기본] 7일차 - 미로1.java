import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution
{
	static final int SIZE = 16;
	static final int[] DY = {-1, 1, 0, 0};
	static final int[] DX = {0, 0, -1, 1};
	
	static boolean[][] map = new boolean[SIZE][SIZE];
	static int[] xq;
	static int[] yq;
	static int head, tail;
	static int destY, destX; // y, x
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= 10; tc++) {
			// 불필요한 테스트케이스 번호 입력 처리
			sb.append('#').append(br.readLine().trim()).append(' ');
			
			yq = new int[SIZE * SIZE + 1];
			xq = new int[SIZE * SIZE + 1];
			
			head = 0;
			tail = 0;
			
			result = 0;
			
			for(int i = 0; i < SIZE; i++) {
				String str = br.readLine();
				for(int j = 0; j < SIZE; j++) {
					char cell = str.charAt(j);
					if (cell == '2') {
						yq[tail] = i;
						xq[tail] = j;
						tail = (tail + 1) % SIZE;
					} else if (cell == '3') {
						destY = i;
						destX = j;
					}
					// map의 true는 길, false는 그 외
					map[i][j] = cell == '0' || cell == '3';
				}
			}
			
			outer:
			while(head != tail) {
				int curY = yq[head];
				int curX = xq[head++];
				
				int ny, nx;
				for(int d = 0; d < DY.length; d++) {
					ny = curY + DY[d];
					nx = curX + DX[d];
					
					if(ny >= SIZE || ny < 0 || nx >= SIZE || nx < 0 || !map[ny][nx])
						continue;
					
					if (ny == destY && nx == destX) {
						result = 1;
						break outer;
					}
					
					yq[tail] = ny;
					xq[tail++] = nx;
					map[ny][nx] = false;
				}
			}
			sb.append(result).append(System.lineSeparator());
		}
		System.out.print(sb);
	}
}