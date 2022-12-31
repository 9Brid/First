package test2.han02;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 输入格式
 * 输入包括多个数据集合。
 * 每个数据集合的第一行是两个整数 列数、行数
 * 每个字符表示一块瓷砖的颜色，规则如下
 *
 * 1）‘.’：黑色的瓷砖；
 * 2）‘#’：白色的瓷砖；
 * 3）‘@’：黑色的瓷砖，并且你站在这块瓷砖上。该字符在每个数据集合中唯一出现一次。
 *
 * 当在一行中读入的是两个零时，表示输入结束。
 *
 * 输出格式
 * 对每个数据集合，分别输出一行，显示你从初始位置出发能到达的瓷砖数(记数时包括初始位置的瓷砖)。
 *
 * 数据范围
 * 1≤W,H≤20
 */
public class Q04 {
    static int N = 20;
    static char[][] g = new char[N][N];
    static boolean[][] vis = new boolean[N][N];
    static int n, m;
    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, -1, 0, 1};

    public static void main(String[] args) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("./src/han02/04.txt"));
            System.setIn(bufferedInputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(System.in);

        m = scanner.nextInt();
        n = scanner.nextInt();
        while (m != 0 || n != 0) {
            for (int i = 0; i < n; i++) {
                g[i] = scanner.next().toCharArray();
            }
            int x = 0, y = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    vis[i][j] = false;
                    if(g[i][j] == '@') {
                        x = i;
                        y = j;
                    }
                }
            }
            System.out.println(dfs(x, y));
            m = scanner.nextInt();
            n = scanner.nextInt();
        }
        scanner.close();
    }

    private static int dfs(int x, int y) {
        int num = 1;
        vis[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newx = x + dx[i];
            int newy = y + dy[i];

            if(newx<0 || newx>=n || newy<0 || newy>=m) {
                continue;
            }
            if(g[newx][newy] == '#') {
                continue;
            }
            if(vis[newx][newy]) {
                continue;
            }
            num += dfs(newx, newy);
        }
        return num;
    }
}
