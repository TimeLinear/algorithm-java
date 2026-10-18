import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Solution
{
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int n;
	static int[] nums, c;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		int T = nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			n = nextInt();
			nums = new int[n];
			c = new int[n];

			for(int i = 0; i < n; i++) {
				nums[i] = nextInt();
			}
			
			int size = 1;
			c[0] = nums[0];
			for(int i = 1; i < n; i++) {
				if (c[size - 1] < nums[i]) {
					c[size++] = nums[i];
				} else {
					int insertIdx = lowerBound(size - 1, nums[i]);
					c[insertIdx] = nums[i];
				}
			}
			sb.append('#').append(tc).append(' ').append(size).append('\n');
		}
		System.out.print(sb);
	}
	
	// 교체 대상 인덱스를 구하는 것. 추가 대상 인덱스 X
	static int lowerBound(int end, int target) {
		int cur = 0;
		while (cur < end) {
			int mid = (cur + end) / 2;
			if (c[mid] >= target) {
				end = mid;
			} else {
				cur = mid + 1;
			}
		}
		return end;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}