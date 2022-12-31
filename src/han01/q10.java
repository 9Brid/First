package han01;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class q10 {

    static String [] a = new String[6];
    static String [] b = new String [6] ;
    static String A, B ;
    static HashMap<String,Integer> d1 = new HashMap<>() ; 
    static HashMap<String,Integer> d2 = new HashMap<>() ;
    static int cnt ;
    public static void main (String [] args) throws FileNotFoundException{
    	// 输入重定向
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("./src/han01/q10.txt"));
        System.setIn(in);
        Scanner sc = new Scanner(System.in);
        
        A = sc.next();
        B = sc.next() ;
        while (sc.hasNext()) {
            a[cnt] = sc.next();
            b[cnt] = sc.next();
            cnt++;
        }
        int ret = bfs ();
        if (ret == 11) {
            System.out.println("NO ANSWER!");
        } else {
            System.out.println(ret);
        }
        sc.close();
    }

    static int bfs (){
        Queue<String> q1 = new LinkedList<>();
        Queue<String> q2 = new LinkedList<>();
        q1.offer(A);
        q2.offer(B);
        d1.put(A, 0) ;
        d2.put(B, 0) ;
        int step = 0 ;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            int t = 0 ;
            if(q1.size() <= q2.size()) {
               t = extend (q1, d1, d2, a, b);          
            } else {
               t = extend (q2, d2, d1, b ,a) ;     
            }
            if (t <= 10) return t ;
            if (++step == 10) {
                break ;
            }
        }
        return 11 ;
    }

    static int extend (Queue<String> q, HashMap<String,Integer> da, HashMap<String,Integer> db , String [] x,
      String [] y){
        int d = da.get(q.peek()) ;
        while (!q.isEmpty() && d == da.get(q.peek())) {
            String cur = q.poll();
            int n = cur.length();
            for (int i = 0; i < n ; ++i) {
                for (int j = i ; j < n ; ++j) {
                    String sub = cur.substring(i, j + 1) ;
                    for (int k = 0 ; k < cnt ; ++k) {
                        if (sub.equals(x[k])) {
                            String next = cur.substring(0, i) + y[k] + cur.substring(j + 1 , n) ;
                            if (db.containsKey(next)) {
                                return da.get(cur) + 1 + db.get(next);
                            }
                            if (da.containsKey(next)) continue ;
                            da.put(next, da.get(cur) + 1) ;
                            q.offer(next);
                        }
                    }
                }
            }
        }
        return 11 ;
    }

}