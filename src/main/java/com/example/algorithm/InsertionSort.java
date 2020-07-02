package com.example.algorithm;

public class InsertionSort {

    public static int[] insertionSort(int[] arr){
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {

            // 记录要插入的数据
            int tmp = arr[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }

        }
        return arr;
    }

    public static void main(String[] args) {
        int [] str = {7,2,92,6,0,23,55,12,656,88};
        int [] code =  insertionSort(str);
        for(int d:code){
            System.out.print(d + " ");
        }
    }
}
