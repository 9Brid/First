package han03;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
从(1, 1)、(1, 1)沿着两条路径分别到达(i1, j1)、(i2, j2)的所有路线中的最大和用dp[i1][j1][i2][j2]表示，
由于两条路径的(i1, j1)、(i2, j2)可能是同一坐标，两条路径的最大和只能加一次a[i1][j1]，
如果这两个坐标不一样，那么两条路径的最大和需要+a[i1][j1]，再+a[i2][j2]。
坐标相同时i1+j1肯定等于i2+j2，我们令k=i1+j1=i2+j2（理解为从起点到当前点一共走了多少步，两个一起走 i1 + j1 应该恒等于 i2 + j2 ），那么就可以变成dp[k][i1][i2]，四维变三维
只要i1不等于i2就表示坐标不相同，因为i1不等于i2时，j1=k-i1就肯定不等于j2=k-i2。

两条路径可以从上上、上左、左上、左左到dp[k][i1][i2] --> dp[i1][k-i1][i2][k-i2]，
上上：dp[k-1][i1-1][i2-1] --> dp[i1-1][k-i1][i2-1][k-i2]，k-1-(i1-1)=k-i1
上左：
左上：dp[k-1][i1][i2-1]  --> dp[i1][k-i1-1][i2-1][k-i2]，k-1-i1=k-i1-1
左左：
通过计算得到的纵坐标j1 = k - i1, j2 = k - i2需要判断是否越界。
由于dp[k][i1][i2]在多处要用到且太长，为了方便定义了一个引用int &m = dp[k][i1][i2]，然后直接对这个引用操作就行了。
注意在输入完后最好是检查下所有输入是否正确。

关于Scanner和BufferedReader的对比分析：
BufferedReader可以用来读取文件或者接收来自键盘（控制台）的信息。它比Scanner更加快捷，能够大幅度缩短程序运行时间。
它下面的readline()方法可以一次性读取一行文字（String），非常方便。
需要注意的是，使用BufferedReader对象的readLine()方法必须处理java.io.IOException异常(Exception)。
以及，在使用完BufferredReader以后，需要用close()方法关闭流。
*/
public class Q12_方格取数 {
    static int N = 15;
    static int[][][] f = new int[N * 2][N][N];
    static int[][] w = new int[N][N];

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han03/q12.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(in);
        int n = scan.nextInt();
        while (true) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            int c = scan.nextInt();
            if (a == 0 || b == 0 || c == 0) break;
            w[a][b] = c;
        }

        for (int k = 2; k <= n * 2; k++)
            for (int i1 = 1; i1 <= n; i1++)
                for (int i2 = 1; i2 <= n; i2++) {
                    int j1 = k - i1;
                    int j2 = k - i2;
                    if (j1 <= 0 || j1 > n || j2 <= 0 || j2 > n) continue;

                    int t = w[i1][j1];
                    if (i1 != i2) t += w[i2][j2];//若路径重合
                    int x = f[k][i1][i2];
                    x = Math.max(x, f[k - 1][i1 - 1][i2 - 1] + t);
                    x = Math.max(x, f[k - 1][i1 - 1][i2] + t);
                    x = Math.max(x, f[k - 1][i1][i2 - 1] + t);
                    x = Math.max(x, f[k - 1][i1][i2] + t);
                    f[k][i1][i2] = x;
                }
        System.out.println(f[n * 2][n][n]);
    }
}
