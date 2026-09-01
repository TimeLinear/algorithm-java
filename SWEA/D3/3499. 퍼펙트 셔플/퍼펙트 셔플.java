import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	static int N, half;
	static String[] card;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
            half = (N + 1) / 2;
            
			card = new String[half];
			
			sb.append('#').append(tc);
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < half; i++) {
				card[i] = st.nextToken();
			}
            
            for(int i = 0; i < half; i++) {
                sb.append(' ').append(card[i]);
                if (i < N - half) {
                    sb.append(' ').append(st.nextToken());
                }
            }
			sb.append('\n');
		}
		System.out.print(sb);
	}
}