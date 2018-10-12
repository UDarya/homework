package com.home;

import java.util.concurrent.PriorityBlockingQueue;

public class Worker extends Thread {
    private PriorityBlockingQueue<Message> queue;

    private String SEPARATOR = ":";

    public Worker(PriorityBlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message message = this.queue.take();
                System.out.print(Thread.currentThread().getId() + SEPARATOR + message);
            }
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

}
