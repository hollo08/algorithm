package com.wukou.algorithm.dp;

import com.wukou.algorithm.Util;

public class CoinChange {
    public static void main(String[] args) {
        int[] a = {2, 5, 7};
        int m = 27;
        System.out.println(change(a, m));
    }

    /**
     *
     * @param a coin types
     * @param m amount of count money
     * @return
     */
    public static int change(int[] a, int m){
        int[] f = new int[m+1];
        Util.printArray(f);
        int n = a.length;

        f[0] = 0;
        for(int i=1; i<=m; i++){
            f[i] = Integer.MAX_VALUE;
            for(int j=0; j<n; j++){
                if(i >= a[j] && f[i - a[j]] != Integer.MAX_VALUE){
                    f[i] = Math.min(f[i], f[i - a[j]] + 1);
                }
            }
        }

        if(f[m] == Integer.MAX_VALUE){
                f[m] = -1;
        }
        Util.printArray(f);
        return f[m];
    }
}
