package com.luxoft.basics.part2.tasks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Sort {

    public static void main (String[] args){
        int[] q = {4, 49, 4, 1, 0, 22, 876, 12, 2, 777, 65};
        System.out.println("Start buble sort");
        long start = System.currentTimeMillis();
        int[] w =  sort(q);
        System.out.println("Sorting took " + (System.currentTimeMillis() - start) + " milliseconds");
        System.out.println(Arrays.toString(w));
        System.out.println("Start Arrays sort");
        start = System.currentTimeMillis();
        Arrays.sort(q);
        System.out.println("Sorting took " + (System.currentTimeMillis() - start) + " milliseconds");
        System.out.println(Arrays.toString(q));
    }

    public static int[] sort(int[] a){
      int temp;
      int l = a.length;
      for (int i = 1; i <= l; i++){
          for (int j = 0; j < l - i; j++){
              if (a[j] > a[j+1]) {
                  temp = a[j];
                  a[j] = a[j+1];
                  a[j+1] = temp;
              }

          }

      }

      return a;
    }


}




