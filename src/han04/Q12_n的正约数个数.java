package han04;

/*
若正整数n可分解为p1^a1*p1^a2*...*pk^ak
其中pi为两两不同的素数,ai为对应指数
n的约数个数为(1+a1)*(1+a2)*....*(1+ak)
如180=2*2*3*3*5=2^2*3^2*5
180的约数个数为(1+2)*(1+2)*(1+1)=18个。
*/

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

public class Q12_n的正约数个数 {

    static final int N = 1000010;
    static int[] p = new int[N];
    static int pNum1;
    static boolean[] flag = new boolean[N + 1];

    static class Node {
        int x, cnt;

        Node() {
        }

        Node(int x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }
    }

    static Node[] fac = new Node[100];
    static int pNum2;

    static void findPrime(int n) {                                                             //埃氏筛法
        for (int i = 2; i <= n; i++) {
            if (flag[i] == false) {
                p[pNum1++] = i;

                for (int j = i + i; j <= n; j += i) {                                    //2 4 6 8 10 12, 3 6 9 12...
                    flag[j] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han04/q12.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner stdin = new Scanner(in);
        int m, n;
        m = stdin.nextInt();
        n = m;
        findPrime(n);
        for (int i = 0; i < pNum1 && p[i] <= Math.sqrt(n); i++) {     //分别处理<=sqrt(n)的质因子，因为大于sqrt(n) 的质因子不可能去整除n
            if (n % p[i] == 0) {
                fac[pNum2] = new Node(p[i], 0);

                while (n % p[i] == 0) {                          //求n中有多少质因子p[i]
                    fac[pNum2].cnt++;
                    n /= p[i];
                }
                pNum2++;
            }

            if (n == 1) break;                                   //如果n已经变成1了，提前退出循环
        }

        if (n != 1) {
            fac[pNum2++] = new Node(n, 1);
        }

        int ans = 1;
        for (int i = 0; i < pNum2; i++) {
            ans *= (fac[i].cnt + 1);
        }

        System.out.printf("%d的正约数个数为: %d\n", m, ans);
    }
}
