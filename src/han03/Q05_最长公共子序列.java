package han03;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.setIn;

/*
LCS
给定两个长度分别为N和M的字符串A和B，求既是A的子序列又是B的子序列的字符串长度最长是多少。

输入格式
第一行包含两个整数N和M。
第二行包含一个长度为N的字符串A。
第三行包含一个长度为M的字符串B。

字符串均由小写字母构成。

输出格式
输出一个整数，表示最大长度。

数据范围
1≤N,M≤1000

dp[i][j]表示a[1...i]与b[1...j]的公共子序列的最大长度，
根据a[i]、b[j]是否相等进行划分，
相等时： dp[i-1][j-1]+1 （条件：a[i]==b[j]）
不等时： 继承dp[i - 1][j], dp[i][j - 1]中的最大值
*/
public class Q05_最长公共子序列 {
    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han03/q05.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        String A = " " + scan.next();
        String B = " " + scan.next();
        int[][] f = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                //包含A[i] = B[j]情况的集合
                if (A.charAt(i) == B.charAt(j))
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);
            }
        }
        System.out.println(f[n][m]);
    }
}
