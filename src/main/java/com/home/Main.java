package com.home;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // TODO: 10/12/18 hardcode
        int workerNum = 3;
        int msgLength = 10;
        int maxPriority = 10;
        int delay = 100000;
        int duration_sec = 1;
        int processDuration = 1;

        MessageGenerator generator = new MessageGenerator(msgLength, maxPriority, duration_sec, delay);
        QueueWriter writer = QueueWriter.getInstance();
        QueueRegistrator registrator = new QueueRegistrator();

        ArrayList<Thread> workers = new ArrayList<>();
        for (int i = 0; i < workerNum; i++) {
            Worker worker = new Worker(registrator.registrate(writer), processDuration);
            workers.add(new Thread(worker));
        }

        Thread generatoThread = new Thread(generator);
        generatoThread.start();

        workers.forEach(Thread::start);
    }
}
