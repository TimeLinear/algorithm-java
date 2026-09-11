import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    static int N, B;
    static int[] assistants = new int[20];
    static int minDiff = Integer.MAX_VALUE;
    
    private static void dfs(int n, int total) {
        
        if (total >= B) {
            minDiff = Math.min(minDiff, total - B);
            return;
        }
        
        if (n == N) return;
        
        dfs(n + 1, total + assistants[n]);
        
        dfs(n + 1, total);
    }
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
        
		for(int tc = 1; tc <= T; tc++)
		{
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            minDiff = Integer.MAX_VALUE;
            
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                assistants[i] = Integer.parseInt(st.nextToken());
            }
            
            //dfs(0, assistants[0]);
            dfs(0, 0);
            
            System.out.println("#" + tc + " " + minDiff);
		}
	}
}