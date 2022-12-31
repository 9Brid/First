package han02;

import java.util.Scanner;

/**
 * N皇后问题是指将 n 个皇后放在 n*n 的国际象棋棋盘上，使得皇后不能相互攻击到，即任意两个皇后都不能处于同一行、同一列或同一斜线上。
 *
 * 两种思路：主对角线、反对角线在y轴上的截距大小为2*N。
 * 1.因为每行都需要放一个皇后，所以按行去枚举。当r==n即枚举完所有行后就是所求。
 * 当枚举每一行时，再去判断某一列是否可行？若可行则再去判断主对角线是否可行？若可行则再去判断反对角线是否可行？
 * 若也可行则表示该行该列可以放一个皇后，然后再去处理下一行dfs(r+1)。注意返回时的现场恢复。
 *
 * 2.从左上角那个位置开始，依次向右枚举，当枚举完该行最后一个位置后，行数+1，列数=0，相当于从下一行的最左边那个位置枚举。
 * 对于每个位置存在两种选择:
 * 一是该位置不放皇后，直接取下一个位置（c+1）进行操作dfs(r, c+1, s)。
 * 二是该位置打算放皇后，但是先要取判断该位置是否可行，即该行该列该对角线该反对角线之前都不能放过皇后，如
 * 果都没有放过，那么就在该位置上放上一个皇后（s+1），再跳到下一个位置（c+1）进行操作dfs(r, c+1, s+1)。注意返回时的现场恢复。
 *
 * 第2种思路速度要稍微慢一些。
 */
public class q02 {
	static int N=10;
	static char[][] g= new char[N][N];
	static boolean[] col=new boolean[N];
	static boolean[] dg=new boolean[2*N];
	static boolean[] udg=new boolean[2*N];
	static int n;
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		n=in.nextInt();
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				g[i][j]='.';
				
			}
		}
		dfs(0);
		in.close();
	}
	private static void dfs(int r) {
		if(r==n) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					System.out.print(g[i][j]);
				}
				System.out.println();
			}
			System.out.println();
			return;
		}
		for(int c=0;c<n;c++) {
			if(!col[c]&&!dg[r-c+n]&&!udg[r+c]) {
				g[r][c]='Q';
				col[c]=dg[r-c+n]=udg[r+c]=true;
				dfs(r+1);
				col[c]=dg[r-c+n]=udg[r+c]=false;
				g[r][c]='.';
			}
		}
	}
}
