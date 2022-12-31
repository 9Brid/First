package han04;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
河南北两岸各有位置各不相同的N个城市。
北岸的每个城市有且仅有一个友好城市在南岸，而且不同城市的友好城市不相同。
每对友好城市都向政府申请在河上开辟一条直线航道连接两个城市，但是由于河上雾太大，政府决定避免任意两条航道交叉，以避免事故。
编程帮助政府做出一些批准和拒绝申请的决定，使得在保证任意两条航线不相交的情况下，被批准的申请尽量多。

输入格式
第1行，一个整数N，表示城市数。
第2行到第n+1行，每行两个整数，中间用1个空格隔开，分别表示南岸和北岸的一对友好城市的坐标。

输出格式
仅一行，输出一个整数，表示政府所能批准的最多申请数。

数据范围
1≤N≤5000,
0≤xi≤10000

两个坐标需按照其中一个排序，用pair，按照first排好序后，如果first小的对应的second却比first大的对应的second大，那么这两座桥肯定交叉。
*/
public class Q02_奈何桥 {
    static class Node implements Comparable<Node> {
        int x;
        int y;

        public Node() {

        }

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            return x - o.x;
        }
    }

    static final int N = 5010;
    static Node[] a = new Node[N];
    static int[] dp = new int[N];

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han04/q02.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner stdin = new Scanner(in);

        int n = stdin.nextInt();
        for (int i = 0; i < n; i++) {
            Node tmp = new Node();
            tmp.x = stdin.nextInt();
            tmp.y = stdin.nextInt();
            a[i] = tmp;
        }
        Arrays.sort(a, 0, n);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (a[j].x < a[i].y) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        System.out.printf("%d\n", ans);
    }


}


