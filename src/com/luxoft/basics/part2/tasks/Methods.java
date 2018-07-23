package com.luxoft.basics.part2.tasks;

import javax.print.DocFlavor;
import java.util.Random;
import java.util.Scanner;

public class Methods {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        acquaintance();
        hello("Leonid!");

        for (int i = 0; i < 10; i++) {
            System.out.println(" Give me the number from 0 to 5");
            int n = sc.nextInt();
            int m = getRandomInt();
            System.out.println("My number is " + m);
            if (n == m)
                System.out.println("You won");
            else
                System.out.println("You lose");
        }

    }

    static void acquaintance(){
        System.out.println("Hello!!!! What is your name?");

    }

    static void hello(String name){
        System.out.println("Hello, " + name);

    }

    static int getRandomInt(){
        Random rand = new Random();
        return rand.nextInt(5);
    }

    static int getRandomInt(int bound){
        Random rand = new Random();
        return rand.nextInt(bound);
    }

}
