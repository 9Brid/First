package han04;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
若正整数n可分解为p1^a1*p1^a2*...*pk^ak
其中pi为两两不同的素数,ai为对应指数
n的约数个数为(1+a1)*(1+a2)*....*(1+ak)
如180=2*2*3*3*5=2^2*3^2*5
180的约数个数为(1+2)*(1+2)*(1+1)=18个。

求n！中的正约数个数。
*/


public class Q11_分解n的阶乘 {
    static final int N = 1000010;
    static int[] p = new int[N];
    static int pNum1, pNum2;
    static boolean[] flag = new boolean[N + 1];

    static class PII {
        int x, y;

        public PII() {
        }

        public PII(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    static PII[] y = new PII[N];

    static void findPrime(int n) {
        //埃氏筛法
        for (int i = 2; i <= n; i++) {
            if (flag[i] == false) {
                p[pNum1++] = i;
                for (int j = i + i; j <= n; j += i) {//2 4 6 8 10 12, 3 6 9 12...
                    flag[j] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han04/q11.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner stdin = new Scanner(in);
        int n = stdin.nextInt();

        findPrime(n);
        for (int i = 0; i < pNum1; i++) {
            int q = p[i];
            int s = 0;
            for (int j = n; j != 0; j /= q) {
                // 求n！有多少个质因子q，n/q + n/q^2 + n/q^3...。
                // 思考：如何n！末尾含有多少个0呢？
                // 即求n！中质因子5的个数。p182
                s += j / q;
            }
            System.out.printf("质因子%d 个数%d\n", q, s);

            y[pNum2++] = new PII(q, s);
            ;
        }

        int ans = 1;
        for (int i = 0; i < pNum2; i++) {
            ans *= (y[i].y + 1);
            //重点关注，是*不是+
        }

        System.out.printf("%d!的正约数个数为: %d\n", n, ans);
    }
}
