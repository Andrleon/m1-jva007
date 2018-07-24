package com.luxoft.basics.part3.xtasks;

import java.util.Arrays;

public class T1_Sort
{
    public static void main(String[] args) throws Exception
    {
        int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 9, 8};

        System.out.println(Arrays.toString(numbers));
        System.out.println();

        sortAsc(numbers);

        int[] arrToCheck = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("Is numbers sorted: " + String.valueOf(Arrays.equals(arrToCheck, numbers)).toUpperCase());
    }

    /**
     *
     * Method should sort data array ascending order.
     *
     */
    public static void sortAsc(int[] data)
    {
      int n = data.length;
      int newn;
      while (n !=0) {
          newn = 0;
          for (int i = 1; i <= n - 1; i++) {
              System.out.println("iter " + i);
              if (data[i - 1] > data[i]) {
                  System.out.println(data[i-1] + " swapped with " + data[i]);
                  swap(data, i - 1, i);
                  newn = i;
              }
          }
          n = newn;
      }

    }

    /**
     *
     * Method should exchange values with idx1 and idx2 in data array.
     *
     */
    public static void swap(int[] data, int idx1, int idx2)
    {
        data[idx2] += data[idx1];
        data[idx1] =  data[idx2] - data[idx1];
        data[idx2] -= data[idx1];

    }
}
