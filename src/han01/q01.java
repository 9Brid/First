package han01;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
//q01_矩阵中的1的快数

/*
输入：
行数 列数
迷宫

结果：
图中1相连的块数 
*/
public class q01 {
    static int N =105;
    static int n,m;
    static int[][] a =new int[N][N];
    static boolean[][] inq = new boolean[N][N];
    static class node{
        int x,y;
    }
    static int X[]={0,1,0,-1};
    static int Y[]={1,0,-1,0};

    public static void main(String[] args) throws FileNotFoundException {
        // 输入重定向
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("src/han01/q01.txt"));
        System.setIn(in);
        Scanner stdin = new Scanner(System.in);
        n = stdin.nextInt();
        m = stdin.nextInt();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                a[i][j]=stdin.nextInt();
            }
        }
        //test
        System.out.println("n: "+n+",m: "+m);
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }

        int ans=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(a[i][j]==1&&inq[i][j]==false){
                    ans++;
                    bfs(i,j);
                }
            }
        }
        System.out.println(ans);
        stdin.close();
    }

    private static void bfs(int x, int y) {
        Queue<node> q = new LinkedList<node>();
        node Node = new node();
        Node.x=x;
        Node.y=y;
        q.offer(Node);//如果可能立即在不违反容量限制的情况下这样做的话，将指定的元素插入到队列中。使用容量限制队列时，这种方法通常比 add(E)，可不能只通过抛出异常，插入一个元素。
        inq[x][y]=true;
        while (!q.isEmpty()){
            node top =q.element();//查看第一个元素
            q.poll();//弹出第一个元素 //检索并移除此队列的头，或返回 null如果队列为空。
            for(int i=0;i<4;i++){
                int newX = top.x+X[i];
                int newY = top.y+Y[i];
                if(judge(newX,newY)){
                    node n = new node();
                    n.x=newX;
                    n.y=newY;
                    q.offer(n);
                    inq[newX][newY]=true;
                }
            }

        }
    }
    private static boolean judge(int x, int y) {
        if(x<0||x>=n||y<0||y>=m) return false;
        if(a[x][y]==0) return false;
        if(inq[x][y])return false;
        return true;
    }
}
