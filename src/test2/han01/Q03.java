package test2.han01;
//q03_走迷宫_打印路径

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
输入：
行数 列数
迷宫
起点横纵坐标 终点横纵坐标

结果：
最少需要走多少步，且把路径打印出来
*/
public class Q03 {
    static int N = 105;
    static int n, m;
    static char[][] a = new char[N][N];
    static boolean[][] inq = new boolean[N][N];
    static class Node{
        int x, y, step;
    }
    static Node S = new Node();
    static Node T = new Node();
    static int[] X = {1, 0, -1, 0};
    static int[] Y = {0, -1, 0, 1};
    static Node[][] path = new Node[N][N];

    /**
     * 当类被加载时，静态代码块会执行，由于类只加载一次，因此静态代码块只执行一次。在程序中，通常会使用静态代码块来对类的成员变量进行初始化。
     */
    static {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                path[i][j] = new Node();
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("src/han01/q02.txt"));
        System.setIn(in);
        Scanner stdin = new Scanner(System.in);

        n = stdin.nextInt();
        m = stdin.nextInt();
        System.out.println("n:" + n + ", m:" + m);
        for (int i = 0; i < n; i++) {
            String str = stdin.next();
            for (int j = 0; j < m; j++) {
                a[i][j] = str.charAt(j);
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        S.x = stdin.nextInt();
        S.y = stdin.nextInt();
        S.step = 0;
        T.x = stdin.nextInt();
        T.y = stdin.nextInt();
        System.out.println(S.x + " " + S.y + "    " +T.x + " " + T.y);

        int num = bfs();
        System.out.println(num);

        System.out.println("路径打印方法一：");
        int x = T.x, y = T.y;
        Stack<Node> s = new Stack<Node>();
        for (int i = 0; i < num; i++) {
            int xx = path[x][y].x;
            int yy = path[x][y].y;
            Node tmp = new Node();
            tmp.x = xx;
            tmp.y = yy;
            tmp.step = path[x][y].step;
            s.push(tmp);
            x = xx;
            y = yy;
        }
        while (!s.isEmpty()) {
            Node tmp = s.peek();
            s.pop();
            System.out.println("(" + tmp.x + " " + tmp.y + " " + (char)tmp.step + ")");
        }

        System.out.println("路径打印方法二：");
        print(T.x, T.y);
        stdin.close();
    }

    private static void print(int x, int y) {
        if(x == S.x && y == S.y){
            return;
        }
        print(path[x][y].x, path[x][y].y);
        System.out.printf("(%d %d %c)\n", path[x][y].x, path[x][y].y, path[x][y].step);
    }

    private static boolean judge(int x, int y) {
        if(x<0 || x >=n || y<0 || y>=m) {
            return false;
        }
        if(a[x][y] == '*') {
            return false;
        }
        if(inq[x][y]) {
            return false;
        }
        return true;
    }
    private static int bfs(){
        Queue<Node> q = new LinkedList<Node>();
        q.offer(S);
        inq[S.x][S.y] = true;
        while (!q.isEmpty()) {
            Node top = q.element();
            q.poll();
            if(top.x == T.x && top.y == T.y) {
                return top.step;
            }

            for (int i = 0; i < 4; i++) {
                int newX = top.x + X[i];
                int newY = top.y + Y[i];
                if(judge(newX, newY)){
                    Node node = new Node();
                    node.x = newX;
                    node.y = newY;
                    node.step = top.step + 1;
                    q.offer(node);
                    inq[newX][newY] = true;

                    path[newX][newY].x = top.x;
                    path[newX][newY].y = top.y;
                    switch (i) {
                        case 0:
                            path[newX][newY].step = 'E';
                            break;
                        case 1:
                            path[newX][newY].step = 'S';
                            break;
                        case 2:
                            path[newX][newY].step = 'W';
                            break;
                        case 3:
                            path[newX][newY].step = 'N';
                            break;
                    }
                }
            }
        }
        return 0;
    }
}
