package test2.han01;
//q05_抓老牛所需时间

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
    农夫知道一头牛的位置，想要抓住它。
    农夫和牛都位于数轴上，农夫起始位于点N，牛位于点K。
    农夫有两种移动方式：
    从X?移动到X?1?或X+1，每次移动花费一分钟
    从X?移动到2?X，每次移动花费一分钟
    假设牛没有意识到农夫的行动，站在原地不动。
    农夫最少要花多少时间才能抓住牛？
    输入格式
    共一行，包含两个整数N和K。
    输出格式
    输出一个整数，表示抓到牛所花费的最少时间。
    数据范围
    0≤N,K≤10^5
    输入样例：
    5 17
    输出样例：
    4
*/
public class Q05 {
    static int N  = 100010, M = 2*N;
    static int[] q = new int[M];
    static int[] inq = new int[M];

    public static void main(String[] args) throws FileNotFoundException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("src/han01/q05.txt"));
        System.setIn(inputStream);
        Scanner stdin = new Scanner(System.in);
        int n, k;
        n = stdin.nextInt();
        k = stdin.nextInt();
        int ans = bfs(n, k);
        if(ans == -1) {
            System.out.println("Impossable!");
        } else  {
            System.out.println(ans);
        }
        stdin.close();
    }

    static private int bfs(int s, int t) {
        int front = 0, rear = 0;
        for (int i = 0; i < M; i++) {
            inq[i] = -1;
        }
        q[rear++] = s;
        inq[s] = 0;
        while (front < rear) {
            int top = q[front++];
            if(top == t) {
                return inq[top];
            }
            if(top+1<M && inq[top+1]==-1) {
                q[rear++] = top+1;
                inq[top+1] = inq[top] + 1;
            }
            if(top+1>0 && inq[top-1]==-1) {
                q[rear++] = top-1;
                inq[top-1] = inq[top]+1;
            }
            if(top*2<M && inq[top*2]==-1) {
                q[rear++] = top * 2;
                inq[top*2] = inq[top] + 1;
            }
        }
        return -1;
    }
}
