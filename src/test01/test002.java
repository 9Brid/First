package test01;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class test002 {
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(str);

        Map<Character , Integer> map = new HashMap<Character, Integer>();
        int max = 0;

        for (int i = 0; i < str.length(); i++) {
            if(map.containsKey(str.charAt(i))) {
                map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            } else {
                map.put(str.charAt(i), 1);
            }
            max = Math.max(max, map.get(str.charAt(i)));
        }
        System.out.println(max);
    }
}
