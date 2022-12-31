package han02;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/*
给定n个正整数，将它们分组，使得每组中任意两个数互质。
至少要分成多少个组？
输入格式
第一行是一个正整数n。
第二行是n个不大于10000的正整数。
输出格式
一个正整数，即最少需要的组数。
数据范围
1≤n≤10

dfs()依次去处理数组当中的每一个元素，下标从0开始，当处理到下标n时表示数组中的所有元素处理结束，用该组方案的组数gNum与之前的得到的结果取最小值。
对于数组中的每个元素a[index]，先看看能不能加入到前面的gNum组中的第0组当中，如果可以，则继续处理下一个下标的元素，如果不可以，看看能不能加入到第1组当中，依次处理。
不管a[index]能不能加到前面的组当中，一定会添加到一个新的第gNum组当中，即该元素自成新的一组。注意现场保护、现场恢复的处理。最优化剪枝if(gNum >= ans) return;不加也可以AC，但是加了之后速度会加快4.5倍！

剪枝分为最优化剪枝、可行性剪枝等。
最优化剪枝是指当枚举一个分支的时候无论如何都不能是最优解了，那么直接干掉。
可行性剪枝是指当枚举某种情况时，不满足某些条件时直接干掉。

4
14 20 33 44

@  0  14   0   1
~  1  20   0
@  1  20   1   2
~  2  33   0
#  2  33   0   3
~  3  44   0
~  3  44   1
@  3  44   2   4
~  2  33   1
#  2  33   1   3
~  3  44   0
~  3  44   1
@  3  44   2   4
@  2  33   2   3
~  3  44   0
~  3  44   1
~  3  44   2
@  3  44   3   4
3
*/

public class q07 {
	static int N = 12;
	static int[] a = new int[N];

	static Vector<Vector<Integer>> group = new Vector<Vector<Integer>>();

	static int ans, gNum;
	static int n;

	static {
		// 初始化
		for (int i = 0; i < N; i++) {
			Vector<Integer> tmp = new Vector<>();
			group.add(tmp);
		}
	}

	private static int gcd(int a, int b) {

		return b != 0 ? gcd(b, a % b) : a;
	}

	private static boolean judge(int x, Vector<Integer> g) {
		for (int i = 0; i < g.size(); i++) {
			if (gcd(x, g.get(i)) > 1) {
				return false;
			}
		}
		return true;
	}

	private static void dfs(int index) {
		if (gNum >= ans)
			return;
		if (index == n) {
			ans = Math.min(ans, gNum);
			return;
		}
		for (int i = 0; i < gNum; i++) {
			if (judge(a[index], group.get(i))) {
				group.get(i).add(a[index]);
				dfs(index + 1);
				group.get(i).remove(group.get(i).size() - 1);// 移除最后的元素
			}
		}
		group.get(gNum++).add(a[index]);
		dfs(index + 1);
		int q = --gNum;
		group.get(q).remove(group.get(q).size() - 1);// 移除最后的元素
	}

	public static void main(String[] args) throws FileNotFoundException {
		// 输入重定向
		BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han02/07.txt"));
		System.setIn(in);
		Scanner stdin = new Scanner(System.in);

		n = stdin.nextInt();
		System.out.println("n:" + n);

		for (int i = 0; i < n; i++) {
			a[i] = stdin.nextInt();
		}

		ans = n;

		dfs(0);

		System.out.println("ans:" + ans);

		stdin.close();
	}

}
