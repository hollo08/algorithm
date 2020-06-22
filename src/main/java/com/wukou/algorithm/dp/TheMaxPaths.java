package com.wukou.algorithm.dp;

public class TheMaxPaths {
    public static void main(String[] args) {
        System.out.println(uniquePath(3, 4));
    }
    public static int uniquePath(int m, int n){
        int[][] a = new int[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i==0 || j==0){
                    a[i][j] = 1;
                    continue;
                }
                a[i][j] = a[i-1][j] + a[i][j-1];
            }
        }
        return a[m-1][n-1];
    }
}
