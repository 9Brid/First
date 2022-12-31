package han03;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
班上同学安排坐成一个 m 行 n 列的矩阵，而云龙兄和云飞兄被安排在矩阵对角线的两端，因此，他们就无法直接交谈了。
幸运的是，他们可以通过传纸条来进行交流。
纸条要经由许多同学传到对方手里，云龙兄坐在矩阵的左上角，坐标(1,1)，云飞兄坐在矩阵的右下角，坐标(m,n)。
从云龙兄传到云飞兄的纸条只可以向下或者向右传递，从云飞兄传给云龙兄的纸条只可以向上或者向左传递。
在活动进行中，云龙兄希望给云飞兄传递一张纸条，同时希望云飞兄给他回复。
班里每个同学都可以帮他们传递，但只会帮他们一次，也就是说如果此人在云龙兄递给云飞兄纸条的时候帮忙，那么在云飞兄递给云龙兄的时候就不会再帮忙，反之亦然。
还有一件事情需要注意，全班每个同学愿意帮忙的好感度有高有低（注意：云龙兄和云飞兄的好心程度没有定义，输入时用0表示），可以用一个0-100的自然数来表示，数越大表示越好心。
云龙兄和云飞兄希望尽可能找好心程度高的同学来帮忙传纸条，即找到来回两条传递路径，使得这两条路径上同学的好心程度之和最大。
现在，请你帮助云龙兄和云飞兄找到这样的两条路径。

输入格式
第一行有2个用空格隔开的整数 m 和 n，表示学生矩阵有 m 行 n 列。
接下来的 m 行是一个 m*n 的矩阵，矩阵中第 i 行 j 列的整数表示坐在第 i 行 j 列的学生的好心程度，每行的 n 个整数之间用空格隔开。

输出格式
输出一个整数，表示来回两条路上参与传递纸条的学生的好心程度之和的最大值。

数据范围
1≤n,m≤50

思路与方格取数一模一样。

循环条件中考虑了i1、i2的范围后，就不需要再去判断y1=k-i1是否越界了。另外注意循环处理四种上上、上左、左上、左左的方式。
因为题目数据全是非负数，题目要求最大值，故可以使用dp[k][i1][i2]作为全局变量的默认初始化的值0，但是如果数字能取到负数且要求最大值，那么就需要把dp初始化成负无穷。
*/
public class Q13_传信息 {
    static int N = 55;
    static int[][][] f = new int[N * 2][N][N];
    static int[][] w = new int[N][N];

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han03/q13.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                w[i][j] = scan.nextInt();
            }
        }

        for (int k = 1 + 1; k <= n + m; k++) {
            for (int i1 = Math.max(1, k - m); i1 <= Math.min(n, k - 1); i1++) {
                for (int i2 = Math.max(1, k - m); i2 <= Math.min(n, k - 1); i2++) {
                    int t = w[i1][k - i1];
                    if (i1 != i2) t += w[i2][k - i2];//若路径重合
                    int x = f[k][i1][i2];
                    x = Math.max(x, f[k - 1][i1 - 1][i2 - 1] + t);
                    x = Math.max(x, f[k - 1][i1 - 1][i2] + t);
                    x = Math.max(x, f[k - 1][i1][i2 - 1] + t);
                    x = Math.max(x, f[k - 1][i1][i2] + t);
                    f[k][i1][i2] = x;
                }
            }
        }

        System.out.println(f[n + m][n][n]);
    }
}
