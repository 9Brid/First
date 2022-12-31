package han04;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
有一个缺陷导弹拦截系统：虽然它的第一发导弹能够到达任意的高度，但是以后每一发导弹都不能高于前一发的高度。
某天，雷达捕捉到敌国的导弹来袭。
由于该系统还在试用阶段，所以只有一套系统，因此有可能不能拦截所有的导弹。
输入导弹依次飞来的高度（雷达给出的高度数据是不大于30000的正整数，导弹数不超过1000），计算这套系统最多能拦截多少导弹，如果要拦截所有导弹最少要配备多少套这种导弹拦截系统。

输入格式
共一行，输入导弹依次飞来的高度。

输出格式
第一行包含一个整数，表示最多能拦截的导弹数。
第二行包含一个整数，表示要拦截所有导弹最少要配备的系统数。

数据范围
雷达给出的高度数据是不大于 30000 的正整数，导弹数不超过 1000。

一个序列最少要用多少个不上升子序列才可以覆盖的数量等于最长上升子序列元素的数量。
注意读入a[i]后i++的位置，while循环结束后i即是读入的元素个数。注意不能用while(scanf("%d", &a[i++]) != -1)，因为在最后scanf发生错误返回-1时，i任然会自增一次。
第一次是最长不上升子序列，第二次是最长上升子序列
*/
public class Q04_导弹拦截 {
    static final int N = 1010;
    static int[] a = new int[N], dp = new int[N], b = new int[N];

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han04/q04.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner stdin = new Scanner(in);

        int i = 0;
        while (stdin.hasNext()) {
            a[i] = stdin.nextInt();
            i++;
        }

        for (int j = 0; j < i; j++) {
            System.out.printf("%d ", a[j]);
        }
        System.out.printf("\n");

        int ans1 = 0;
        for (int j = 0; j < i; j++) {
            dp[j] = 1;
            for (int k = 0; k < j; k++) {
                if (a[k] >= a[j]) {                            //重点关注
                    dp[j] = Math.max(dp[j], dp[k] + 1);
                }
            }
            ans1 = Math.max(ans1, dp[j]);
        }

        int ans2 = 0;
        for (int j = 0; j < i; j++) {
            dp[j] = 1;
            for (int k = 0; k < j; k++) {
                if (a[k] < a[j]) {                             //重点关注
                    dp[j] = Math.max(dp[j], dp[k] + 1);
                }
            }
            ans2 = Math.max(ans2, dp[j]);
        }

        System.out.printf("%d\n%d\n", ans1, ans2);

    }
}
