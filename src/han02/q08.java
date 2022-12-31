package han02;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
金韬养了N只小猫，带着猫去爬山，准备花钱让它们坐索道上山。
索道上的缆车最大承重量为W，而N只小猫的重量分别是C1、C2……CN。
当然，每辆缆车上的小猫的重量之和不能超过W。
每租用一辆缆车，就要付1美元，问最少需要付多少美元才能把这N只小猫都运送上山？
输入格式
第1行：包含两个用空格隔开的整数，N和W。
第2..N+1行：每行一个整数，其中第i+1行的整数表示第i只小猫的重量Ci。
输出格式
输出一个整数，表示最少需要多少美元，也就是最少需要多少辆缆车。
数据范围
1≤N≤18,
1≤Ci≤W≤10^8

加了最优化剪枝（不可能产生最优解的方案了）if(carNum > ans) return;后，由不加之前的TLE到的AC的910ms，如果改成>=之后，速度由910ms提升至17ms，Oh, My God! 
当u==n时，说明已经处理完所有的小猫，由于前面已经加了最优化剪枝后，已经保证了carNum < ans，故也可以不用min求最小值。满足条件cat[u] + car[i] <= w才处理是可行性剪枝（该方案不可行了）。
优化搜索顺序，优先搜索分支较少的结点，从大到小排序后先处理重量大的猫，是因为重量大的猫的选择车的可能性更少一些，先处理重量大的猫可以使得搜索树靠近树根的分支就会更少一些，
这样暴搜的速度就加快了很多，搜索树靠近树根的分支数越多，就需要会速处理更多次！
如果加上最优化剪枝，但是从重量小的猫先处理的话，会TLE。
跟题目互质组问题很相似。

方二的dfs的第一次参数表示当前正在处理第u只猫，第二个参数表示当前有carNum辆车。
*/

public class q08 {

	static int N = 20;
	// static int[] cat = new int[N];
	// static int[] car = new int[N];

	static ArrayList<Integer> cat = new ArrayList<>();
	static ArrayList<Integer> car = new ArrayList<>();

	static int n, w, ans = N;

	public static void main(String[] args) throws FileNotFoundException {
		// 输入重定向
		BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han02/08.txt"));
		System.setIn(in);
		Scanner stdin = new Scanner(System.in);

		// 初始化car
		for (int i = 0; i < N; i++) {
			car.add(0);
		}

		n = stdin.nextInt();
		w = stdin.nextInt();

		for (int i = 0; i < n; i++) {
			cat.add(stdin.nextInt());
		}

		Collections.sort(cat);

		// System.out.println(cat);

		Collections.reverse(cat);

		// System.out.println(cat);

		dfs(0, 0);

		System.out.println("ans:" + ans);

		stdin.close();
	}

	//u:代表第几只猫
	private static void dfs(int u, int carNum) {
		if (carNum >= ans)
			return;
		if (u == n) {
			ans = carNum;
			return;
		}
		for (int i = 0; i < carNum; i++) {
			if (cat.get(u) + car.get(i) <= w) {
				car.set(i, car.get(i) + cat.get(u));
				dfs(u + 1, carNum);
				car.set(i, car.get(i) - cat.get(u));
			}
		}

		car.set(carNum, cat.get(u));

		dfs(u + 1, carNum + 1);
		car.set(carNum, 0);
	}

}
