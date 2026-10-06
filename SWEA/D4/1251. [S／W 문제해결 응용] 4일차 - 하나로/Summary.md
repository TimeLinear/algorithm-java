# 🤖 AI 분석

## 💡 접근 방식

모든 섬 쌍을 간선으로 만들고 거리의 제곱(long)을 가중치로 오름차순 정렬한 뒤 Kruskal 알고리즘으로 MST를 구합니다. 유니온 파인드는 음수 루트에 집합 크기를 저장하는 union by size와 경로 압축을 사용하고, N-1개 간선을 고르면 조기 종료합니다. 제곱 거리의 합에 환경 부담 세율 E를 곱해 Math.round로 반올림합니다.

## ⏱️ 시간 복잡도

O(N^2 log N) - 약 N^2/2개의 간선을 정렬하는 부분이 지배적이다.

## 📦 공간 복잡도

O(N^2) - 모든 간선 객체를 리스트에 저장한다.

## 🔧 개선 사항

로직은 정확합니다. 거리 제곱 합의 최대치는 약 1e15라서 double 정밀도 범위(약 9e15) 안이고, N=1인 경우도 문제없습니다. 다만 완전 그래프이므로 Prim O(N^2) 방식이 더 적합합니다. 간선 객체를 최대 약 50만 개 만들지 않아 메모리가 O(N)으로 줄고 정렬 비용도 없어집니다. Kruskal을 유지한다면 Edge 객체 대신 long 배열에 인코딩해 정렬하는 방법도 있습니다. 사소하게는 static 변수 대신 지역 변수를 쓰고, 출력은 print 대신 sb를 한 번에 출력하는 현재 방식을 유지하면 됩니다.

## 🎯 다음 추천 문제

SWEA에서 '최소 스패닝 트리' 키워드로 검색해 간선 수가 많은 희소 그래프 MST 문제(Kruskal 또는 우선순위 큐 Prim)를 풀어 보세요.

## 🏷️ 태그

mst, greedy, union-find, graph

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine().trim());
			long[] x = new long[n];
			long[] y = new long[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) x[i] = Long.parseLong(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) y[i] = Long.parseLong(st.nextToken());
			double e = Double.parseDouble(br.readLine().trim());

			long[] dist = new long[n];
			boolean[] visited = new boolean[n];
			Arrays.fill(dist, Long.MAX_VALUE);
			dist[0] = 0;
			long total = 0;

			for (int iter = 0; iter < n; iter++) {
				int u = -1;
				for (int i = 0; i < n; i++) {
					if (!visited[i] && (u == -1 || dist[i] < dist[u])) u = i;
				}
				visited[u] = true;
				total += dist[u];
				for (int v = 0; v < n; v++) {
					if (visited[v]) continue;
					long dx = x[u] - x[v];
					long dy = y[u] - y[v];
					long d = dx * dx + dy * dy;
					if (d < dist[v]) dist[v] = d;
				}
			}

			sb.append("#").append(tc).append(" ").append(Math.round(total * e)).append("\n");
		}
		System.out.print(sb);
	}
}
```
