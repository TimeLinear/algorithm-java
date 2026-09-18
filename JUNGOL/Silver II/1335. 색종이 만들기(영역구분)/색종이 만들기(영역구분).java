import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
    static int N;
	static boolean[][] map;
	static int[][] psum;
	static int blue, white;

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuilder sb = new StringBuilder();
		
		in.nextToken();
		N = (int) in.nval;
		
		map = new boolean[N][N];
		psum = new int[N + 1][N + 1];
		
		for(int i = 0; i < N; i++) {
			for(int  j = 0; j < N; j++) {
				in.nextToken();
				map[i][j] = in.nval == 1.0;
				psum[i + 1][j + 1] = psum[i][j + 1] 
						+ psum[i + 1][j] - psum[i][j] 
						+ (map[i][j] ? 1 : 0);
			}
		}
		
		divide(0, 0, N);
		
		sb.append(white).append('\n').append(blue);
		System.out.print(sb);
	}

	static void divide(int y, int x, int size) {
		if (size <= 0) return;
		
		int sum = psum[y + size][x + size] - psum[y][x + size] 
				- psum[y + size][x] + psum[y][x];
		
		if (sum == 0) {
			white++;
		} else if (sum == size * size) {
			blue++;
		} else {
			int half = size / 2;
			divide(y, x, half);
			divide(y + half, x, half);
			divide(y, x + half, half);
			divide(y + half, x + half, half);
		}
	}
}