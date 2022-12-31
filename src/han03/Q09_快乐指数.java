package han03;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;   //所有的import用一个import java.util.*;替代
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
有N名员工，编号为1~N。
他们的关系就像一棵以董事长为根的树，父节点就是子节点的直接上司。
每个员工有一个快乐指数，用整数 Hi 给出，其中 1≤i≤N。
现在要召开一场宴会，不过，没有职员愿意和直接上司一起参会。
在满足这个条件的前提下，主办方希望邀请一部分员工参会，使得所有参会员工的快乐指数总和最大，求这个最大值。

输入格式
第一行一个整数N。
接下来N行，第 i 行表示 i 号员工的快乐指数Hi。
接下来N-1行，每行输入一对整数L, K,表示K是L的直接上司。

输出格式
输出最大的快乐指数。

数据范围
1≤N≤6000,
-128≤Hi≤127

dp[u][0]、dp[u][1]表示以u为根的子树在不选u时的快乐指数最大值，以u为根的子树在选u时的快乐指数最大值。
初始化：
dp[u][0] = 0，   表示u结点在不选的时候，不考虑其子树的情况下单独的u结点的快乐指数为0。
dp[u][1] = w[u]，表示u结点在选的时候，不考虑其子树的情况下单独的u结点的快乐指数为w[u]。

           5
         /   \
		3     4
	   / \   / \
	  1  2  6   7
*/
public class Q09_快乐指数 {
    static int N = 6010;
    static int[] happy = new int[N];
    static int[] h = new int[N];
    static int[] e = new int[N];
    static int[] ne = new int[N];
    static int idx = 0;
    static int[][] f = new int[N][2];
    static boolean[] has_fa = new boolean[N];

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static void dfs(int u) {
        f[u][1] = happy[u];
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            dfs(j);

            f[u][1] += f[j][0];
            f[u][0] += Math.max(f[j][1], f[j][0]);
        }
    }

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han03/q09.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(in);
        int n = scan.nextInt();
        for (int i = 1; i <= n; i++) happy[i] = scan.nextInt();
        Arrays.fill(h, -1);
        for (int i = 0; i < n - 1; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            add(b, a);
            has_fa[a] = true;
        }
        int root = 1;
        while (has_fa[root]) root++;
        dfs(root);

        System.out.println(Math.max(f[root][0], f[root][1]));
    }
}
