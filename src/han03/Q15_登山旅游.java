package han03;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
登山观光，队员们发现山上一个有N个景点，并且决定按照顺序来浏览这些景点，即每次所浏览景点的编号都要大于前一个浏览景点的编号。
同时队员们还有另一个登山习惯，就是不连续浏览海拔相同的两个景点，并且一旦开始下山，就不再向上走了。
队员们希望在满足上面条件的同时，尽可能多的浏览景点，你能帮他们找出最多可能浏览的景点数么？

输入格式
第一行包含整数N，表示景点数量。

第二行包含N个整数，表示每个景点的海拔。

输出格式
输出一个整数，表示最多能浏览的景点数。

数据范围
2≤N≤1000

正向求一遍上升子序列，反向求一遍下降子序列，最终答案是对每一个点的最长上升子序列 + 最长下降子序列之和再减1后的最大值，因为中间的那个点算了两次，所有需要减1
*/
public class Q15_登山旅游 {
    private static final int N = 1010;
    static int[] a = new int[N], dpl = new int[N], dpr = new int[N];

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han03/q15.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner stdin = new Scanner(in);
        int n;
        n = stdin.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = stdin.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            dpl[i] = 1;
            for (int j = 1; j < i; j++) {
                if (a[j] < a[i]) {
                    dpl[i] = Math.max(dpl[i], dpl[j] + 1);
                }
            }
        }

        for (int i = n; i >= 1; i--) {
            dpr[i] = 1;
            for (int j = n; j > i; j--) {
                if (a[j] < a[i]) {
                    dpr[i] = Math.max(dpr[i], dpr[j] + 1);
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dpl[i] + dpr[i] - 1);
        }
        System.out.println(ans);
    }
}
