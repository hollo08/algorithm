package com.wukou.algorithm;

public class Util {
    public static void printArray(int[] a){
        StringBuilder sb =new StringBuilder("[");
        for (int aa:a) {
            sb.append(aa + ",");
        }
        sb.append("]");
        System.out.print(sb.toString());
    }

    public static void println(Object o){
        System.out.println(o);
    }

    public static void testDivide(){
        int i = 100;
        int j = 6;
        System.out.println(i/j);
        System.out.println(i%j);
    }

    public static void main(String[] args) {
        testDivide();
    }
}
