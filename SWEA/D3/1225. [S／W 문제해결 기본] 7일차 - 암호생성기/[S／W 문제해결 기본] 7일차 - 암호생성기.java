import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

class Solution
{
	static final int N = 8;
	static int[] nums = new int[N];
	static int head, tail;
	
	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= 10; tc++) {
			
			if(in.nextToken() == StreamTokenizer.TT_EOF) break;
			
			Arrays.fill(nums, 0);
			head = 0;
			tail = 0;
			
			for(int i = 0; i < 8; i++) {
				in.nextToken();
				nums[tail++] = (int) in.nval;
			}
			
			int sub = 1;
			int cur;
			while(nums[(tail - 1) % 8] != 0) {
				cur = nums[head++ % 8];
				cur -= sub;
				if (cur < 0) cur = 0;
				nums[tail++ % 8] = cur;
				sub = (sub % 5) + 1;
			}
			
			sb.append('#').append(tc).append(' ');
			for(int i = 0; i < 8; i++) {
				sb.append(nums[(head + i) % 8]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}