package han01;
//q06_房间数_最大房间面积

/*
输入格式
第一行包含两个整数 m 和 n，分别表示南北方向的长度和东西方向的长度。
接下来 m 行，每行包含 n 个整数，每个整数都表示平面图对应位置的方块的墙的特征。
每个方块中墙的特征由数字 P 来描述，我们用1表示西墙，2表示北墙，4表示东墙，8表示南墙，P 为该方块包含墙的数字之和。
例如，如果一个方块的 P 为3，则 3 = 1 + 2，该方块包含西墙和北墙。
城堡的内墙被计算两次，方块(1,1)的南墙同时也是方块(2,1)的北墙。
输入的数据保证城堡至少有两个房间。

输出格式
共两行，第一行输出房间总数，第二行输出最大房间的面积（方块数）。

数据范围
1≤m,n≤50,
0≤P≤15

西：1
北：10
东：100
南：1000

注意输入数据很特殊。注意判断新位置是否有墙是从g[top.x}[top.y]>>i & 1，
分别向右移0、1、2、3位（分别代表西、北、东、南四个方向，需要与增量数组对应起来）与1按位与，
如果为1表示有墙，为0表示没有墙。）
*/

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;
public class q06 {
    static int N = 60;
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
	static Pair[] q =new Pair[N*N];
    static boolean[][] inq =new boolean[N][N];
    static int m,n;
    static int[] dx ={0,-1,0,1};
    static int[] dy ={-1,0,1,0};
    public static void main(String[] args) {
        // 输入重定向
    	try {
    		BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/q06.txt"));
    		System.setIn(in);
    		Scanner stdin = new Scanner(System.in);
    		m=stdin.nextInt();
    		n=stdin.nextInt();
    		System.out.println(n+" "+m);
    		for(int i=0;i<m;i++){
    			for (int j=0;j<n;j++){
    				g[i][j]= stdin.nextInt();
    			}
    		}
    		for(int i=0;i<m;i++){
    			for (int j=0;j<n;j++){
    				System.out.print(g[i][j]+" ");
    			}
    			System.out.println();
    			
    		}
    		int cnt=0,maxs=0;
    		
    		for (int i = 0; i < m; i++) {
    			for (int j = 0; j < n; j++) {
    				if(!inq[i][j]){
    					int tmp=bfs(i,j);
    					maxs=maxs>tmp?maxs:tmp;
    					cnt++;
    				}
    			}
    		}
    		System.out.println(cnt);
    		System.out.println(maxs);
    		stdin.close();
		} catch (Exception e) {
			System.out.println(e);
		}
    	
    }

    private static int bfs(int x, int y) {
        int front=0,rear=0;
        q[rear++]=new Pair(x,y);
        inq[x][y]=true;
        
        int cnt=0;
        while (front<rear){
            Pair  top = q[front++];
            cnt++;
            for (int i=0;i<4;i++){
                int newx=top.getX()+dx[i];
                int newy=top.getY()+dy[i];
                if(judge(newx,newy,top.getX(),top.getY(),i)){
                    q[rear++]=new Pair (newx,newy);
                    inq[newx][newy]=true;
                }
            }
        }
        return cnt;
    }

    private static boolean judge(int x, int y, int oldx, int oldy, int z) {
    	if (x<0||x>=m||y<0||y>=n) return false;
        if(inq[x][y])return false;
        int t1=(g[oldx][oldy]>>z&1);
        if((t1)!=0) return false;
        return true;
    }
}
