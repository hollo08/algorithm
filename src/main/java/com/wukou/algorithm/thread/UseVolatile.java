package com.wukou.algorithm.thread;

/**
 * Volatile
 * 1，直接从内存中读取数据，不从CPU缓存中读取
 * 2，禁止指令重排，volatile不重排CPU指令
 */
public class UseVolatile {
    private static volatile boolean a;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    //if a not use volatile, a cann't equels !a, but when a has volatile, a may equels !a; because a get value form RAM, not from cup cache.
                    if(a == !a){
                        System.out.println("a == true");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    System.out.println(" a = !a");
                    a = !a;
                }
            }
        });

        t1.start();
        t1.sleep(100);
        t2.start();
    }
}

