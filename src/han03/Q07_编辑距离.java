package han03;

/*
给定n个长度不超过10的字符串以及m次询问，每次询问给出一个字符串和一个操作次数上限。
对于每次询问，请你求出给定的n个字符串中有多少个字符串可以在上限操作次数内经过操作变成询问给出的字符串。
每个对字符串进行的单个字符的插入、删除或替换算作一次操作。

输入格式
第一行包含两个整数n和m。
接下来n行，每行包含一个给定的字符串。
再接下来m行，每行包含一个字符串和一个整数，表示一次询问。
字符串中只包含小写字母，且长度均不超过10。

输出格式
输出共m行，每行输出一个整数作为结果，表示一次询问中满足条件的字符串个数。

数据范围
1≤n,m≤1000

修改情况，dp[i][j] = min(dp[i][j], dp[i - 1][j - 1] + (a[i] != b[j]))这种写法更加简单。
注意dp[i][0]、dp[0][i]是初始化成了i。
*/


import java.io.*;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

public class Q07_编辑距离 {

    //static BufferedReader read = new BufferedReader(new InputStreamReader(in));
    static int INF = 0x3f3f3f3f;

    public static int minEdit(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) dp[i][0] = i;
        for (int i = 1; i <= n2; i++) dp[0][i] = i;
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                dp[i][j] = INF;
                dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                dp[i][j] = Math.min(dp[i][j],
                        dp[i - 1][j - 1] + (s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1));
            }
        }
        return dp[n1][n2];
    }

    public static void main(String[] args) throws Exception {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han03/q07.txt"));
            setIn(in);
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner read = new Scanner(in);
        String[] ss = read.nextLine().split(" ");
        int n = Integer.valueOf(ss[0]);
        int m = Integer.valueOf(ss[1]);
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = read.nextLine();
        }
        while (m-- > 0) {
            int ans = 0;
            ss = read.nextLine().split(" ");
            int limit = Integer.valueOf(ss[1]);
            for (int i = 0; i < n; i++) {
                if (minEdit(s[i], ss[0]) <= limit) ans++;
            }
            System.out.println(ans);
        }

    }

}
