package han01;
//q02_走迷宫

/*
输入：
行数 列数
迷宫
起点横纵坐标 终点横纵坐标 

结果：
最少需要走多少步 
*/ 

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class q02 {
        static int N = 105;
        static int n,m;
        static char[][] a=new char[N][N];
        static boolean[][] inq = new boolean[N][N];
        static class node{
              int x,y,step;
        }
        static node S=new node();
        static node T=new node();
        static node Node=new node();
        static int X[]={0,1,0,-1};
        static int Y[]={1,0,-1,0};

    public static void main(String[] args) throws FileNotFoundException {
        //02_走迷宫.java
        // 输入重定向
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("src/han01/q02.txt"));
        System.setIn(in);
        Scanner stdin = new Scanner(System.in);
        n=stdin.nextInt();
        m=stdin.nextInt();
        System.out.println(n+" "+m);
        for (int i=0;i<n;i++){
            String b=stdin.next();
            for(int j=0;j<b.length();j++){
                a[i][j]=b.charAt(j);
            }
        }
        for (int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
        S.x=stdin.nextInt();
        S.y=stdin.nextInt();
        T.x=stdin.nextInt();
        T.y=stdin.nextInt();
        System.out.println(S.x+" "+S.y+" "+T.x+" "+T.y);

        S.step=0;
        int num=bfs();
        System.out.println(num);
        stdin.close();
    }

    private static int bfs() {
        Queue<node> q =new LinkedList<node>();
        q.offer(S);
        inq[S.x][S.y]=true;
        while (!q.isEmpty()){
            node top=q.element();
            q.poll();
            if(top.x==T.x&&top.y==T.y){
                return top.step;
            }
            for (int i=0;i<4;i++){
                int newX =top.x+X[i];
                int newY = top.y+Y[i];
                if(judge(newX,newY)){
                    node t=new node();
                    t.x=newX;
                    t.y=newY;
                    t.step=top.step+1;
                    q.offer(t);
                    inq[newX][newY]=true;
                }
            }
        }
        return 0;
    }

    private static boolean judge(int x, int y) {
        if(x<0||x>=n||y<0||y>=m) return false;
        if(a[x][y]=='*') return false;
        if(inq[x][y])return false;
        return true;
    }
}
