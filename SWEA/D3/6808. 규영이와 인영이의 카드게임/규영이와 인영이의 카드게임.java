import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Solution
{
    static final int MAX_SCORE = 171;
    static final int CARD_NUM = 9;
    static int[] gCard = new int[CARD_NUM + 1];
    static int[] iCard = new int[CARD_NUM + 1];
    static int[] factorials = new int[CARD_NUM + 1];
    static int gCheck;
    static int[][] memoization;
    
    private static int dfs(int gScore, int iScore, int depth, int visited) {
        int half = ((MAX_SCORE + 1) / 2);
        
        if (memoization[gScore][visited] != -1) {
            return memoization[gScore][visited];
        }
        
        if (gScore >= half) {
            int n = 9 - depth;
            return factorials[n];
        }
        if (iScore >= half) {
            return 0;
        }
        
        int winCnt = 0;
        
        for (int i = 0; i < CARD_NUM; i++) {
            if ((visited & (1 << i)) != 0) continue;
            
            int g = gCard[depth];
            int nextVisited = visited | (1 << i);
            
            if (g > iCard[i]) {
                winCnt += dfs(gScore + g + iCard[i], iScore, depth + 1, nextVisited);
            } else {
                winCnt += dfs(gScore, iScore + g + iCard[i], depth + 1, nextVisited);
            }
        }
        
        return memoization[gScore][visited] = winCnt;
    }
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        factorials[0] = 1;
        for(int i = 1; i < CARD_NUM + 1; i++) {
            factorials[i] = factorials[i - 1] * i;
        }
        
        memoization = new int[MAX_SCORE + 1][1 << CARD_NUM];
        
		for(int tc = 1; tc <= T; tc++)
		{
            st = new StringTokenizer(br.readLine());
            gCheck = 0;
            
            for (int i = 0; i <= MAX_SCORE; i++) {
                Arrays.fill(memoization[i], -1);
            }
            
            for(int i = 0; i < CARD_NUM; i++) {
                gCard[i] = Integer.parseInt(st.nextToken());
                gCheck |= (1 << gCard[i]);
            }
            
            int idx = 0;
            for(int i = 1; i <= CARD_NUM * 2; i++) {
                if((gCheck & (1 << i)) == 0) {
                    iCard[idx++] = i;
                }
            }
            
            int winCnt = dfs(0, 0, 0, 0);
            
            sb.append("#").append(tc).append(" ").append(winCnt).append(" ").append(factorials[CARD_NUM] - winCnt).append("\n");
        }
        System.out.println(sb);
	}
}