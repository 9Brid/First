package test2.han01;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q01 {
    static int N = 105;
    static int n, m;
    static int[][] a = new int[N][N];
    static boolean[][] inq = new boolean[N][N];
    static class Node{
        int x, y;
    }
    static int[] X = {1, 0, -1, 0};
    static int[] Y = {0, -1, 0, 1};

    public static void main(String[] args) throws FileNotFoundException {
        //
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("src/han01/q01.txt"));
        System.setIn(in);
        Scanner stdin = new Scanner(System.in);
        n = stdin.nextInt();
        m = stdin.nextInt();
        System.out.println("n:" + n + ",m:" + m);
        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = stdin.nextInt();
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(a[i][j] == 1 && inq[i][j]==false){
                    ans++;
                    bfs(i, j);
                }
            }
        }
        System.out.println(ans);
        stdin.close();
    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<Node>();
        Node node = new Node();
        node.x = x;
        node.y = y;

        q.offer(node);
        inq[x][y] = true;
        while (!q.isEmpty()) {
            Node top = q.element();
            q.poll();
            for (int i = 0; i < 4; i++) {
                int newX = top.x + X[i];
                int newY = top.y + Y[i];
                if(judge(newX, newY)){
                    Node n = new Node();
                    n.x = newX;
                    n.y = newY;
                    q.offer(n);
                    inq[newX][newY] = true;
                }
            }
        }
    }

    private static boolean judge(int x, int y) {
        if(x<0 || x>=n || y<0 || y >=m) {
            return false;
        }
        if (a[x][y] == 0) {
            return false;
        }
        if(inq[x][y]) {
            return false;
        }

        return true;
    }
}
