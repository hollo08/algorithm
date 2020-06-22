package com.wukou.algorithm.thread;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class SafeThread {
    private int i = 0;
    private String iStr = "Thread : ";

    public static void main(String[] args) {
        SafeThread safe = new SafeThread();
        //safe.reentrantLock();
        //safe.syncLock();
        safe.semaphare();
    }

    @SneakyThrows
    public void semaphare(){
        //The Semaphore has two params, The Second param is fair lock;
        //FairLock means that which threads executes first acquires the lock firsts
        Semaphore semaphore = new Semaphore(3, false);
        Runnable r = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                semaphore.acquire();
                System.out.println("before                  " + name);
                Thread.sleep(2000);
                semaphore.release();
                System.out.println("after" + name);
            }
        };
        for (int i=0; i<10; i++){
            Thread t = new Thread(r);
            t.start();
            Thread.sleep(10);
        }
    }

    @lombok.SneakyThrows
    public void reentrantLock(){
        CountDownLatch down = new CountDownLatch(100);
        ReentrantLock lock = new ReentrantLock();
        Runnable run = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(100);
                try {
                    lock.lock();
                    i++;
                    iStr += " , " + Thread.currentThread().getName();
                }finally {
                    lock.unlock();
                }
                down.countDown();
            }
        };
        Thread t;
        for(int j=0; j<100; j++){
            t = new Thread(run, "t"+j);
            t.start();
            //t.join();//Ensure the running order of threads
        }
        down.await();
        System.out.println(i);
        System.out.println(iStr);
    }

    @lombok.SneakyThrows
    public void syncLock() {
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
                    iStr += " , " + Thread.currentThread().getName();
                }
                down.countDown();
            }
        };
        Thread t;
        for(int j=0; j<100; j++){
            t = new Thread(run, "t"+j);
            t.start();
            //t.join();//Ensure the running order of threads
        }
        down.await();
        System.out.println(i);
        System.out.println(iStr);
    }
}
