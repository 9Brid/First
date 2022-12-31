package test01;

public class Solution {

    public static void main(String[] args) {
//        int k = 2;
//        int[] prices = {3,2,6,5,0,3};
////        int[] prices = {2, 4 ,1};
//        Map<Integer, Integer> map = new HashMap<>();
//        int max1=0, max2 = 0;
//        for (int i = 0; i < prices.length-1; i++) {
//            for (int j = prices.length-1; j > i ; j--) {
//                max1 = Math.max(max1, prices[j] - prices[i]);
//                if(!map.containsKey(max1)) {
//                    map.put(max1, j);
//                }
//            }
//        }
//        for (int i = map.get(max1) + 1; i < prices.length-1; i++) {
//            for (int j = prices.length-1; j > i ; j--) {
//                max2 = Math.max(max2, prices[j] - prices[i]);
//            }
//        }
//
//        System.out.println(max1+max2);












        int k = 2;
        int[] prices = {2,1,2,0,1};
//        int[] prices = {2, 4 ,1};
        int sum = 0, temp = -1;
        for (; k >0 ;k--){
            int len = 0;
            if(k == 2) {
                len = 1;
            } else  {
                len = 0;
            }
            int max1 = 0;
            for (int i = temp + 1; i < (prices.length + len)/k; i++) {
                for (int j = i + 1; j < (prices.length + len)/k; j++) {
                    if((prices[j] - prices[i] ) > max1) {
                        max1 = prices[j] - prices[i];
                        temp = j;
                    }
                }
            }
            sum += max1;
        }
        System.out.println(sum);
    }
}
