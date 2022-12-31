package han04;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.String.format;
import static java.lang.System.in;
import static java.lang.System.setIn;

/*
保护一座城市，这条城市的道路构成了一棵树。
每个节点上的士兵可以观察到所有和这个点相连的边。
他必须在节点上放置最少数量的士兵，以便他们可以观察到所有的边。
你能帮助他吗？


如图只需要放置1名士兵（在节点1处），就可观察到所有的边。

输入格式
输入包含多组测试数据，每组测试数据用以描述一棵树。
对于每组测试数据，第一行包含整数N，表示树的节点数目。
接下来N行，每行按如下方法描述一个节点。
节点编号：(子节点数目) 子节点 子节点 …
节点编号从0到N-1，每个节点的子节点数量均不超过10，每个边在输入数据中只出现一次。

输出格式
对于每组测试数据，输出一个占据一行的结果，表示最少需要的士兵数。

数据范围
0<N≤1500

树形dp，参考快乐指数，思路一模一样，并与其具有对称性
快乐指数：每条边上最多选择一个点，求最大权值
保家卫国：每条边上最少选择一条点，求最小权值

状态表示
f[u][0]：所有以u为根的子树中选择，并且不选u这个点的方案的最小值
f[u][1]：所有以u为根的子树中选择，并且选u这个点的方案的最小值

状态计算
当前u结点不选，子结点一定选
f[u][0]=∑(f[si,1])
当前u结点选，子结点一定可选可不选
f[u][1]=∑min(f[si,0],f[si,1])
时间复杂度 O(n)
注意多组数据的处理问题，在每组数据处理之前需要所有状态变量清零。格式化处理输入问题。

dp[u][0] = 0，表示u结点在不选的时候，不考虑其子树的情况下单独的u结点的士兵数量为0。
dp[u][1] = 1，表示u结点在选的时候，不考虑其子树的情况下单独的u结点的士兵数量为1。
*/
public class Q09_保家卫国 {
    static final int N = 1510, M = 1510;
    static int[] h = new int[N], dt = new int[M], wt = new int[M], nt = new int[M];
    static int idx, n;
    static int[][] dp = new int[N][2];
    static boolean[] st = new boolean[N];

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han04/q09.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner stdin = new Scanner(in);

        while (stdin.hasNext()) {
            n = stdin.nextInt();
            Arrays.fill(h, -1);
            idx = 0;
            Arrays.fill(st, false);
            for (int i = 1; i <= n; i++) {
                int x = 0, y = 0, z;
                String tmp[] = stdin.next().split(":");
                x = Integer.valueOf(tmp[0]);
                y = Integer.valueOf(tmp[1].substring(1, tmp[1].length() - 1));
                for (int j = 1; j <= y; j++) {
                    z = stdin.nextInt();
                    add(x, z);
                    st[z] = true;
                }
            }

            int root = 0;

            while (st[root]) root++;

            dfs(root);
            System.out.printf("%d\n", Math.min(dp[root][0], dp[root][1]));           //重点关注

        }
    }

    static void add(int x, int y) {
        dt[idx] = y;
        nt[idx] = h[x];
        h[x] = idx++;
    }

    static void dfs(int u) {
        dp[u][0] = 0;
        dp[u][1] = 1;

        for (int i = h[u]; i != -1; i = nt[i]) {
            int v = dt[i];

            dfs(v);

            dp[u][0] += dp[v][1];
            dp[u][1] += Math.min(dp[v][0], dp[v][1]);
        }
    }
}
