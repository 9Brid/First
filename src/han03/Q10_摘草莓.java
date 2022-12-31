package han03;

import java.io.*;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
矩形草莓地，从左上角进去，右下角出来。
地里每个道路的交叉点上都有种着一株草莓苗，上面有若干颗草莓，经过一株草莓苗就能摘走该它上面所有的草莓。
能向东或向南走，不能向西或向北走。
问最多能够摘到多少颗草莓。

输入格式
第一行是一个整数T，代表一共有多少组数据。
接下来是T组数据。
每组数据的第一行是两个整数，分别代表草莓苗的行数R和列数 C。
每组数据的接下来R行数据，从北向南依次描述每行草莓苗的情况。每行数据有C个整数，按从西向东的顺序描述了该行每株草莓苗上的草莓数目M。

输出格式
对每组输入数据，输出一行，能摘到得最多的草莓颗数。

数据范围
1≤T≤100,
1≤R,C≤100,
0≤M≤1000

dp[i][j]表示的是所有从(1, 1)起点到(i, j)点的所有路径上的花生数量的最大值。
公式：dp[i][j] = max(dp[i-1][j], dp[i][j-1])+ g[i][j]，
因为按照题意规定的行走顺序，到(i, j)点只有两条路径，从上(i-1, j)来 or 从左(i, j-1)来，
即比较从起点(1, 1)到达(i-1, j)点的所有路径的最大值与从起点(1, 1)到达(i, j-1)点的所有路径的最大值，在这两个最大值中选取大的那一个，再加上(i, j)点的花生数量即为所求。
公式推导中用到了第0行or第0列的数据，因为dp[][]定义成的全局变量，存放在堆中，系统为自动清零。
线性dp：两重for循环的变量i、j依次循环，再计算dp[i][j]时，它依赖的两个状态dp[i-1][j] or dp[i][j-1]都已经提前计算出来了，所以可以顺利的推导下去。
*/
public class Q10_摘草莓 {
    static int N = 110;
    static int[][] f = new int[N][N];

    public static void main(String[] args) throws IOException {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han03/q10.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        int n = Integer.parseInt(reader.readLine().trim());
        while (n-- > 0) {
            String[] s1 = reader.readLine().split(" ");
            int r = Integer.parseInt(s1[0]);
            int c = Integer.parseInt(s1[1]);
            for (int i = 1; i <= r; i++) {
                String[] s2 = reader.readLine().split(" ");
                for (int j = 1; j <= c; j++) {
                    f[i][j] = Integer.parseInt(s2[j - 1]);
                }
            }
            for (int i = 1; i <= r; i++) {
                for (int j = 1; j <= c; j++) {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]) + f[i][j];
                }
            }
            System.out.println(f[r][c]);
        }
    }
}
