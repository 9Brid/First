package han03;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
时迁兄弟操作受损的滑翔翼逃脱。
假设城市中一共有N幢建筑排成一条线，每幢建筑的高度各不相同。
初始时，时迁兄弟可以在任何一幢建筑的顶端。
他可以选择一个方向逃跑，但是不能中途改变方向（因为中森警部会在后面追击）。
因为滑翔翼动力装置受损，他只能往下滑行（即：只能从较高的建筑滑翔到较低的建筑）。
他希望尽可能多地经过不同建筑的顶部，这样可以减缓下降时的冲击力，减少受伤的可能性。
请问，他最多可以经过多少幢不同建筑的顶部(包含初始时的建筑)？

输入格式
输入数据第一行是一个整数K，代表有K组测试数据。
每组测试数据包含两行：第一行是一个整数N，代表有N幢建筑。第二行包含N个不同的整数，每一个对应一幢建筑的高度h，按照建筑的排列顺序给出。

输出格式
对于每一组测试数据，输出一行，包含一个整数，代表时迁兄弟最多可以经过的建筑数量。

数据范围
1≤K≤100,
1≤N≤100,
0<h<10000

dp[i]:所有以a[i]结尾的上升子序列 or 下降子序列的最大值
选定某一栋建筑物，选定一个方向后就不能回头了，
正反各求一遍最长上升子序列，最后求出两遍的最大值即可。
*/
public class Q14_时迁兄弟逃跑 {
    static int N = 110;
    static int[] a = new int[N], dp = new int[N];

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han03/q14.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner stdin = new Scanner(in);
        int t, n;
        t = stdin.nextInt();
        while (t-- != 0) {
            n = stdin.nextInt();
            for (int i = 1; i <= n; i++) {
                a[i] = stdin.nextInt();
            }
            int ans = 0;
            for (int i = 1; i <= n; i++) {
                dp[i] = 1;
                for (int j = 1; j < i; j++) {
                    if (a[j] < a[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                ans = Math.max(ans, dp[i]);
            }
            for (int i = n; i >= 1; i--) {
                dp[i] = 1;
                for (int j = n; j > i; j--) {
                    if (a[j] < a[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                ans = Math.max(ans, dp[i]);
            }
            System.out.println(ans);
        }
    }
}
