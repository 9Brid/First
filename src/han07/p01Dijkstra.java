package han07;

//java代码虽然加了剪枝，但是spfa还是会有数据通不过，可以修改成优化过的dijkstra
/*
给定一个 n 个点 m 条边的有向图，图中可能存在重边和自环，所有边权均为正值。
请你求出 1 号点到 n 号点的最短距离，如果无法从 1 号点走到 n 号点，则输出 ?1。

输入格式
第一行包含整数 n 和 m。
接下来 m 行每行包含三个整数 x,y,z，表示存在一条从点 x 到点 y 的有向边，边长为 z。

输出格式
输出一个整数，表示 1 号点到 n 号点的最短距离。

如果路径不存在，则输出-1。

数据范围
1≤n≤500,
1≤m≤10^5,
图中涉及边长均不超过10000。

输入样例：
3 3
1 2 2
2 3 1
1 3 4
输出样例：
3

稠密图，朴素dijkstra。s为当前已确定最短路径的点。
最开始初始化除了起点的距离为0外，其他都设成无穷大，每次从还没有确定最短路的点中选取一个最小值的点，然后用这个点去更新其他点。
一次循环确定出一个点的最短路，循环n次就求出了所有点的最短路。
稠密图用邻接矩阵，稀疏图用邻接表。
很多点都跟起点不连通的时候会继续循环多次，首先先把他们当中最前面的那个设成最小的t，
设成已经访问，d[j]（0x3f3f3f3f）肯定要小于d[t]+g[t][j]（某一个正数 + 0x3f3f3f3f or 一个正数，故可以成功运行。

时间复杂度：O(n^2)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class p01Dijkstra {
	static int N = 50010;
	static int M = 100010 * 2;
	static int m;
	static int n;
	static int INF = 0x3f3f3f3f;
	static boolean[] st = new boolean[N];
	static int[][] dist = new int[6][N];
	static int[] id = new int[N];
	static int[] h = new int[N];
	static int[] e = new int[M];
	static int[] w = new int[M];
	static int[] ne = new int[M];
	static int idx = 0;
	static int ans = INF;

	static void add(int a, int b, int c) {
		e[idx] = b;
		w[idx] = c;
		ne[idx] = h[a];
		h[a] = idx++;
	}

	static void spfa(int start, int[] dist) {
		Queue<Integer> q = new LinkedList<Integer>();
		Arrays.fill(dist, INF);
		dist[start] = 0;
		q.add(start);
		st[start] = true;

		while (!q.isEmpty()) {
			int t = q.poll();
			st[t] = false;
			for (int i = h[t]; i != -1; i = ne[i]) {
				int j = e[i];
				if (dist[j] > dist[t] + w[i]) {
					dist[j] = dist[t] + w[i];
					if (!st[j]) {
						q.add(j);
						st[j] = true;
					}
				}
			}
		}
	}

	// u表示已经走了多少个点，start表示当前点，distance表示已走路程
	static void dfs(int u, int start, int distance) {
		// 最优性剪枝
		if (distance >= ans)
			return;
		if (u == 6) {
			ans = distance;
			return;
		}
		for (int i = 1; i < 6; i++) {
			if (st[i])
				continue;
			int next = id[i];
			st[i] = true;
			dfs(u + 1, i, distance + dist[start][next]);
			st[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s1 = br.readLine().split(" ");
		n = Integer.parseInt(s1[0]);
		m = Integer.parseInt(s1[1]);
		id[0] = 1;
		String[] s2 = br.readLine().split(" ");
		for (int i = 1; i <= 5; i++)
			id[i] = Integer.parseInt(s2[i - 1]);
		Arrays.fill(h, -1);
		while (m-- > 0) {
			String[] s3 = br.readLine().split(" ");
			int a = Integer.parseInt(s3[0]);
			int b = Integer.parseInt(s3[1]);
			int c = Integer.parseInt(s3[2]);
			add(a, b, c);
			add(b, a, c);
		}

		for (int i = 0; i < 6; i++) {
			spfa(id[i], dist[i]);
		}

		dfs(1, 0, 0);
		System.out.println(ans);
	}
}