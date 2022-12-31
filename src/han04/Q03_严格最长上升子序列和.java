package han04;

import static java.lang.System.in;
import static java.lang.System.setIn;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
注意，最长的上升子序列的和不一定是最大的，比如序列(100,1,2,3)的最大上升子序列和为100，而最长上升子序列为(1,2,3)。

输入格式
输入的第一行是序列的长度N。
第二行给出序列中的N个整数，这些整数的取值范围都在0到10000(可能重复)。

输出格式
输出一个整数，表示最大上升子序列和。

数据范围
1≤N≤1000

dp[i]表示以ai结尾的最长上升子序列和的最大值。
*/
public class Q03_严格最长上升子序列和 {
    static final int N = 1010;
    static int[] a = new int[N], dp = new int[N];

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han04/q03.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner stdin = new Scanner(in);

        int n = stdin.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = stdin.nextInt();
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = a[i];                                    //重点关注
            for (int j = 1; j < i; j++) {
                if (a[j] < a[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + a[i]);        //重点关注
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        System.out.printf("%d\n", ans);
    }
}
