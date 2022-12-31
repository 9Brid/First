package test2.han02;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
现在我们已知一组单词，且给定一个开头的字母，要求出以这个字母开头的最长的“龙”，每个单词最多被使用两次。
在两个单词相连时，其重合部分合为一部分，例如 beast 和 astonish ，如果接成一条龙则变为 beastonish。
我们可以任意选择重合部分的长度，但其长度必须大于等于1，且严格小于两个串的长度，例如 at 和 atide 间不能相连。

输入格式
输入的第一行为一个单独的整数 n 表示单词数，以下 n 行每行有一个单词（只含有大写或小写字母，长度不超过20），输入的最后一行为一个单个字符，表示“龙”开头的字母。
你可以假定以此字母开头的“龙”一定存在。

输出格式
只需输出以此字母开头的最长的“龙”的长度。

数据范围
n≤20

输入样例：
5
at
touch
cheat
choose
tact
a
输出样例：
23
提示
连成的“龙”为 atoucheatactactouchoose

预处理字符串a的后缀与字符串b的前缀的最小匹配是多少，因为匹配最小最后得到的单词龙的长度才会更长，最小匹配要> 1且< min(a.length(), b.length())，
找到最小匹配后直接break跳出循环，需对各种字符串的组合进行处理，包括跟自身字符串。dfs(上一次的单词龙字符串，上一次的字符串编号)，
先用上一次的单词龙字符串于全局变量len求最大值，然后再在上一次的单词龙字符串后面依次拼接各个可以拼接的字符串，并记录拼接的字符串编号。
注意现场保护、现场恢复的处理。memset(uesd, 0, sizeof(vis))可以不需要，因为现场恢复后used状态数组会清零，保证了在处理下一组测试数据的时候不会出错。
*/
public class Q06 {
    static int N = 25;
    static int n;
    static String[] word = new String[N];
    static int[] used = new int[N];
    static int[][] g = new int[N][N];
    static char[] start = new char[2];
    static int len;

    private static void dfs(String str, int last) {
        len = Math.max(str.length(), len);
        used[last]++;
        for (int i = 0; i < n; i++) {
            if(g[last][i] != 0 && used[i] < 2) {
                dfs(str + word[i].substring(g[last][i]), i);
            }
        }
        used[last]--;
    }

    public static void main(String[] args) throws FileNotFoundException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("./src/han02/06.txt"));
        System.setIn(bufferedInputStream);
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            word[i] = scanner.next();
            System.out.println(word[i]);
        }
        System.out.println();

//      初始化，单词与单词之间相同部分的长度
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String a = word[i];
                String b = word[j];
                for (int k = 1; k < Math.min(a.length(), b.length()); k++) {
                    String str1 = a.substring(a.length()-k);
                    String str2 = b.substring(0, k);
                    if(str1.equals(str2)) {
                        g[i][j] = k;
                    }
                }
            }
        }

        start = scanner.next().toCharArray();
        for (int i = 0; i < n; i++) {
            if(word[i].charAt(0) == start[0]) {
                dfs(word[i], i);
            }
        }
        System.out.println(len);
        scanner.close();
    }
}
