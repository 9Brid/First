package han03;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.setIn;

/*
从顶部出发，在每一结点可以选择移动至其左下方的结点或移动至其右下方的结点，
一直走到底层，要求找出一条路径，使路径上的数字的和最大。
        7
      3   8
    8   1   0
  2   7   4   4
4   5   2   6   5

输入格式
第一行包含整数n，表示数字三角形的层数。

接下来n行，每行包含若干整数，其中第 i 行表示数字三角形第 i 层包含的整数。

输出格式
输出一个整数，表示最大的路径数字和。

数据范围
1≤n≤500,
-10000≤三角形中的整数≤10000

dp[i][j]表示所有从起点走到（i,j）的路径的最大值，
根据左上方、右上方划分,
dp[i][j] = max(dp[i - 1][j - 1], dp[i - 1][j]) + a[i][j];

注意第一种初始化为负无穷大的方式，j<=i+1而不能写成j<=i，因为dp[3][3] = max(dp[2][2], dp[2][3])。
fill初始化一维数组dp[N]：    fill(dp, dp + N, -INF)
fill初始化二维数组dp[N][N]： fill(dp[0], dp[0] + N * N, -INF)
不能忘记dp[1][1] = a[1][1]，不是就会出错，其他dp值从第二行开始计算。
*/
public class Q03_数字三角形 {
    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han03/q03.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int[][] a = new int[510][510];
        int[][] f = new int[510][510];
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                a[i][j] = sc.nextInt();
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("%d ", a[i][j]);
            }
            System.out.println("");
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i + 1; j++) {
                f[i][j] = Integer.MIN_VALUE;
            }
        }
        f[1][1] = a[1][1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                f[i][j] = Math.max(f[i - 1][j - 1], f[i - 1][j]) + a[i][j];
                // 一定要注意，先取MAX，再求和
            }
        }
        int res = Integer.MIN_VALUE;
        for (int k = 1; k <= n; k++) {
            res = Math.max(res, f[n][k]);
        }
        System.out.println("---------------");
        System.out.println(res);
    }

}
