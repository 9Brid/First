package han07;


import java.util.*;

public class p03spfa {
	/*
	 * 要想松弛后面的点，必须要更新过当前的点，如果这个点都没有更新过，那么更新后面的点也是无效的
	 适用范围：只要没有负环，都可以使用。
	    spfa也能解决权值为正的图的最短距离问题，且一般情况下比Dijkstra算法还好

	 时间复杂度：一般情况 O(m)，最坏情况下O(nm) 
	 1. 初始化dist[1]=0; dist[其他点] = 正无穷
	 2. 队列不空
	    1. 只有这个点被更新后，其他与它相连的点才会被更新

	*/
	static int N=100010;
	static int n,m;
	//邻接表建图的模板
	static int h[]=new int[N],e[]=new int[N],ne[]=new int[N],w[]=new int[N];
	static int idx;
	
	static int[] dist=new int[N];       //每个节点到起点的距离
	static boolean[] st=new boolean[N]; //st用来记录哪些点现在在队列里面，如果在里面不需要重复添加
	static int INF=0x3f3f3f3f;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		Arrays.fill(h, -1);             //初始头节点为-1
        while (m-- > 0) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            add(a, b, c);
        }

        int res = spfa();
        if (res == -1) System.out.println("impossible");
        else System.out.println(res);
	}
	
	//邻接表建图的模板
	static void add(int a,int b,int c) {
		e[idx] = b;
		ne[idx]= h[a];
		w[idx] = c;
		h[a]   = idx++;
	}
	
	static int spfa() {
	    Queue<Integer> q=new LinkedList<>();         //注意<>中是Integer，而不是int；注意是new LinkedList<>() 
		Arrays.fill(dist, INF);                      //初始化距离为INF
		dist[1]=0;
		q.offer(1); 
		st[1]=true;
		
		while(!q.isEmpty()) {                        //队列里面存储的是被更新过的节点，
			int t=q.poll();
			st[t]=false;                             //标志为false，让这个点可以再次加入队列
			
			                                         //重边不会被影响到，dist数组里面最后肯定是存储最小的
			for(int i=h[t];i!=-1;i=ne[i]) {
				int j=e[i];
				//进行距离最小值更新
				if(dist[j]>dist[t]+w[i]) {
					dist[j]=dist[t]+w[i];
					if(!st[j]) { // 如果这个点没有加入队列，这里是防止点重复被加入。
						q.offer(j);
						st[j]=true;
					}
				}
			}
		}
		
		// 如果这个点不可达，就一定不会被更新，
		if(dist[n]==INF) {
			return -1;
		}
		return dist[n];
	}
}



