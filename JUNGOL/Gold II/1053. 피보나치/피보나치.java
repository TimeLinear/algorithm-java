import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
    static final int MOD = 10000;
	
	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			in.nextToken();
			int N = (int) in.nval;
			if (N == -1) break;
			
			sb.append(fiboMod(N)).append('\n');
		}
		System.out.println(sb);
	}
	
	static long[][] matpow(long[][] M, int n) {
		long[][] result = {{1, 0}, {0, 1}};
		long[][] base = {{M[0][0], M[0][1]}, {M[1][0], M[1][1]}};
		
		// 13 = 1101 (2진수) = 8 + 4 + 1
		// 행렬 M의 13제곱 = 행렬 M의 8제곱 * 행렬 M의 4제곱 * 행렬 M의 1제곱
		while(n > 0) {
			if ((n & 1) == 1) {
				result = matMulti(result, base);
			}
			base = matMulti(base, base);
			n >>= 1;
		}
		
		return result;
	}
	
	static long[][] matMulti(long[][] A, long[][] B) {
		long[][] C = new long[2][2];
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				long sum = 0;
				for(int k = 0; k < 2; k++) {
					sum += A[i][k] * B[k][j];
				}
				// 오버 플로우 방지
				C[i][j] = sum % MOD;
			}
		}
		return C;
	}
	
	static long fiboMod(int n) {
		long[][] M = {{1, 1}, {1, 0}};
		long[][] result = matpow(M, n);
		return result[0][1];
	}
}