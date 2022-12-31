package han02;

import java.util.Scanner;

public class q01 {

	static int N=10;
	static int[] path =new int[N];
	static int n;
	static boolean[] st =new boolean[N];
	
	public static void main(String[] args) {
		
		Scanner st =new Scanner(System.in);
		n=st.nextInt();
		dfs(0);
		st.close();
	}
	private static void dfs(int u) {
		if(u==n) {
			for(int i=0;i<n;i++) {
				System.out.printf("%d ",path[i]);
			}
			System.out.println();
			return;
		}
		for(int i=1;i<=n;) {
			if(!st[i]) {
				path[u]=i;
				st[i]=true;
				dfs(u+1);
				st[i]=false;
				path[u]=0;
			}
			i++;
		}
	}
}
