package han04;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
N位同学站成一排，音乐老师要请其中的(N-K)位同学出列，使得剩下的K位同学排成合唱队形。
合唱队形：设K位同学从左到右依次编号为1，2…，K，他们的身高分别为T1，T2，…，TK，则他们的身高满足T1<…<Ti>Ti+1>…>TK(1≤i≤K)。
你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
输入格式
输入的第一行是一个整数N，表示同学的总数。
第二行有n个整数，用空格分隔，第i个整数Ti是第i位同学的身高(厘米)。
输出格式
输出包括一行，这一行只包含一个整数，就是最少需要几位同学出列。
数据范围
2≤N≤100,
130≤Ti≤230

从两边各求一次最长上升子序列，思路与登山旅游相同。注意最后的结果是n - ans，而不是ans。
*/
public class Q01_大合唱 {
    static final int N = 110;
    static int[] a = new int[N], dpl = new int[N], dpr = new int[N];

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(".//src//han04//q01.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner stdin = new Scanner(in);

        int n = stdin.nextInt();
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
            ans = Math.max(ans, dpl[i] + dpr[i] - 1);                                  //重点关注，需-1
        }

        System.out.printf("%d\n", n - ans);

    }
}
