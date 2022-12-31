package test01;

public class Mianshi01 {
    public  void abc(int a, int[] b, Integer c){
        a++;
        b[0]++;
        c++;
    }

    public static void main(String[] args) {
        int a = 0;
        int[] b = {0};
        Integer c = 0;
//        mianshi01.abc(a, b, c);
//        this.abc(a, b, c);
        Mianshi01 m= new Mianshi01();
        m.abc(a, b, c);

        System.out.println(a);
        System.out.println(b[0]);
        System.out.println(c);

    }

}
