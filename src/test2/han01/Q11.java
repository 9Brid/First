package test2.han01;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

//q11_八数码问题
/*
在一个3×3的网格中，1~8这8个数字和一个“x”恰好不重不漏地分布在这3×3的网格中。
例如：
1 2 3
x 4 6
7 5 8
在游戏过程中，可以把“x”与其上、下、左、右四个方向之一的数字交换（如果存在）。
我们的目的是通过交换，使得网格变为如下排列（称为正确排列）：
1 2 3
4 5 6
7 8 x
例如，示例中图形就可以通过让“x”先后与右、下、右三个方向的数字交换成功得到正确排列。
交换过程如下：
1 2 3   1 2 3   1 2 3   1 2 3
x 4 6   4 x 6   4 5 6   4 5 6
7 5 8   7 5 8   7 x 8   7 8 x
现在，给你一个初始网格，请你求出得到正确排列至少需要进行多少次交换。
输入格式
输入占一行，将3×3的初始网格描绘出来。
例如，如果初始网格如下所示：
1 2 3
x 4 6
7 5 8
则输入为：1 2 3 x 4 6 7 5 8
输出格式
输出占一行，包含一个整数，表示最少交换次数。
如果不存在解决方案，则输出”-1”。
*/
public class Q11 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("src/han01/q11.txt"));
            System.setIn(bufferedInputStream);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(System.in);
        char[] in = new char[3];
        String start = new String();
        while (scanner.hasNext()) {
            in = scanner.next().toCharArray();
            start = new StringBuilder(start).append(in[0]).toString();
        }//2  3  4  1  5  x  7  6  8
        String end = "12345678x";
        System.out.println(bfs(start, end));
        scanner.close();
    }

    private static int bfs(String start, String end) {
        Queue<String> q = new LinkedList<String>();
        HashMap<String, Integer> inq = new HashMap<String, Integer>();
        q.offer(start);
        inq.put(start, 0);

        while (!q.isEmpty()) {
            String top = q.element();
            q.poll();
            if(top.equals(end)) {
                return inq.get(top);
            }

            int loc = top.indexOf("x"); //返回在top字符串中x的索引位置
            int x = loc / 3, y = loc % 3;
            int step = inq.get(top);
            int newx, newy, nloc;
            for (int i = 0; i < 4; i++) {
                newx = x + dx[i];
                newy = y + dy[i];
                nloc = newx * 3 + newy;

                if(!(newx<0 || newx>=3 || newy<0 || newy>=3)) {
                    char[] a = top.toCharArray();
                    char tmp;
                    tmp = a[loc];
                    a[loc] = a[nloc];
                    a[nloc] = tmp;
                    top = String.valueOf(a);

                    if (inq.get(top) == null) {
                        q.offer(top);
                        inq.put(top, step+1);
                    }
                    a = top.toCharArray();
                    tmp = a[loc];
                    a[loc] = a[nloc];
                    a[nloc] = tmp;
                    top = String.valueOf(a);
//                    top.replace(top.charAt(loc), top.charAt(nloc));
                }
            }
        }
        return -1;
    }
}
