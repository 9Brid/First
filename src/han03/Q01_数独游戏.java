package han03;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setIn;

/*
需要把一个9 ×9的数独补充完整，使得图中每行、每列、每个3 ×3的九宫格内数字1~9均恰好出现一次。

输入格式
输入包含多组测试用例。
每个测试用例占一行，包含81个字符，代表数独的81个格内数据（顺序总体由上到下，同行由左到右）。
每个字符都是一个数字（1-9）或一个”.”（表示尚未填充）。
您可以假设输入中的每个谜题都只有一个解决方案。
文件结尾处为包含单词“end”的单行，表示输入结束。
输出格式
每个测试用例，输出一行数据，代表填充完全后的数独。
输入样例：
4.....8.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......
......52..8.4......3...9...5.1...6..2..7........3.....6...1..........7.4.......3.
end

ones[]表格是查询一个数的二进制表示里面有几个1。map表格是用于查询二进制1在哪一位上，如1-->第0位，1000-->第3位。
row[N], col[N], cell[3][3]分别用于记录每一行、每一列、每一个九宫格里面的9个数字的出现情况，
如第0行有数字8，则row[0]的靠左边的第8-1=7位的二进制位为0，第0行有数字4，则row[0]的靠左边的第4-1=3位的二进制位为0，还有第5-1=4位为0，row[0]的其余位都是1，表示这些数字没有出现过。
优化搜索顺序，找出一个选择性最少的位置开始搜索，也就是row[x] & col[y] & cell[x/3][y/3]的二进制数，1的bit位最少的位置，
也即该行、该列、该九宫格没有出现过的数字选择最少。注意现场保护、现场恢复。如上图(6, 1)位置选择最少。
修改二进制的某一位，可以使用+ or -(1<<i)，也可以使用按位或|(1 << i) or & ~(1 << i)

用bitset能得到正确答案，代码也短很多，即使加上map[]小抄（这个表格是为了提速，直接有相应函数count()故不需要ones[]小抄），但还是会超时。
*/
public class Q01_数独游戏 {
    //M = 1 * 2^9
    static int N = 9, M = 1 << N;
    static int[] ones = new int[M];
    static int[] map = new int[M];
    static int[] row = new int[N], col = new int[N];
    static int[][] cell = new int[3][3];
    static char[]  str = new char[100];

    static void init() {
        //二进制中的用1表示未用,用0表示已用方便后面&运算
        /*
        row[N], col[N], cell[3][3]分别用于记录每一行、每一列、每一个九宫格里面的9个数字的出现情况，
        如第0行有数字8，则row[0]的靠左边的第8-1=7位的二进制位为0，
        第0行有数字4，则row[0]的靠左边的第4-1=3位的二进制位为0，
        还有第5-1=4位为0，row[0]的其余位都是1，表示这些数字没有出现过。
        优化搜索顺序，找出一个选择性最少的位置开始搜索，
        也就是row[x] & col[y] & cell[x/3][y/3]的二进制数，1的bit位最少的位置，
        */
        for (int i = 0; i < N; i++) {
            row[i] = col[i] = (1 << N) - 1;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cell[i][j] = (1 << N) - 1;
            }
        }

        for (int i = 0; i < N; i++) {
            //map表格是用于查询二进制1在哪一位上，如1-->第0位，1000-->第3位。
            map[1 << i] = i;
        }

        for (int i = 0; i < (1 << N); i++) {
            int cnt = 0;
            for (int j = i; j != 0; j -= lowbit(j)) {
                cnt++;
            }
            //ones[]表格是查询一个数的二进制表示里面有几个1。
            ones[i] = cnt;
        }
    }

    //内联函数，减少函数调用的时间。取x的二进制数最右边的1和它右边的所有0，对于lowbit(二进制数110010000) = 二进制数10000，lowbit(二进制数1100100) = 二进制数100
    private static int lowbit(int x) {
        return x & -x;
    }

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han03/q01.txt"));
            setIn(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner stdin = new Scanner(in);

        while (true) {
            str = stdin.next().toCharArray();
            if (str[0] == 'e') {
                break;
            }
            //String.valueOf:返回的 char数组参数的字符串表示形式。
            System.out.println(String.valueOf(str));

            init();//初始化

            //打印地图
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.printf("%2c", str[i * N + j]);
                }
                System.out.println();
            }

            //num记录有多少个空位
            int num = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (str[i * 9 + j] != '.') {
                        int t = str[i * 9 + j] - '1';           //把（i, j）处的字符'1'~'9'转换成数字0~8，并更新三个状态数组
                        row[i] -= (1 << t);
                        col[j] -= (1 << t);
                        cell[i / 3][j / 3] -= (1 << t);
                    } else {
                        num++;
                    }
                }
            }

            dfs(num);

            System.out.println(String.valueOf(str));
            //打印地图
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.printf("%2c", str[i * N + j]);
                }
                System.out.println();
            }

            System.out.println("------------------------------------------");
        }
        stdin.close();
    }

    private static boolean dfs(int num) {
        if (num == 0) return true;

        int minx = N, x = 0, y = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (str[i * 9 + j] == '.') {
                    int t = ones[find(i, j)];
                    if (t < minx) {
                        minx = t;
                        x = i;
                        y = j;
                    }
                }
            }
        }

        int w = find(x, y);
        for (int i = w; i != 0; i -= lowbit(i)) {
            int j = map[lowbit(i)];
            str[x * 9 + y] = (char) (j + '1');
            row[x] -= (1 << j);
            col[y] -= (1 << j);
            cell[x / 3][y / 3] -= (1 << j);
            if (dfs(num - 1)) return true;
            str[x * 9 + y] = '.';            //现场恢复
            row[x] += (1 << j);
            col[y] += (1 << j);
            cell[x / 3][y / 3] += (1 << j);
        }

        return false;
    }

    private static int find(int x, int y) {
        return row[x] & col[y] & cell[x / 3][y / 3];
    }
}
