package com.wukou.algorithm.dp;

import com.wukou.algorithm.Util;

public class TheLongestConsecutiveString {
    public static void main(String[] args) {
        int[] a = {23,33,55,33,1,2,3,4,98,22,100};
        int[] b = {1,1,1,1,1,1,1,1,1,1,1};
        getSubString(a, b, a.length);
        Util.printArray(b);
    }

    public static void getSubString(int[] a, int[] b, int length){
        int count = 0;
        for(int i=0; i<length; i++){
            int max = 1;
            for(int j=0; j<i; j++){
                if(a[i]>a[j]){
                    int l = b[j] + 1;
                    if(l > max){
                        max = l;
                    }
                }
                ++count;
            }

            b[i] = max;
        }
        System.out.println(count);
    }
}
