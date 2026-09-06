import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			char[] c = br.readLine().toCharArray();
			int n = c.length;

			int cnt = 0, ans = 0;
			for (int i = 0; i < n; i++) {
				if (c[i] == '(') {
					cnt++;
				} else {
					cnt--;
					if (c[i - 1] == '(') {
						ans += cnt;
					} else {
						ans++;
					}
				}
			}

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		System.out.print(sb);
	}
}