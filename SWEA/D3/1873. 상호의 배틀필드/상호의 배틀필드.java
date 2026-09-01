import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    static final int dy[] = {-1, 1, 0, 0};
    static final int dx[] = {0, 0, -1, 1};
    static final char[] player = {'^', 'v', '<', '>'};
    
    static int H, W, N;
	static int cy = 0, cx = 0;
	static int dir = 0;
    
    static char[][] field = new char[20][20];
	static char[] cmds = new char[100];
    
    private static void shoot() {
        int dir = 0;
        char cur = field[cy][cx];
        
        switch(cur) {
            case '^' : dir = 0; break;
            case 'v' : dir = 1; break;
            case '<' : dir = 2; break;
            case '>' : dir = 3; break;
        }
         
        int ny = cy + dy[dir];
        int nx = cx + dx[dir];
         
        while(ny >= 0 && ny < H && nx >= 0 && nx < W) {
            if(field[ny][nx] == '*') {
                field[ny][nx] = '.';
                break;
            } else if(field[ny][nx] == '#') {
                break;
            }
             
            ny += dy[dir];
            nx += dx[dir];
        }
    }
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st;
        for(int tc = 1; tc <= T; tc++)
        {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            
            for(int i = 0; i < H; i++) {
                field[i] = br.readLine().toCharArray();
                for(int j = 0; j < W; j++) {
                    char c = field[i][j];
                    if (c == '^' || c == 'v' || c == '<' || c == '>') { 
                        cy = i; cx = j;
                    }
                }
            }
            
            N = Integer.parseInt(br.readLine());
            cmds = br.readLine().toCharArray();
            for(int i = 0; i < N; i++) {
                char cmd = cmds[i];
                
                switch(cmd) {
                    case 'U': 
                        dir = 0;
                        field[cy][cx] = player[dir];
                        break;
                    case 'D':
                        dir = 1;
                        field[cy][cx] = player[dir];
                        break;
                    case 'L':
                        dir = 2;
                        field[cy][cx] = player[dir];
                        break;
                    case 'R':
                        dir = 3;
                        field[cy][cx] = player[dir];
                        break;
                    case 'S':
                        shoot();
                        continue;
                }
                
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];
                
                if(ny >= 0 && ny < H && nx >= 0 && nx < W && field[ny][nx] == '.') {
					field[cy][cx] = '.';
                    cy = ny;
                    cx = nx;
                    field[cy][cx] = player[dir];
                }
            }
            
            sb.append("#").append(tc).append(" ");
            for(int i = 0; i < H; i++) {
                sb.append(field[i]).append("\n"); 
            }
        }
        System.out.print(sb);
	}
}