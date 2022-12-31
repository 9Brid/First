package han03;

import static java.lang.System.in;
import static java.lang.System.setIn;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


/*
设有N堆石子排成一排，其编号为1，2，3，…，N。
每堆石子有一定的质量，可以用一个整数来描述，现在要将这N堆石子合并成为一堆。
每次只能合并相邻的两堆，合并的代价为这两堆石子的质量之和，合并后与这两堆石子相邻的石子将和新堆相邻，合并时由于选择的顺序不同，合并的总代价也不相同。
例如有4堆石子分别为 1 3 5 2， 我们可以先合并1、2堆，代价为4，得到4 5 2， 又合并 1，2堆，代价为9，得到9 2 ，再合并得到11，总代价为4+9+11=24；
如果第二步是先合并2，3堆，则代价为7，得到4 7，最后一次合并代价为11，总代价为4+7+11=22。
问题是：找出一种合理的方法，使总的代价最小，输出最小代价。

输入格式
第一行一个数N表示石子的堆数N。

第二行N个数，表示每堆石子的质量(均不超过1000)。

输出格式
输出一个整数，表示最小代价。

数据范围
1≤N≤300

dp[i][j]表示所有合并区间[i, j]之间石子方案的最小代价，
把其分成[i, k]、[k+1, j]两个区间，然后分别算出这两个区间的最小代价，
然后再加上[i, j]之间所有元素的和（这可以用前缀和来求），
对k从i到j-1循环处理算出最小值。最开始分割成的两个区间是[i, i]+[i+1, j]，
最后分割的区间是[i, j-1] +[j, j]，即区间最短也必须有一个元素，这也就是len从2开始的原因。

注意dp的初始化要memset在前，dp[i][i]=1在后，要顺序搞反了就错了。最后答案就是dp[1][n]，也就是合并区间[1, n]之间的石子的最小代价。

4
1 3 5 2

 2  1  1  2  0  0  4
 2  2  2  3  0  0  8
 2  3  3  4  0  0  7
 3  1  1  3  0  8 17
 3  1  2  3  4  0 13
 3  2  2  4  0  7 17
 3  2  3  4  8  0 17
 4  1  1  4  0 17 28
 4  1  2  4  4  7 22
 4  1  3  4 13  0 22
22
*/

public class Q08_合并石头堆儿 {
    public static final int N = 310;

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han03/q08.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(in);
        int n = scan.nextInt();
        int[] a = new int[N];
        for (int i = 1; i <= n; i++) {
            a[i] = scan.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            a[i] += a[i - 1];//计算前缀和
        }

        int[][] dp = new int[N][N];

        int len = 1;
        for (len = 2; len <= n; len++) {//长度从小到大递增
            for (int i = 1; i + len - 1 <= n; i++) {
                int left = i;//左边界
                int right = i + len - 1;//右边界
                dp[left][right] = (int) 1e9;
                for (int k = left; k < right; k++) {//k表示在left到right之间的哪个点进行分割
                    dp[left][right] = Math.min(dp[left][right], dp[left][k] + dp[k + 1][right] + a[right] - a[left - 1]);
                }
            }
        }

        System.out.println(dp[1][n]);
    }
}
