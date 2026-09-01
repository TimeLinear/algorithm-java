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
			
			for(int i = 0; i < N; i++) {
				in.nextToken();
				nums[tail++] = (int) in.nval;
			}
			
			int sub = 1;
			int cur, cycle;
			while(nums[(tail - 1) % N] != 0) {
				cur = nums[0];
				for(int i = 0; i < N; i++) {
					if(nums[i] < cur) cur = nums[i];
				}
				cycle = (cur - 1) / 15; // 0까지 다 빼지 말고 직전까지만 세기
				if(cycle > 0) {
					for(int i = 0; i < N; i++) {
						nums[i] -= 15 * cycle;
					}
					continue;
				}
				
				// cycle이 안나오는 경우 남은 스텝 진행
				cur = nums[head++ % N];
				cur -= sub;
				if (cur < 0) cur = 0;
				nums[tail++ % N] = cur;
				sub = (sub % 5) + 1;
			}
			
			sb.append('#').append(tc).append(' ');
			for(int i = 0; i < N; i++) {
				sb.append(nums[(head + i) % N]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}