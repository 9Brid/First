package han01;
//演示HashMap使用

import java.util.HashMap;

public class q09_1{
	
	public static void main(String arg[]) {
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		m.put("china", 5);
		System.out.println(m.get("hello"));
		System.out.println(m.get("china"));
		System.out.println(m.containsKey("china"));
	}
}