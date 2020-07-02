package com.example.algorithm;

/**
 * 希尔排序
 */
public class ShellSort {

    public static int[] shellSort(int[] arr) {
        int gap = 1;
        while (gap < arr.length) {
            gap = gap * 4+ 1;
        }

        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            gap = (int) Math.floor(gap / 4);
        }
        return arr;
    }

    public static void main(String[] args) {
        int [] str = {7,2,92,6,0,23,55,12,656,88};
        int [] code =  shellSort(str);
        for(int d:code){
            System.out.print(d + " ");
        }
    }
}
