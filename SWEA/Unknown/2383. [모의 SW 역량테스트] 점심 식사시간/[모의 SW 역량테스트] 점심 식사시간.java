import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;

class Solution
{
	static int N;
	static int[][] map;
	static ArrayList<int[]> people;
	static ArrayList<int[]> stairs;
	static int[] moveTime1, moveTime2;
	static int result;
	
	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuilder sb = new StringBuilder();
		
		in.nextToken();
		int T = (int) in.nval;
		
		map = new int[10][10];
		people = new ArrayList<>();
		stairs = new ArrayList<>(2); // y, x, 시간
		moveTime1 = new int[10];
		moveTime2 = new int[10];
		
		for(int tc = 1; tc <= T; tc++) {
			in.nextToken();
			N = (int) in.nval;
			
			people.clear();
			stairs.clear();
			
			result = Integer.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					in.nextToken();
					map[i][j] = (int) in.nval;
					if (map[i][j] == 1) {
						people.add(new int []{i, j});
					} else if (map[i][j] >= 2) {
						stairs.add(new int[] {i, j, map[i][j]});
					}
				}
			}
			
			// 사람 별로 1번 계단, 2번 계단 도착 시간 계산
			// 단, 계단 이동 시간은 합치지 않음. 도착한다고 이동 가능하단 보장이 없으므로 두 시간을 나눠놓기
			calcMoveTime();
			
			int comboCount = 1 << people.size();
			for (int flag = 0; flag < comboCount; flag++) {
				result = Math.min(result, scheduling(flag));
			}
			
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	static void calcMoveTime() {
		int[] stair1 = stairs.get(0);
		int[] stair2 = stairs.get(1);
		for(int i = 0; i < people.size(); i++) {
			int[] pPos = people.get(i);
			// 사람 전원의 첫번째 계단으로 이동 시간 계산
			moveTime1[i] = Math.abs(pPos[0] - stair1[0]) + Math.abs(pPos[1] - stair1[1]);
			// 사람 전원의 두번째 계단으로 이동 시간 계산
			moveTime2[i] = Math.abs(pPos[0] - stair2[0]) + Math.abs(pPos[1] - stair2[1]);
		}
	}
	
	static int scheduling(int flag) {
		int size2 = Integer.bitCount(flag); // 2번 계단 배정 인원
		int size1 = people.size() - size2; // 1번 계단 배정 인원
		ArrayList<Integer> schedule1 = new ArrayList<>(size1);
		ArrayList<Integer> schedule2 = new ArrayList<>(size2);
		
		for(int i = 0; i < people.size(); i++) {
			if ((flag & (1 << i)) == 0) {
				schedule1.add(moveTime1[i] + 1);
			} else {
				schedule2.add(moveTime2[i] + 1);
			}
		}
		
		schedule1.sort(Comparator.naturalOrder());
		schedule2.sort(Comparator.naturalOrder());
		
		// 다른 건 다 했는데 이거만 못했음...
		int sum1 = calcStairTime(schedule1, stairs.get(0)[2]);
		int sum2 = calcStairTime(schedule2, stairs.get(1)[2]);
		
		return Math.max(sum1, sum2);
	}
	
	static int calcStairTime(ArrayList<Integer> arrivals, int climbTime) {
	    ArrayDeque<Integer> freeAt = new ArrayDeque<>(); // 계단이 비는 시각, 동시 최대 3명
	    int maxFinish = 0;
	    for (int arrival : arrivals) {
	        int start;
	        if (freeAt.size() < 3) {
	            start = arrival;                 // 자리 있으면 즉시 오르기 시작
	        } else {
	            int earliestFree = freeAt.poll(); // 3명 다 차있으면 제일 먼저 빈 자리를 기다림
	            start = Math.max(arrival, earliestFree);
	        }
	        int finish = start + climbTime;       // 이 계단은 오르는 데 climbTime초
	        freeAt.add(finish);
	        maxFinish = Math.max(maxFinish, finish);
	    }
	    return maxFinish;
	}
}