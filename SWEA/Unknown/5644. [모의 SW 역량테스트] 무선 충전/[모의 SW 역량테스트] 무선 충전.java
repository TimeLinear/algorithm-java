import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Solution
{
	static final int[] DY = { 0, -1, 0, 1, 0 };
	static final int[] DX = { 0, 0, 1, 0, -1 };
	static final int MAX_MOVE = 100;
	static final int MAX_BATTERY = 8;
	static int M, A;
	static int[] moveA;
	static int[] moveB;
	static int[] batteryPower;
	static int[][] bMap;
	static int[] uA, uB; // A와 B 사용자 상태

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuilder sb = new StringBuilder();

		in.nextToken();
		int T = (int) in.nval;

		moveA = new int[MAX_MOVE];
		moveB = new int[MAX_MOVE];
		batteryPower = new int[MAX_BATTERY]; // 좌표(X, Y), 충전 범위(C), 처리량(P) / !! X, Y 순으로 입력됨에 주의
		bMap = new int[11][11];

		uA = new int[2];
		uB = new int[2];
		for (int tc = 1; tc <= T; tc++) {
			in.nextToken();
			M = (int) in.nval; // 이동 시간
			in.nextToken();
			A = (int) in.nval; // 배터리 개수
            
            for(int i = 0; i < 11; i++) {
				for(int j = 0; j < 11; j++) {
					bMap[i][j] = 0;
				}
			}

			// 사용자 정보 초기화
			uA[0] = 1;
			uA[1] = 1;
			uB[0] = 10;
			uB[1] = 10;

			// 이동 입력
			for (int i = 0; i < M; i++) {
				in.nextToken();
				moveA[i] = (int) in.nval;
			}

			for (int i = 0; i < M; i++) {
				in.nextToken();
				moveB[i] = (int) in.nval;
			}

			// 배터리 입력 즉시 비트 마스킹 맵으로 전환
			int bX, bY, bC, bP;
			for (int i = 0; i < A; i++) {
				in.nextToken();
				bX = (int) in.nval;
				in.nextToken();
				bY = (int) in.nval;
				in.nextToken();
				bC = (int) in.nval;
				in.nextToken();
				bP = (int) in.nval;

				batteryPower[i] = bP;

				// 배터리 영역 boolean 맵 만들기
				setBatteryMap(bY, bX, bC, i);
			}

			int result = 0;
			for (int i = 0; i <= M; i++) {
				if (i > 0) {
					uA[0] += DY[moveA[i - 1]];
					uA[1] += DX[moveA[i - 1]];
					uB[0] += DY[moveB[i - 1]];
					uB[1] += DX[moveB[i - 1]];
				}
				int uAB = bMap[uA[0]][uA[1]];
				int uBB = bMap[uB[0]][uB[1]];

				int idxA = getMaxIdx(uAB);
				int idxB = getMaxIdx(uBB);
				int powA = idxA == -1 ? 0 : batteryPower[idxA];
				int powB = idxB == -1 ? 0 : batteryPower[idxB];

				if (idxA != -1 && idxA == idxB) {
					result += powA;
					int restA = uAB & ~(1 << idxA);
					int restB = uBB & ~(1 << idxB);
					if (restA != 0 && restB != 0) {
						result += Math.max(getMaxPower(restA), getMaxPower(restB));
					} else if (restA != 0) {
						result += getMaxPower(restA);
					} else if (restB != 0) {
						result += getMaxPower(restB);
					}
				} else {
					result += powA + powB;
				}
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}
	
	static void setBatteryMap(int bY, int bX, int bC, int cnt) {
		int sX = (bX - bC) < 0 ? 0 : bX - bC;
		int eX = (bX + bC) >= 10 ? 10 : bX + bC;
		int sY = (bY - bC) < 0 ? 0 : bY - bC;
		int eY = (bY + bC) >= 10 ? 10 : bY + bC;

		for (int j = sY; j <= eY; j++) {
			for (int k = sX; k <= eX; k++) {
				int dist = Math.abs(bY - j) + Math.abs(bX - k);
				if (dist <= bC) {
					bMap[j][k] |= (1 << cnt);
				}
			}
		}
	}
	
	static int getMaxPower(int userBatteryBit) {
        int batIdx, batPower, maxPower = 0;
        while (userBatteryBit != 0) {
            batIdx = Integer.numberOfTrailingZeros(userBatteryBit);
            if (batIdx != 32) {
                batPower = batteryPower[batIdx];
                maxPower = Math.max(maxPower, batPower);
            }
            userBatteryBit &= ~(1 << batIdx);
        }
        return maxPower;
    }
	
	static int getMaxIdx(int mask) {
		int idx = -1, maxP = -1;
		while (mask != 0) {
			int i = Integer.numberOfTrailingZeros(mask);
			if (batteryPower[i] > maxP) { maxP = batteryPower[i]; idx = i; }
			mask &= mask - 1;
		}
		return idx;
	}
}