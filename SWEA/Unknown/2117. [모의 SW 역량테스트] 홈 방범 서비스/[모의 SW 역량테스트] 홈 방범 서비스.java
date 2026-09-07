import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

class Solution
{
	static boolean[][] map = new boolean[20][20];
	static int N, M;
	static int[] profitBorder;
	static int maxHome;

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuilder sb = new StringBuilder();

		in.nextToken();
		int T = (int) in.nval;

		for (int tc = 1; tc <= T; tc++) {
			in.nextToken();
			N = (int) in.nval;
			in.nextToken();
			M = (int) in.nval;

			int kMax = 2 * N - 1;
			profitBorder = new int[kMax + 1];
			maxHome = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					in.nextToken();
					map[i][j] = (int) in.nval == 1;
				}
			}

			for (int i = 1; i <= kMax; i++) {
				int cost = i * i + (i - 1) * (i - 1);
				// i 크기의 영역 전체에 대한 최소 이득이 되는 집의 수
				profitBorder[i] = cost % M > 0 ? cost / M + 1 : cost / M;
			}
			
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					// i, j를 중심으로 크기가 index인 링에 포함되는 집의 수 저장
					int[] ringCnt = new int[kMax];
					
					for (int y = 0; y < N; y++) {
						for(int x = 0; x < N; x++) {
							if (!map[y][x]) continue;
							int dist = Math.abs(y - i) + Math.abs(x - j);
							if (dist < kMax) ringCnt[dist]++;
						}
					}
					
					int sum = 0;
					for (int k = 1; k <= kMax; k++) {
						sum += ringCnt[k - 1];
						if (sum >= profitBorder[k]) {
							maxHome = Math.max(maxHome, sum);
						}
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(maxHome).append('\n');
		}
		System.out.print(sb);
	}
}