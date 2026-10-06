# 🤖 AI 분석

## 💡 접근 방식

크루스칼 알고리즘으로 푼 코드입니다. 모든 간선을 가중치 오름차순으로 정렬한 뒤, 유니온 파인드(경로 압축)로 두 정점이 서로 다른 집합일 때만 간선을 선택해 가중치를 누적합니다. 합계는 long으로 저장해 오버플로를 피했고, 테스트케이스마다 parents와 edges를 다시 초기화합니다.

## ⏱️ 시간 복잡도

O(E log E) - 간선 정렬이 지배적이고, 유니온 파인드 연산은 거의 상수 시간에 가깝다.

## 📦 공간 복잡도

O(V + E) - parents 배열과 간선 리스트를 저장한다.

## 🔧 개선 사항

1) 가장 중요한 점은 find가 재귀이고 union by rank/size가 없다는 것입니다. 간선이 (1,2),(2,3),... 순서로 처리되면 parents가 길이 V(최대 100,000)의 체인이 되고, 첫 find 호출의 재귀 깊이가 커져 StackOverflowError가 날 수 있습니다. find를 반복문(경로 반분)으로 바꾸거나 union by size를 추가하는 것이 안전합니다. 2) 선택한 간선이 V-1개가 되면 반복을 종료해 불필요한 find 호출을 줄일 수 있습니다. 3) List<int[]> 대신 int[][]와 Arrays.sort를 쓰면 박싱·리스트 오버헤드가 줄어듭니다. 4) 비교자 a[2]-b[2]는 가중치 범위(±1,000,000)에서는 오버플로가 없지만 Integer.compare가 습관상 더 안전합니다. 5) V, E, result 같은 값은 static이 아니라 지역 변수로 두면 더 깔끔합니다. 알고리즘 자체는 올바릅니다.

## 🎯 다음 추천 문제

SWEA 1251 - 하나로 (D4). 좌표로 간선 비용을 직접 계산해야 하는 MST 문제이며, 프림과 크루스칼을 모두 연습하기 좋습니다.

## 🏷️ 태그

mst, kruskal, union-find, graph

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			parents = new int[V + 1];
			for (int i = 1; i <= V; i++) {
				parents[i] = i;
			}

			int[][] edges = new int[E][];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int fv = Integer.parseInt(st.nextToken());
				int sv = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				edges[i] = new int[] {fv, sv, w};
			}

			Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

			long result = 0;
			int cnt = 0;
			for (int[] edge : edges) {
				int x = find(edge[0]);
				int y = find(edge[1]);
				if (x != y) {
					parents[x] = y;
					result += edge[2];
					if (++cnt == V - 1) break;
				}
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}

	static int find(int x) {
		while (parents[x] != x) {
			parents[x] = parents[parents[x]];
			x = parents[x];
		}
		return x;
	}
}

```
