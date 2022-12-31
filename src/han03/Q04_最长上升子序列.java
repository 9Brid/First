package han03;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
LIS
dp[i]表示所有以第i个数结尾的上升子序列的最大长度,
以a[i]结尾的上升子序列的前一个数是没有任何数字还是a[1]...a[i-1]去划分,

注意求的是最长上升子序列，需严格单调递增，故if(a[j] < a[i])；如果求的是最长不下降子序列，由于不需要严格单调递增，故if(a[j] <= a[i])。
dp[i] = 1表示以a[i]结尾且只有自身一个元素构成的最长上升子序列的长度为1。
j从1到i-1，如果a[j] < a[i]，那么dp[i]就有可能需要更新，因为以a[j]结尾的最长上升子序列后面再加上a[i]，则长度有可能会更新成dp[j]+1（以a[j]结尾的最长上升子序列的长度dp[j]再加上1，因为后面多了一个a[i]元素）
*/
public class Q04_最长上升子序列 {
    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han03/q04.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(in);
        int N = 1100;
        int[] f = new int[N];
        int[] a = new int[N];

        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            f[i] = 1;//每一个f[i]的初始化都是1，因为最短就是本身，即为1；
            for (int j = 1; j < i; j++) {
                if (a[i] > a[j]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }

        int res = 0; //通过遍历f数组，来求最大值

        for (int i = 1; i <= n; i++) {
            res = Math.max(res, f[i]);
        }
        System.out.println(res);
    }
}
