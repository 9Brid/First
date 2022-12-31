package test01;

import java.util.*;

public class Mianshi02 {
    public static void main(String[] args) {
        int[][] a = {{5 , 85, 4 , 2 , 68},
                     {78, 82, 75, 41, 14},
                     {79, 47, 38, 1 , 47},
                     {1 , 2 , 14, 65, 13},
                     {96, 87, 32, 14, 21}};
        int[][] b = new int[5][5];

        int[] x = {1, 1 , 0 , -1, -1, -1, 0, 1};
        int[] y = {0, -1, -1, -1, 0 , 1 , 1, 1};

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                List<Integer> temp = new ArrayList<Integer>();
                for (int k = 0; k < 8; k++) {
                    int xx = i+x[k];
                    int yy = j+y[k];
                    if (xx<0 || xx>4 || yy<0 || yy>4){
                        continue;
                    }
                    temp.add(a[xx][yy]);
                }
                Collections.sort(temp);
//                System.out.println(temp.size() + " " + temp.size()/2);
//                for (int k = 0; k < temp.size(); k++) {
//                    System.out.print(temp.get(k) + " ");
//                }
//                System.out.println();
                b[i][j] = temp.get(temp.size()/2 );
                System.out.print(b[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
