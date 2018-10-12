package com.home;

import java.util.concurrent.PriorityBlockingQueue;

public class QueueRegistrator {
    public PriorityBlockingQueue registrate(QueueWriter writer) {
        PriorityBlockingQueue queue = new PriorityBlockingQueue();
        writer.registrateQueue(queue);
        return queue;
    }
}

