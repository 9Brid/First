package test2.han02;

import java.util.Scanner;

/** 全排列
 * 注意返回时的恢复现场。dfs暴搜时最主要的是搜索的顺序。
 * 对于每个位置，都要把所有的值枚举一遍。
 */
public class Q01 {
    static int N = 10;
    static int[] path = new int[N];
    static int n;
    static boolean[] st = new boolean[N];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        dfs(0);
        scanner.close();
    }

    private static void dfs(int u) {
        if(u == n){
            for (int i = 0; i < n; i++) {
                System.out.printf("%d ", path[i]);
            }
            System.out.println();
            return;
        }
        for (int i = 1; i <= n;i++) {
            if(!st[i]) {
                path[u] = i;
                st[i] = true;
                dfs(u+1);
                st[i] = false;
                path[u] = 0;
            }
        }
    }
}
