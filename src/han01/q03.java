package han01;
//q03_走迷宫_打印路径

/*
输入：
行数 列数
迷宫
起点横纵坐标 终点横纵坐标 

结果：
最少需要走多少步，且把路径打印出来 
*/ 

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class q03 {
    static int N = 105;
    static int n,m;
    static char[][] a=new char[N][N];
    static boolean[][] inq = new boolean[N][N];
    static class node{
        int x,y,step;
    }
    static q02.node S=new q02.node();
    static q02.node T=new q02.node();
    static q02.node Node=new q02.node();
    static int X[]={0,1,0,-1};
    static int Y[]={1,0,-1,0};
    static node[][] path= new node[N][N];
    static {
        for(int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                path[i][j]=new node();
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
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

        System.out.println("路径打印方法一：");
        int x=T.x,y=T.y;
        Stack<node> s =new Stack<node>();
        for(int i=0;i<num;i++){
            int xx=path[x][y].x;
            int yy=path[x][y].y;
            node tmp=new node();
            tmp.x=xx;
            tmp.y=yy;
            tmp.step=path[x][y].step;
            s.push(tmp);
            x=xx;
            y=yy;
        }
        while (!s.empty()){
            node tmp= s.peek();
            s.pop();
            System.out.println("("+tmp.x+" "+tmp.y+" "+(char)tmp.step+")");
        }

        System.out.println("路径打印方法二：");
        print(T.x,T.y);
        stdin.close();

    }
    static void print(int x,int y){
        if(x==S.x&&y==S.y){
            return;
        }
        print(path[x][y].x,path[x][y].y);
        System.out.printf("(%d %d %c)\n",path[x][y].x,path[x][y].y,path[x][y].step);
    }
    private static boolean judge(int x, int y) {
        if(x<0||x>=n||y<0||y>=m) return false;
        if(a[x][y]=='*') return false;
        if(inq[x][y])return false;
        return true;
    }
    private static int bfs() {
        Queue<q02.node> q =new LinkedList<q02.node>();
        q.offer(S);
        inq[S.x][S.y]=true;
        while (!q.isEmpty()){
            q02.node top=q.element();
            q.poll();
            if(top.x==T.x&&top.y==T.y){
                return top.step;
            }
            for (int i=0;i<4;i++){
                int newX =top.x+X[i];
                int newY = top.y+Y[i];
                if(judge(newX,newY)){
                    q02.node t=new q02.node();
                    t.x=newX;
                    t.y=newY;
                    t.step=top.step+1;
                    q.offer(t);
                    inq[newX][newY]=true;

                    path[newX][newY].x=top.x;
                    path[newX][newY].y=top.y;
                    switch (i){
                        case 0:
                            path[newX][newY].step='E';
                            break;
                        case 1:
                            path[newX][newY].step='S';
                            break;
                        case 2:
                            path[newX][newY].step='W';
                            break;
                        case 3:
                            path[newX][newY].step='N';
                            break;
                    }
                }
            }
        }
        return 0;
    }
}
