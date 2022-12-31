package han04;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
将 n 堆石子圆形排放，现要将石子有序地合并成一堆。
规定每次只能选相邻的两堆合并成新的一堆，并将新的一堆的石子数记做该次合并的得分。
请编写一个程序，读入堆数 n 及每堆的石子数，并进行如下计算：
选择一种合并石子的方案，使得做 n-1 次合并得分总和最大。
选择一种合并石子的方案，使得做 n-1 次合并得分总和最小。
输入格式
第一行包含整数 n，表示共有 n 堆石子。
第二行包含 n 个整数，分别表示每堆石子的数量。

输出格式
输出共两行：

第一行为合并得分总和最小值，
第二行为合并得分总和最大值。

数据范围
1≤n≤200

思路与合并石头堆儿相同，只是这里需要将环形dp转换成区间dp，方式就是a1~an后面再接上a1~an即可。
该题也可以讨论合并时的唯一缺口的位置，只是时间复杂度为O(n^4)，会超时。这里转化成区间dp后时间复杂度为O(n^3
*/
public class Q05_合并环形石子堆儿 {
    static final int N = 410, INF = Integer.MAX_VALUE;
    static int[] a = new int[N], s = new int[N];
    static int[][] dp1 = new int[N][N], dp2 = new int[N][N];

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han04/q05.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner stdin = new Scanner(in);

        int n = stdin.nextInt();

        for (int i = 1; i <= n; i++) {
            a[i] = stdin.nextInt();
            a[n + i] = a[i];
        }

        for (int i = 1; i <= 2 * n; i++) {
            s[i] = s[i - 1] + a[i];
        }

        for (int i = 0; i < dp1.length; i++) {
            Arrays.fill(dp1[i], Integer.MAX_VALUE);
            Arrays.fill(dp2[i], Integer.MIN_VALUE);
        }


        for (int len = 1; len <= n; len++) {
            //重点关注，之前len从2开始，为何这里却改成了从1开始？
            for (int l = 1; l + len - 1 <= 2 * n; l++) {
                int r = l + len - 1;
                if (len == 1) {
                    //边界
                    dp1[l][r] = dp2[l][r] = 0;
                } else {
                    for (int k = l; k < r; k++) {
                        //状态转移方程
                        dp1[l][r] = Math.min(dp1[l][r], dp1[l][k] + dp1[k + 1][r] + s[r] - s[l - 1]);
                        dp2[l][r] = Math.max(dp2[l][r], dp2[l][k] + dp2[k + 1][r] + s[r] - s[l - 1]);
                        System.out.printf("%2d %2d %2d %2d %2d %2d\n", len, l, k, r, dp1[l][r], dp2[l][r]);
                    }
                }
            }
        }

        int ans1 = INF, ans2 = -INF;
        for (int l = 1; l <= n; l++) {
            ans1 = Math.min(ans1, dp1[l][l + n - 1]);                                                       //重点关注，l + n - 1
            ans2 = Math.max(ans2, dp2[l][l + n - 1]);
        }

        System.out.printf("%d\n%d\n", ans1, ans2);

    }

}
