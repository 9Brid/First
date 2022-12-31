package han04;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
设一个n个节点的二叉树tree的中序遍历为（1,2,3,…,n），其中数字1,2,3,…,n为节点编号。
每个节点都有一个分数（均为正整数），记第i个节点的分数为di，tree及它的每个子树都有一个加分，任一棵子树subtree（也包含tree本身）的加分计算方法如下：?????
subtree的左子树的加分 × subtree的右子树的加分 ＋ subtree的根的分数?
若某个子树为空，规定其加分为1。叶子的加分就是叶节点本身的分数，不考虑它的空子树。
试求一棵符合中序遍历为（1,2,3,…,n）且加分最高的二叉树tree。

要求输出：
（1）tree的最高加分?
（2）tree的前序遍历

输入格式
第1行：一个整数n，为节点个数。?
第2行：n个用空格隔开的整数，为每个节点的分数（0<分数<100）。

输出格式
第1行：一个整数，为最高加分（结果不会超过4,000,000,000）。
第2行：n个用空格隔开的整数，为该树的前序遍历。如果存在多种方案，则输出字典序最小的方案。

数据范围
n<30

区间DP, 依次枚举区间长度len 枚举左端点l, 枚举分界点k（根）, 使用辅助数组记录根节点。
状态定义: f[l][r]表示区间[l,r]的最高得分
状态转移: f[l][r]=max(f[l][r], f[l][k-1]*f[k+1][r]+a[k])
计算结果: f[1][n]
为了保证字典序最小，即是保证根的序号最小，代码中使用dp[l][r] < cnt进行保证，即以k为根的分数比以前的大的时候才更新，而相等的时候不去更新。
*/
public class Q07_二叉树加分 {

    static final int N = 40;
    static int[] a = new int[N];
    static int[][] rt = new int[N][N];
    static int[][] dp = new int[N][N];

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han04/q07.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner stdin = new Scanner(in);

        int n = stdin.nextInt();

        for (int i = 1; i <= n; i++) {
            a[i] = stdin.nextInt();
        }

        for (int len = 1; len <= n; len++) {      //重点关注，len起始值
            for (int l = 1; l + len - 1 <= n; l++) {
                int r = l + len - 1;
                if (len == 1) {                                           //边界，叶子的加分就是叶节点本身的分数，不考虑它的空子树，根就是本身
                    dp[l][r] = a[l];
                    rt[l][r] = l;
                } else {
                    for (int k = l; k <= r; k++) {                        //重点关注，k起始值、终止值
                        int left = k == l ? 1 : dp[l][k - 1];           //若某个子树为空，规定其加分为1
                        int right = k == r ? 1 : dp[k + 1][r];
                        int cnt = left * right + a[k];
                        if (dp[l][r] < cnt) {
                            dp[l][r] = cnt;
                            rt[l][r] = k;
                        }
                        System.out.printf("%d %d %d %d %3d %3d %3d\n", len, l, k, r, left, right, dp[l][r]);
                    }
                }
            }
        }

        System.out.printf("%d\n", dp[1][n]);

        print(1, n);
    }

    private static void print(int l, int r) {
        if (l > r) return;

        int root = rt[l][r];
        System.out.printf("%d ", root);
        print(l, root - 1);
        print(root + 1, r);
    }


}
