package com.luxoft.basics.part1.xtasks;

public class T0_Split
{
    public static void main(String[] args)
    {
        StatChck s = new StatChck();
        s.showI();
        StatChck.i = 42;
        s.showI();
        StatChck q = new StatChckExt();
        q.showI();
        StatChck s1 = new StatChck();


    }
}

class StatChck {
    static int i = 0;


    void showI(){
        System.out.println(i);
    }

}
class StatChckExt extends StatChck{
    static int i = 1;
    void showI(){
        System.out.println(i);
    }


}