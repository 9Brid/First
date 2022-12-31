package han01;
//q09_魔板变换
/*
8个大小相同的格子的魔板：
1 2 3 4
8 7 6 5
我们知道魔板的每一个方格都有一种颜色。
这8种颜色用前8个正整数来表示。
可以用颜色的序列来表示一种魔板状态，规定从魔板的左上角开始，沿顺时针方向依次取出整数，构成一个颜色序列。
对于上图的魔板状态，我们用序列(1,2,3,4,5,6,7,8)?来表示，这是基本状态。
这里提供三种基本操作，分别用大写字母 A，B，C 来表示（可以通过这些操作改变魔板的状态）：
A：交换上下两行；
B：将最右边的一列插入到最左边；
C：魔板中央对的4个数作顺时针旋转。
示范：
A：
8 7 6 5
1 2 3 4
B：
4 1 2 3
5 8 7 6
C：
1 7 2 4
8 6 3 5
对于每种可能的状态，这三种基本操作都可以使用。
你要编程计算用最少的基本操作完成基本状态到特殊状态的转换，输出基本操作序列。
注意：数据保证一定有解。
输入格式
输入仅一行，包括8个整数，用空格分开，表示目标状态。
输出格式
输出文件的第一行包括一个整数，表示最短操作序列的长度。
如果操作序列的长度大于0，则在第二行输出字典序最小的操作序列。
*/
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class q09{
	static class Pair{
		char x;
		String y;
		public char getX() {
			return x;
		}
		public String getY() {
			return y;
		}
		public Pair() {
			
		}
		public Pair(char x,String y) {
			this.x=x;
			this.y=y;
		}
	}
	static HashMap<String,Integer > inq = new HashMap<String,Integer>();
	static HashMap<String,Pair> pre = new HashMap<String,Pair>();
	static char[][] arr = new char[2][4];
	
	public static void main(String arg[]) throws FileNotFoundException {

			// 输入重定向
	        BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han01/q09.txt"));
	        System.setIn(in);
	        Scanner stdin = new Scanner(System.in);
	        StringBuilder start=new StringBuilder (),end = new StringBuilder();
	        for (int i = 0; i < 8; i++) {
				int temp;
				temp=stdin.nextInt();
				end.append((char)(temp+'0'));
			}			
			for (int i = 1; i <= 8; i++) {
				start.append((char)(i+'0'));
			}
			int ans = bfs(start,end);
			
			if(ans==-1) {
				System.out.println("Impossible!");
			}else {
				System.out.println(ans);
			}
			
			StringBuilder str = new StringBuilder();
			while(!end.toString().equals(start.toString())) {
				str.append(pre.get(end.toString()).getX());
				end=new StringBuilder(pre.get(end.toString()).getY());
			}
			
			str.reverse();
			System.out.println(str);
			stdin.close();
	}
	
	private static int bfs(StringBuilder  s, StringBuilder  t) {
		Queue<StringBuilder> q = new LinkedList<StringBuilder >();
		q.offer(s);
		
		inq.put(s.toString(), 0);
		while (!q.isEmpty()) {
			StringBuilder top = q.element();
			q.poll();
			
			
			if(top.toString().equals(t.toString())) {
				return inq.get(top.toString());
			}
			StringBuilder[] str = new StringBuilder[3];
			str[0]=change0(top);
			str[1]=change1(top);
			str[2]=change2(top);
			for (int i = 0; i < 3; i++) {
				if(inq.containsKey(str[i].toString())) continue;
				q.offer(str[i]);
				inq.put(str[i].toString(), inq.get(top.toString())+1);
				pre.put(str[i].toString(),new Pair((char)('A'+i),top.toString()));
			}
			
		}
		
		return -1;
	}

	private static void set(StringBuilder s) {
		for (int i = 0; i < 4; i++) {
			arr[0][i]=s.charAt(i);
		}
		for (int i = 3,j=4; i >=0 ; i--,j++) {
			arr[1][i]=s.charAt(j);
		}
		
	}
	private static StringBuilder get() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			s.append(arr[0][i]);
		}
		for (int i = 3; i >= 0; i--) {
			s.append(arr[1][i]);
		}
		return s;
	}


	private static StringBuilder change0(StringBuilder s) {
		set(s);
		for(int i=0;i<4;i++) {
			char tmp= arr[0][i];
			arr[0][i]=arr[1][i];
			arr[1][i]=tmp;
		}
		return get();
	}
	private static StringBuilder change1(StringBuilder s) {
		set(s);
		char t0 = arr[0][3];
		char t1 = arr[1][3];
		for (int i = 2; i >= 0; i--) {
			arr[0][i+1]=arr[0][i];
			arr[1][i+1]=arr[1][i];
		}
		arr[0][0]=t0;
		arr[1][0]=t1;
		
		return get();
	}
	private static StringBuilder change2(StringBuilder s) {
		set(s);
		char temp = arr[0][1];
		arr[0][1]=arr[1][1];
		arr[1][1]=arr[1][2];
		arr[1][2]=arr[0][2];
		arr[0][2]=temp;
		return get();
	}
}