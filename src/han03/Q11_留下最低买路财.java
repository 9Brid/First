package han03;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
一个商人穿过一个N×N的正方形的网格，去参加一个非常重要的商务活动。
他要从网格的左上角进，右下角出。
每穿越中间1个小方格，都要花费1个单位时间。
商人必须在(2N-1)个单位时间穿越出去。
而在经过中间的每个小方格时，都需要缴纳一定的费用。
这个商人期望在规定时间内用最少费用穿越出去。
请问至少需要多少费用？
注意：不能对角穿越各个小方格（即，只能向上下左右四个方向移动且不能离开网格）。
输入格式
第一行是一个整数，表示正方形的宽度N。
后面N行，每行N个不大于100的整数，为网格上每个小方格的费用。
输出格式
输出一个整数，表示至少需要的费用。
数据范围
1≤N≤100
输入样例：
5
1  4  6  8  10
2  5  7  15 17
6  8  9  18 20
10 11 12 19 21
20 23 25 29 33
输出样例：
109
样例解释
样例中，最小值为109=1+2+5+7+9+12+19+21+33。

先走到第1个格子花费1个单位时间，再从左到右需花费n-1，再从上到下需花费n-1，总共为1+2*(n-1)=2n-1，
题目的意思也就是不能走回头路，要么往右走要么往下走，跟摘草莓一模一样，只是这里求的是最小值而非最大值，需特殊处理边界问题，并将所有元素都要初始化成正无穷大INF。
dp[1][1]=a[1][1]，特殊处理。
对于第1行的其他值都是在if(j>1)处赋的值（仅从左往右算），对于第1列的其他值都是在if(i>1)处赋的值（仅从上往下算），
对于不在第1行或第1列的值这两处代码会相继跑到（即先从上往下算，再从左往右算），并求出这两处的最小值。
*/
public class Q11_留下最低买路财 {
    static int n = 0, N = 110;
    static int[][] a = new int[N][N];
    static int[][] dp = new int[N][N];

    public static void main(String[] args) throws Exception {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han03/q11.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner s = new Scanner(in);
        n = s.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                a[i][j] = s.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {                                               //重点关注，二维数组初始化，要从下标0开始，因为后面的状态转移方程会用到下标为0的量
            Arrays.fill(dp[i], 0x3f3f3f3f);
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (i == 1 && j == 1) {
                    dp[i][j] = a[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + a[i][j];
                }
            }
        }
        System.out.print(dp[n][n]);
    }
}
