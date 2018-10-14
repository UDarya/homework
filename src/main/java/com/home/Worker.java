package com.home;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {
    private PriorityBlockingQueue<Message> queue;
    private int duration;

    private String SEPARATOR = ":";

    public Worker(PriorityBlockingQueue<Message> queue, int duration) {
        this.queue = queue;
        this.duration = duration;
    }

    @Override
    public void run() {
        long durationMillis = TimeUnit.SECONDS.toMillis(duration);
        try {
            while (true) {
                Message message = this.queue.take();
                System.out.print(Thread.currentThread().getId() + SEPARATOR + message);
                Thread.sleep(durationMillis);
            }
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

}
