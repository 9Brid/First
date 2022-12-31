package han04;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
皇宫各个宫殿的分布，呈一棵树的形状，宫殿可视为树中结点，两个宫殿之间如果存在道路直接相连，则该道路视为树中的一条边。
已知，在一个宫殿镇守的守卫不仅能够观察到本宫殿的状况，还能观察到与该宫殿直接存在道路相连的其他宫殿的状况。
大内保卫森严，三步一岗，五步一哨，每个宫殿都要有人全天候看守，在不同的宫殿安排看守所需的费用不同。
可是大内总管手上的经费不足，无论如何也没法在每个宫殿都安置留守侍卫。
帮助布置侍卫，在看守全部宫殿的前提下，使得花费的经费最少。

输入格式
输入中数据描述一棵树，描述如下：

第一行 n，表示树中结点的数目。
第二行至第 n+1 行，每行描述每个宫殿结点信息，依次为：该宫殿结点标号 i，在该宫殿安置侍卫所需的经费 k，该结点的子结点数 m，接下来 m 个数，分别是这个结点的 m 个子结点的标号 r1,r2,…,rm。
对于一个 n 个结点的树，结点标号在 1 到 n 之间，且标号不重复。

输出格式
输出一个整数，表示最少的经费。

数据范围
1≤n≤1500

给定一棵树，要在一些节点上放置守卫，每个守卫可以看护当前节点以及与此节点连通的节点，在不同节点放置守卫的代价不同，如何选取节点使代价最小，这是个典型的树形DP问题，
显然每个节点有放置守卫和不放置守卫两种，
但是从计算的过程看，不放置守卫的状态由两种，一种是有其父节点上的守卫看护，一种是由其子节点的守卫看护，
因此可将每个节点的看护情况分为三种：
该节点由父节点处放置的守卫看护 or由子节点处放置的守护看护 or该节点放置的守卫看护。

下面考虑状态转移的过程，建立数组dp[u][3]，其中：
dp[u][0]表示第u个节点由父节点处放置的守卫看护下的最小代价
dp[u][1]表示第u个节点由子节点处放置的守卫看护下的最小代价
dp[u][2]表示第u个节点由在该节点放置的守卫看护下的最小代价

那么可以写出转移关系：
dp[u][0] += min(dp[v][1], dp[v][2]);
dp[u][2] += min(min(dp[v][0], dp[v][1]), dp[v][2]);
dp[u][1] = min(dp[u][1], sum - min(dp[v][1], dp[v][2]) + dp[v][2]);
其中v为u的子节点，sum为所有子节点v的min(dp[v][1],dp[v][2])的和，1和2的意义很明显，3的意义代表，如果第u个节点由子节点守卫，那么所有子节点都不能由父节点守卫，并且每个子节点都得到了守卫（由其子结点 or
该结点守卫），且至少有一个子节点处放置了守卫。sum其实就等于dp[u][0]，不需要重新计算。
结果是min(dp[root][1], dp[root][2])，因为根节点没有父结点，不存在dp[root][0]。
*/
public class Q10_大内带刀侍卫 {
    static final int N = 1510, M = N, INF = Integer.MAX_VALUE;
    static int[] h = new int[N], dt = new int[M], nt = new int[M];
    static int idx, n;
    static int[] w = new int[N];
    static int[][] dp = new int[N][3];
    static boolean[] st = new boolean[N];

    static void add(int x, int y) {
        dt[idx] = y;
        nt[idx] = h[x];
        h[x] = idx++;
    }

    static void dfs(int u) {
        dp[u][2] = w[u];

        for (int i = h[u]; i != -1; i = nt[i]) {
            int v = dt[i];

            dfs(v);

            dp[u][0] += Math.min(dp[v][1], dp[v][2]);
            dp[u][2] += Math.min(Math.min(dp[v][0], dp[v][1]), dp[v][2]);
        }

        dp[u][1] = INF;
        for (int i = h[u]; i != -1; i = nt[i]) {
            int v = dt[i];

            dp[u][1] = Math.min(dp[u][1], dp[v][2] + dp[u][0] - Math.min(dp[v][1], dp[v][2]));
        }
    }

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han04/q10.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner stdin = new Scanner(in);
        n = stdin.nextInt();
        Arrays.fill(h, -1);
        for (int i = 1; i <= n; i++) {
            int x, y, z;
            x = stdin.nextInt();
            y = stdin.nextInt();
            z = stdin.nextInt();
            w[x] = y;

            while (z-- != 0) {
                int v;
                v = stdin.nextInt();
                add(x, v);
                st[v] = true;
            }
        }

        int root = 1;
        while (st[root]) root++;

        dfs(root);

        System.out.printf("%d\n", Math.min(dp[root][1], dp[root][2]));

    }
}
