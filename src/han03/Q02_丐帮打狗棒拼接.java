package han03;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

public class Q02_丐帮打狗棒拼接 {
    static int N = 70;
    static Integer[] sticks = new Integer[N], st = new Integer[N];
    static int n, len, cnt;

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han03/q02.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner stdin = new Scanner(in);
        while (true) {
            n = stdin.nextInt();
            if (n == 0) {
                break;
            }
            int maxl = 0, sum = 0;
            Arrays.fill(sticks, 0);
            for (int i = 0; i < n; i++) {
                sticks[i] = stdin.nextInt();
                maxl = Math.max(maxl, sticks[i]);
                sum += sticks[i];
            }

            Arrays.sort(sticks, Collections.reverseOrder());
            System.out.println(Arrays.toString(sticks));
            Arrays.fill(st, 0);
            for (len = maxl; len <= sum; len++) {
                if (sum % len != 0) continue;
                cnt = sum / len;
                if (dfs(0, 0, 0)) {
                    break;
                }
            }
            System.out.println(len);
        }
    }
//u代表第几根木棒，cur代表当前长度，start代表数组下标
    private static boolean dfs(int u, int cur, int start) {
        if (u == cnt) return true;
        if (cur == len) return (dfs(u + 1, 0, 0));
        int errLen = 0;
        for (int i = start; i < n; i++) {
            if (st[i] != 0) continue;
            if (cur + sticks[i] > len) continue;
            st[i] = 1;
            if (dfs(u, cur + sticks[i], i + 1)) return true;
            st[i] = 0;
            errLen = sticks[i];
            if (cur == 0 || cur + sticks[i] == len) return false;
        }
        return false;
    }
}
