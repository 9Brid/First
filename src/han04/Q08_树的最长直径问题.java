package han04;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
给定一棵树，树中包含 n 个结点（编号1~n）和 n-1 条无向边，每条边都有一个权值。
现在请你找到树中的一条最长路径。
换句话说，要找到一条路径，使得路径两端的点的距离最远。
注意：路径中可以只包含一个点。

输入格式
第一行包含整数 n。
接下来 n-1 行，每行包含三个整数 ai,bi,ci，表示点 ai 和 bi 之间存在一条权值为 ci 的边。

输出格式
输出一个整数，表示树的最长路径的长度。

数据范围
1≤n≤10000,
1≤ai,bi≤n,
-10^5≤ci≤10^5

求树的直径，
方法是先从任意一个结点作为起点（起点不一定是编号为1的点）出发去遍历求出距离起点最远的点，
然后再以该点为起点，求出距其最大的点即为所有的点，要求最远距离直接读dis[]就行了。
dfs(1, -1, 0)表示从1号结点出来开始dfs遍历，其父结点编号是-1，1号结点距起点1结点距离为0。
遍历vector可以用for(int i = 0; i<v.size(); i++)，也可以用for(auto u : v)。
*/
public class Q08_树的最长直径问题 {
    static final int N = 10010, M = 2 * N;
    static int[] h = new int[N], dt = new int[N], wt = new int[N], nt = new int[N], dist = new int[N];
    static int idx, n;

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han04/q08.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner stdin = new Scanner(in);
        n = stdin.nextInt();
        Arrays.fill(h, -1);
        int x, y, w;
        for (int i = 1; i < n; i++) {
            x = stdin.nextInt();
            y = stdin.nextInt();
            w = stdin.nextInt();
            add(x, y, w);
            add(y, x, w);
        }
        dfs(1, -1, 0);
        int id = 1;
        for (int i = 1; i <= n; i++) {
            if (dist[i] > dist[id]) {
                id = i;
            }
        }

        dfs(id, -1, 0);                                   //结点id距起点最远
        for (int i = 1; i <= n; i++) {
            if (dist[i] > dist[id]) {
                id = i;
            }
        }

        System.out.printf("%d\n", dist[id]);
    }

    private static void dfs(int x, int y, int w) {
        dt[idx] = y;
        wt[idx] = w;
        nt[idx] = h[x];
        h[x] = idx++;
    }

    private static void add(int u, int fa, int len) {
        dist[u] = len;

        for (int i = h[u]; i != -1; i = nt[i]) {
            int v = dt[i];

            if (v != fa) {//避免往回遍历父结点
                dfs(v, u, len + wt[i]); //结点v到起点的距离 = 结点u距起点的距离 + 结点v距结点u的距离
            }
        }
    }
}
