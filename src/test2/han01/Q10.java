package test2.han01;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
已知有两个字串A,B及一组字串变换的规则（至多6个规则）:
A1->B1
A2->B2
…
规则的含义为：在?A?中的子串?A1?可以变换为?B1、A2?可以变换为?B2?…。
例如：A＝’abcd’B＝’xyz’
变换规则为：
‘abc’->‘xu’ ‘ud’->‘y’ ‘y’->‘yz’
则此时，A可以经过一系列的变换变为B，其变换的过程为：
‘abcd’->‘xud’->‘xy’->‘xyz’
共进行了三次变换，使得A变换为B。
输入：
多行变换规则
所有字符串长度的上限为 20。
输出格式
若在 10 步（包含 10步）以内能将A变换为B，则输出最少的变换步数；否则输出”NO ANSWER!”

双向BFS：要比单向BFS速度要快

决策的时候从字符串的起始位置依次去判断能否能用某一规则去替换，若能替换，则看替换后的字符串是否已经入队队列，
如果已经入过，则continue跳过再去判断其他的情况，如果没有入过，则看该替换过后的字符串是否入过另一个方向上的队列，
如果入过，则表示两个方向上的搜索已经碰头，返回该字符串在两个方向上所走过的步数之和，注意这里有个+1操作，因为在该方向上该字符串的步数还没有更新，
需要用top字符串的步数+1计算而得。注意两边的扩展方向是相反的，qa是正向扩展（abc-->xu），qb是反向扩展（xu-->abc），期望在中间汇合。
两种搜索方式：
一是传统方式的交替搜索，有可能会出现一边搜索空间非常大的可能，故一般不采用。
二是优化后的方式，通过判断两边的队列的大小，让小的那边进行扩展搜索，可保证两边搜索的时候比较平均，一般用这种方式比较好。
c++11里面才有unordered_map，如果编译环境很老，则只能使用普通的map，使用方式基本一样，只是map内部会以key从小到大的顺序进行排序，速度会稍慢。
*/
public class Q10 {
    static String[] a = new String[6]; //存规则
    static String[] b = new String[6];  //存规则
    static String A, B;
    static HashMap<String, Integer> d1 = new HashMap<String, Integer>();
    static HashMap<String, Integer> d2 = new HashMap<String, Integer>();
    static int cnt = 0;

    public static void main(String[] args) throws FileNotFoundException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("src/han01/q10.txt"));
        System.setIn(bufferedInputStream);
        Scanner stdin = new Scanner(System.in);
        /**
         * *next()：当输入到空格结束
         * nextline()：但输入到回车结束
         */
        A = stdin.next();
        B = stdin.next();
        while (stdin.hasNext()) {
            a[cnt] = stdin.next();
            b[cnt] = stdin.next();
            cnt++;
        }
        int ret = bfs();
        if(ret == 11) {
            System.out.println("NO ANSWER!");
        } else {
            System.out.println(ret);
        }
        stdin.close();
    }

    private static int bfs() {
        Queue<String> q1 = new LinkedList<>();
        Queue<String> q2 = new LinkedList<>();
        q1.offer(A);
        q2.offer(B);
        d1.put(A, 0);
        d2.put(B, 0);
        int step = 0;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            int t = 0;
            if(q1.size() <= q2.size()) {
                t = extend (q1, d1, d2, a, b);
            } else {
                t = extend (q2, d2, d1, b, a);
            }
            if (t <= 10) {
                return t;
            }
            if (++step ==  10) {
                break;
            }
        }
        return 11;
    }

    private static int extend(Queue<String> q, HashMap<String, Integer> da, HashMap<String, Integer> db, String[] x, String[] y) {
        int d = da.get(q.peek());
        while (!q.isEmpty() ) {
            String cur = q.poll();
            int n = cur.length();
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    String sub = cur.substring(i, j + 1);
                    for (int k = 0; k < cnt; k++) {
                        if(sub.equals(x[k])) {
                            String next = cur.substring(0, i) + y[k] + cur.substring(j + 1, n);
                            if (da.containsKey(next)) {
                                continue;
                            }
                            if(db.containsKey(next)){
                                return da.get(cur) + 1 + db.get(next);
                            }
                            da.put(next, da.get(cur) + 1);
                            q.offer(next);
                        }
                    }
                }
            }
        }
        return 11;
    }
}
