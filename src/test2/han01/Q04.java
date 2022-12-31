package test2.han01;
//q04_老牛吃嫩草

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
K:老牛位置、H：嫩草位置
注意：数据保证一定有解。
输入格式
第1行： 两个数，表示农场的列数C(C<=150)和行数R(R<=150)。
第2..R+1行: 每行一个由C个字符组成的字符串，共同描绘出牧场地图。
输出格式
一个整数，表示跳跃的最小次数。

（注意先输入的列数，再才是行数。每次走的日字。）
*/
public class Q04 {
    static int N = 155;
    static char[][] g = new char[N][N];
    static int[][]  inq = new int[N][N];
    static int n, m;
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
    static class pair {
        int x , y;
        public pair() {
        }
        public pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("src/han01/q04.txt"));
        System.setIn(inputStream);
        Scanner stdin = new Scanner(System.in);
        m = stdin.nextInt();
        n = stdin.nextInt();
        System.out.println("m:" + m + ",n:" + n);
        for (int i = 0; i < n; i++) {
            //.next()返回一个字符串，.toCharArray()将此字符串转换为一个新的字符数组。
            char[] tmp = stdin.next().toCharArray();
            for (int j = 0; j < tmp.length; j++) {
                g[i][j] = tmp[j];
                System.out.print(g[i][j] + " ");
            }
            System.out.println();
        }
        pair s = new pair();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(g[i][j] == 'K'){
                    s = new pair(i, j);
                }
            }
        }

        int ans = bfs(s.getX(), s.getY());
        if(ans == -1) {
            System.out.println("Impossible!");
        } else {
            System.out.println(ans);
        }
        stdin.close();
    }

    static private int bfs(int x, int y) {
        Queue<pair> q = new LinkedList<pair>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                inq[i][j] = -1;
            }
        }
        q.offer(new pair(x, y));
        inq[x][y] = 0;
        while (!q.isEmpty()) {
            pair top = q.element();
            q.poll();
            if(g[top.getX()][top.getY()] == 'H') {
                return inq[top.getX()][top.getY()];
            }

            for (int i = 0; i < 8; i++) {
                int newx = top.getX() + dx[i];
                int newy = top.getY() + dy[i];
                if(newx<0 || newx>=n || newy<0 || newy>=m) {
                    continue;
                }
                if(g[newx][newy] == '*') {
                    continue;
                }
                if (inq[newx][newy] != -1) {
                    continue;
                }
                q.offer(new pair(newx, newy));
                inq[newx][newy] = inq[top.getX()][top.getX()] + 1;
            }
        }
        return -1;
    }
}
