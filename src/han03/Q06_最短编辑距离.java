package han03;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
给定两个字符串A和B，现在要将A经过若干操作变为B，可进行的操作有：

删除–将字符串A中的某个字符删除。
插入–在字符串A的某个位置插入某个字符。
替换–将字符串A中的某个字符替换为另一个字符。
现在请你求出，将A变为B至少需要进行多少次操作。

输入格式
第一行包含字符串A的长度整数n
第二行包含一个长度为n的字符串A。
第三行包含字符串B的长度整数m
第四行包含一个长度为m的字符串B。
字符串中均只包含大写字母。

输出格式
输出一个整数，表示最少操作次数。

数据范围
1≤n,m≤1000

dp[i][j]表示把a[1...i]、b[1...j]变成一样的，需要的最少操作步骤。
初始化dp[i][0] = i表示把a[1...i]变成空需要i步删除操作；dp[0][i] = i表示把空的a[]变成b[1...i]需要i步增加操作。

删除：dp[i - 1][j] + 1，删除a[i]后与b[1...j]一样，那么a[1...i-1]与b[1...j]一样，即在a[1...i-1]与b[1...j]变成一样的最小操作步骤另加一步删除a[i]操作。
增加：dp[i][j - 1] + 1，增加a[i+1]后与b[1...j]一样，那么增加a[i+1]前a[1...i]与b[1...j-1]就要一样，即在a[1...i]与b[1...j-1]变成一样的最小操作步骤另加一步增加a[i+1]操作。
修改：如果a[i]==b[j]，dp[i - 1][j - 1]，由于a[i]==b[j]，故把a[1...i]与b[1...j]变成一样的最小操作步骤，即是把a[1...i-1]与b[1...j-1]变成一样的最小操作步骤。
如果a[i]!=b[j]，dp[i - 1][j - 1] + 1，由于a[i]！=b[j]，故把a[1...i]与b[1...j]变成一样的最小操作步骤，即是把a[1...i-1]与b[1...j-1]变成一样的最小操作步骤另加一步把a[i]修改成b[j]的操作即可。
最后这三种情况取最小值即可。
*/
public class Q06_最短编辑距离 {
    static int N = 1010;
    static int[][] f = new int[N][N];

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han03/q06.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(in);
        int n = scan.nextInt();
        String a = " " + scan.next();
        int m = scan.nextInt();
        String b = " " + scan.next();
        for (int i = 0; i <= n; i++) f[i][0] = i;//若a长度为i，b长度为0，则需要进行i次删除操作
        for (int i = 0; i <= m; i++) f[0][i] = i;//若a长度为0，b长度为i，则需要进行i次添加操作

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {   //删除和添加
                f[i][j] = Math.min(f[i - 1][j] + 1, f[i][j - 1] + 1);
                //修改
                if (a.charAt(i) == b.charAt(j)) f[i][j] = Math.min(f[i][j], f[i - 1][j - 1]);
                else f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + 1);
            }
        System.out.println(f[n][m]);
    }
}
