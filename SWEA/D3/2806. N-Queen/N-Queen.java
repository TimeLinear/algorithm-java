import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution
{
	static int N, cnt, FULL;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());;
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			FULL = (1 << N) - 1;
            cnt = 0;
			
			setQueen(0, 0, 0, 0);
			sb.append('#').append(tc).append(' ').append(cnt).append('\n');
		}
		System.out.print(sb);
	}

	static void setQueen(int row, int col, int main, int sub) {

		if (row == N) {
			++cnt;
			return;
		}

		int available = FULL & ~(col | main | sub);
		
		while(available != 0) {
			int bit = available & (-available);
			available -= bit;
			
			setQueen(row + 1, col | bit, (main | bit) << 1, (sub | bit) >> 1);
		}
	}
}