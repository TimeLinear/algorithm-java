import java.util.Scanner;

class Solution
{
	static int N, E;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int tc = 1; tc <= 10; tc++) {
			sc.nextInt();
			
			N = sc.nextInt();
			E = sc.nextInt();
			
			int result = 1;
			for(int i = 0; i < E; i++) {
				result *= N;
			}
			System.out.printf("#%d %d%n", tc, result);
		}
	}
}