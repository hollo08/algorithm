package com.wukou.algorithm.sort;

import com.wukou.algorithm.Util;

/**
 *
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] a = new int[]{1,5,6,7,3,4,5,6,7,3,3};
        mergeSort(a, 0, a.length-1);
        Util.printArray(a);
    }

    public static void mergeSort(int[] array, int l, int r){
        if(l == r){
            return;
        }
        int m = (l + r)/2;
        mergeSort(array, l, m);
        mergeSort(array, m+1, r);
        mergeArray(array, l, m, r);
    }

    public static void mergeArray(int[] a, int l, int m, int r){
        //start1 = l, end1 = m
        //start2 = m+1, end2 = r
        int[] temp = new int[r - l + 1];
        int i = l;
        int j = m + 1;
        int t = 0; //temp index
        while(i <= m && j <= r){
            if(a[i] < a[j]){
                temp[t++] = a[i++];
            }else{
                temp[t++] = a[j++];
            }
        }
        while(i <= m){
            temp[t++] = a[i++];
        }
        while(j <= r){
            temp[t++] = a[j++];
        }
        for(int x=0, y=l; x<temp.length; x++, y++){
            a[y] = temp[x];
        }
    }
}
