package com.wukou.algorithm.thread;

import java.util.concurrent.CountDownLatch;

public class SafeThread {
    private static int i = 0;

    @lombok.SneakyThrows
    public static void main(String[] args) {
        CountDownLatch down = new CountDownLatch(100);
        Runnable run = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (SafeThread.class){
                    i++;
                }
                down.countDown();
            }
        };
        Thread t;
        for(int j=0; j<100; j++){
            t = new Thread(run);
            //t.join();
            t.start();
        }
        down.await();
        System.out.println(i);
    }
}
