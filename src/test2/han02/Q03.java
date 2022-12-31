package test2.han02;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * ’#’表示障碍物，不能走，’.’表示能走， 输入格式 第1行是测试数据的组数 k，后面跟着 k 组输入。 每组测试数据的第1行是一个正整数
 * n，表示迷宫的规模是 n*n 的。 接下来是一个 n?n 的矩阵，矩阵中的元素为.或者#。 再接下来一行是 4 个整数是起点、终点的横纵坐标（全部是从 0
 * 开始计数的）
 *
 * 输出格式 k行，每行输出对应一个输入。
 *
 * 能办到则输出“YES”，否则输出“NO”。
 *
 * 数据范围 1≤n≤100
 *
 * 注意如果起点或终点是’#’，那么肯定是不通的，直接输出NO。 注意bfs()的返回值类型int不能写成bool，不是就出错了。
 * 注意dfs的几个return处的写法。 dfs只能判断是否可达，但是不方便求出最短的步数，但是如果只是求可达性，那么用dfs代码稍微要短一点。
 */
public class Q03 {
    static int N = 110;
    static char[][] g = new char[N][N];
    static boolean[][] vis = new boolean[N][N];
    static int x1, y1, x2, y2;
    static int n;
    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, -1, 0, 1};

    public static void main(String[] args) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("./src/han02/03.txt"));
            System.setIn(bufferedInputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(System.in);

        int k = scanner.nextInt();
        while (k > 0) {
            System.out.println("-------------" + k + "---------------");
            n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                g[i] = scanner.next().toCharArray();
                System.out.println(g[i]);
            }
            x1 = scanner.nextInt();
            y1 = scanner.nextInt();
            x2 = scanner.nextInt();
            y2 = scanner.nextInt();
            System.out.println("x1:" + x1 + " y1:" + y1 + "    x2:" + x2 + " y2:" + y2);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    vis[i][j] = false;
                }
            }

            if(g[x1][y1] == '#' || g[x2][y2] == '#') {
                System.out.println("NO");
            } else {
                boolean ans = dfs(x1, y1);
                if(ans) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
            k--;
        }
        scanner.close();
    }

    private static boolean dfs(int x, int y) {
        if (x==x2 && y==y2) {
            return true;
        }
        vis[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newx = x + dx[i];
            int newy = y + dy[i];

            if(newx<0 || newx>=n || newy<0 || newy>=n) {
                continue;
            }
            if(g[newx][newy] == '#') {
                continue;
            }
            if(vis[newx][newy]) {
                continue;
            }

            if(dfs(newx, newy)) {
                return true;
            }
        }
        return false;
    }
}
