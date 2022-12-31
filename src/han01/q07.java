package han01;
//q07_山峰山谷数

/*
输入格式:
第一行包含一个正整数n，表示地图的大小。
接下来一个n×n的矩阵，表示地图上每个格子的高度w。
输出格式:
共一行，包含两个整数，表示山峰和山谷的数量。
数据范围
1≤n≤1000,
0≤w≤10^9

如果所有格子都有相同的高度，那么整个地图即是山峰，又是山谷。
山峰：周围没有比它更高滴 
山谷：周围没有比它更低滴
两不像：周围有比它更高滴也有比它更低滴 
*/

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class q07 {
    static int N=1010,M=N*N;
    static int[][] g =new int[N][N];
    static class Pair{
		int x,y;
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public Pair() {
			
		}
		public Pair(int x,int y) {
			this.x=x;
			this.y=y;
		}
	}
    
	static Pair [] q =new Pair[M];
    static boolean[][] inq =new boolean[N][N];
    static int n;
    static int[] dx ={-1,-1,-1,0 ,0, 1,1,1};
    static int[] dy ={-1, 0, 1,-1,1,-1,0,1};
    public static void main(String[] args) throws FileNotFoundException {
        // 输入重定向
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han01/q07_1.txt"));
        System.setIn(in);
        Scanner stdin = new Scanner(System.in);
        n=stdin.nextInt();
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j]=stdin.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(g[i][j]+" ");
            }
            System.out.println();
        }
        int peak=0,valley=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n; j++) {
                if(!inq[i][j]){
                    boolean[] hasHigher =new boolean[1],hasLower=new boolean[1];
                    hasHigher[0]=hasLower[0]=false;
                    bfs(i,j,hasHigher,hasLower);
                    if(!hasHigher[0]) peak++;
                    if(!hasLower[0]) valley++;
                }
            }
        }
        System.out.println(peak+" "+valley);
        stdin.close();
    }

    private static void bfs(int x, int y, boolean[] hasHigher, boolean[] hasLower) {
            int front=0,rear=0;
            q[rear++]=new Pair (x,y);
            inq[x][y]=true;

            while (front<rear){
                Pair top = q[front++];
                for (int i = 0; i < 8; i++) {
                    int newx =top.getX()+dx[i];
                    int newy =top.getY()+dy[i];

                    if(newx<0||newx>=n||newy<0||newy>=n) continue;
                    if(g[newx][newy]!=g[top.getX()][top.getY()]){
                        if(g[newx][newy]>g[top.getX()][top.getY()]){
                            hasHigher[0]=true;
                        }else {
                            hasLower[0]=true;
                        }
                    }else {
                        if(!inq[newx][newy]){
                            q[rear++]=new Pair(newx,newy);
                            inq[newx][newy]=true;
                        }
                    }
                }
            }
    }
}
