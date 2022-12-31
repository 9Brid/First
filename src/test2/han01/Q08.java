package test2.han01;
//q08_去最近食堂的哈曼顿距离
/*
多起点bfs

给定一个N行M列的01矩阵A，A[i][j] 与 A[k][l] 之间的曼哈顿距离定义为：
		dist(A[i][j],A[k][l])=|i-k|+|j-l|
输出一个N行M列的整数矩阵B，其中：
		B[i][j]=min dist(A[i][j],A[x][y]) (1≤x≤N,1≤y≤M,A[x][y]=1)
输入格式
第一行两个整数n,m。
接下来一个N行M列的01矩阵，数字之间没有空格。
输出格式
一个N行M列的矩阵B，相邻两个整数之间用一个空格隔开。
数据范围
1≤N,M≤1000

问题类似于：学校有很多食堂，下课后学生去最近食堂的距离。
*/

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q08 {
    static int N = 1010, M = N * N;
    static char[][] g = new char[N][N];
    static int[][] inq = new int[N][N];
    static int n, m;
    static class Pair {
        int x, y;
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public Pair() {
        }
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("src/han01/q08.txt"));
        System.setIn(bufferedInputStream);
        Scanner stdin = new Scanner(System.in);
        n = stdin.nextInt();
        m = stdin.nextInt();
        for (int i = 0; i < n; i++) {
            String tmp = stdin.next();
            for (int j = 0; j <tmp.length() ; j++) {
                g[i][j] = tmp.charAt(j);
                System.out.print(g[i][j] + " ");
            }
            System.out.println();
        }

        bfs();
        System.out.println("-----------");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(inq[i][j] + " ");
            }
            System.out.println();
        }
        stdin.close();
    }

    private static void bfs() {
        Queue<Pair> q = new LinkedList<Pair>();
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                inq[i][j] = -1;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if( g[i][j] == '1') {
                    q.offer(new Pair(i, j));
                    inq[i][j] = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            Pair top = q.element();
            q.poll();
            for (int i = 0; i < 4; i++) {
                int newx = top.getX() + dx[i];
                int newy = top.getY() + dy[i];
                if(newx<0 || newx>=n || newy<0 || newy>=m) {
                    continue;
                }
                if(inq[newx][newy] != -1) {
                    continue;
                }
                q.offer(new Pair(newx, newy));
                inq[newx][newy] = inq[top.getX()][top.getY()] + 1;
            }
        }
    }
}
