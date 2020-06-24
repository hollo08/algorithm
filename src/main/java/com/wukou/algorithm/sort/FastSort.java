package com.wukou.algorithm.sort;

import com.wukou.algorithm.Util;

/**
 * Divide and Conquer is principle of FastSort
 */
public class FastSort {
    public static void main(String[] args) {
        int[] a = new int[]{6, 5, 7, 2, 4, 8, 11, 4, 10};
        sort(a, 0, a.length - 1);
        int t = 10;
        for (; ; t--) {
            Util.println(t);
        }
        //Util.printArray(a);
    }

    public static void sort(int[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        int low = l;
        int high = r;
        int key = array[low];//key is a reference value, bigger is on the right, smaller is one the left.
        while (low < high) {
            Util.println(low);
            for (; ; high--) {
                if (high <= low) {
                    break;
                }
                if (array[high] < key) {
                    array[low] = array[high];
                    break;
                }
            }
            for (; ; low++) {
                if (high <= low) {
                    break;
                }
                if (array[low] > key) {
                    array[high] = array[low];
                    break;
                }
            }
        }
        if (low == high) {
            array[low] = key;
        }
        //Separate the arrays and deal with them separately;
        //sort(array, l, low - 1);
        //sort(array, low + 1, r);

    }
}
