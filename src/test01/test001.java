package test01;

import org.junit.Test;

import java.util.*;

public class test001 {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(34343);
        System.out.println(list1.get(0));


    }

    @Test
    public void test0001() {
        String  str1 = "123456789";
        str1.replace(str1.charAt(0), '4');
        System.out.println(str1);
        Scanner scanner  = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(str);


    }

    @Test
    public void test0002() {
        Scanner scanner  = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(str);

        Map<Character , Integer> map = new HashMap<Character, Integer>();
        int max = 0;

        for (int i = 0; i < str.length(); i++) {
            if(map.containsKey(str.charAt(i))) {
                map.put(str.charAt(i), map.get(str.charAt(i)));
            }
            max = Math.max(max, map.get(str.charAt(i)));
        }

        System.out.println(max);
    }
    
    @Test
    public void testboolean() {
        boolean[] st = new boolean[5];
        if (st[0]) {
            System.out.println(11111);
        }
    }

}
