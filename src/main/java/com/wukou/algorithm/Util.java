package com.wukou.algorithm;

public class Util {
    public static void printArray(int[] a){
        StringBuilder sb =new StringBuilder("[");
        for (int aa:a) {
            sb.append(aa + ",");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    public static void println(Object o){
        System.out.println(o);
    }
}
