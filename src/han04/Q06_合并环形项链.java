package han04;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
在Mars星球上，每个Mars人都随身佩带着一串能量项链，在项链上有 N 颗能量珠。
能量珠是一颗有头标记与尾标记的珠子，这些标记对应着某个正整数。
并且，对于相邻的两颗珠子，前一颗珠子的尾标记一定等于后一颗珠子的头标记。
因为只有这样，通过吸盘（吸盘是Mars人吸收能量的一种器官）的作用，这两颗珠子才能聚合成一颗珠子，同时释放出可以被吸盘吸收的能量。
如果前一颗能量珠的头标记为m，尾标记为r，后一颗能量珠的头标记为 r，尾标记为 n，则聚合后释放的能量为 m*r*n（Mars单位），新产生的珠子的头标记为 m，尾标记为 n。
需要时，Mars人就用吸盘夹住相邻的两颗珠子，通过聚合得到能量，直到项链上只剩下一颗珠子为止。
显然，不同的聚合顺序得到的总能量是不同的，请你设计一个聚合顺序，使一串项链释放出的总能量最大。
例如：设N=4，4颗珠子的头标记与尾标记依次为(2，3) (3，5) (5，10) (10，2)。
我们用记号⊕表示两颗珠子的聚合操作，(j⊕k)表示第 j，k 两颗珠子聚合后所释放的能量。则
第4、1两颗珠子聚合后释放的能量为：(4⊕1)=10*2*3=60。
这一串项链可以得到最优值的一个聚合顺序所释放的总能量为((4⊕1)⊕2)⊕3）= 10*2*3+10*3*5+10*5*10=710。
输入格式
输入的第一行是一个正整数 N，表示项链上珠子的个数。
第二行是N个用空格隔开的正整数，所有的数均不超过1000，第 i 个数为第 i 颗珠子的头标记，当i<N时，第 i 颗珠子的尾标记应该等于第 i+1 颗珠子的头标记，第 N 颗珠子的尾标记应该等于第1颗珠子的头标记。
至于珠子的顺序，你可以这样确定：将项链放到桌面上，不要出现交叉，随意指定第一颗珠子，然后按顺时针方向确定其他珠子的顺序。
输出格式
输出只有一行，是一个正整数 E，为一个最优聚合顺序所释放的总能量。
数据范围
4≤N≤100,
1≤E≤2.1*10^9

注意变量的起止范围，len：3~n+1，k：l+1~r-1，dp[l][r]=dp[l][k]+dp[k][r]+a[l]*a[k]*a[r]，最后取最大值的时候是对长度为n+1的区间求的。注意dp[][]的默认初始化。
*/
public class Q06_合并环形项链 {
    static final int N = 210;
    static int[] a = new int[N];
    static int[][] dp = new int[N][N];

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han04/q06.txt"));
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

        //重点关注，len起始值
        for (int len = 3; len <= n + 1; len++) {
            for (int l = 1; l + len - 1 <= 2 * n; l++) {
                int r = l + len - 1;
                //重点关注，k起始值
                for (int k = l + 1; k < r; k++) {
                    //重点关注，状态转移方程
                    dp[l][r] = Math.max(dp[l][r], dp[l][k] + dp[k][r] + a[l] * a[k] * a[r]);
                    System.out.printf("%d %d %d %d %3d %3d %2d*%2d*%2d %3d\n", len, l, k, r, dp[l][k], dp[k][r], a[l], a[k], a[r], dp[l][r]);
                }
            }
        }
        int ans = 0;
        for (int l = 1; l <= n; l++) {
            ans = Math.max(ans, dp[l][l + n]);                                                          //重点关注
        }

        System.out.printf("%d\n", ans);
    }
}
