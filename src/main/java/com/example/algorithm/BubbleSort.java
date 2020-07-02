package com.example.algorithm;

/**
 * 冒泡排序
 * 冒泡排序（Bubble Sort）也是一种简单直观的排序算法。它重复地走访过要排序的数列，
 * 一次比较两个元素，如果他们的顺序错误就把他们交换过来
 */
public class BubbleSort {

    /**
     * 前后两个数进行比较，每次循环后最大值排在最后一列，时间复杂度为 n(n-1)/2
     * 即O( )
     */

    public static int[] bubbleSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        return arr;
    }


    public static void main(String[] args) {
        int [] str = {7,2,92,6,0,23,55,12,656,88};
        int[] dd = bubbleSort(str);
        for(int i:dd){
            System.out.print(i + " ");
        }

    }
}
