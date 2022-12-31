package han02;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
给定n*m小的棋盘，以及马的初始位置(x，y)，要求不能重复经过棋盘上的同一个点，计算马可以有多少途径遍历棋盘上的所有点。
输入格式
第一行为整数T，表示测试数据组数。
每一组测试数据包含一行，为四个整数，分别为棋盘的大小以及初始位置坐标n,m,x,y。
输出格式
每组测试数据包含一行，为一个整数，表示马能遍历棋盘的途径总数，若无法遍历棋盘上的所有点则输出 0。
数据范围
1≤T≤9,
1≤m,n≤9,
0≤x≤n-1,
0≤y≤m-1

这个题目不好用bfs实现。
注意dfs中的现场保护、现场恢复，也可以在for循环dfs()的前后进行现场保护、现场恢复，
但是这样也需要在主函数中调用dfs的前后进行现场保护、现场恢复。
多次调用，需要把全局变量、状态数组等清零!
memset(vis, false, sizeof(vis))可以不需要，因为现场恢复后vis状态数组会清零，保证了在处理下一组测试数据的时候不会出错。
*/
public class q05 {

	static int N = 15;

	static boolean[][] vis = new boolean[N][N];

	static int T;
	static int n, m, x, y;
	static int num;

	static int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };

	public static void main(String[] args) throws FileNotFoundException {
		// 输入重定向
		BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han02/05.txt"));
		System.setIn(in);
		Scanner stdin = new Scanner(System.in);

		T = stdin.nextInt();

		while (T-- != 0) {
			n = stdin.nextInt();
			m = stdin.nextInt();
			x = stdin.nextInt();
			y = stdin.nextInt();
			num = 0;
			dfs(x, y, 1);
			System.out.println(num);
		}

		stdin.close();
	}

	private static void dfs(int x, int y, int t) {
		if (t == n * m) { // 遍历完所有棋盘格子时方案数+1
			num++;
			return;
		}

		vis[x][y] = true;

		for (int i = 0; i < 8; i++) { // 分别从8个方向进行扩展
			int newx = x + dx[i];
			int newy = y + dy[i];

			if (newx < 0 || newx >= n || newy < 0 || newy >= m)
				continue;
			if (vis[newx][newy])
				continue;

			dfs(newx, newy, t + 1); // (newx, newy)是马上去遍历的第t+1个棋盘格子
		}
		vis[x][y] = false; // 现场恢复

	}

}
