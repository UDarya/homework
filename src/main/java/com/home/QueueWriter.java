package com.home;

import java.util.ArrayList;
import java.util.Queue;

public class QueueWriter {
    private ArrayList<Queue<Message>> queueList = new ArrayList<>();

    public void registrateQueue(Queue queue) {
        queueList.add(queue);
    }

    public void write(Message message) {
        queueList.forEach(queue -> queue.add(message));
    }

    public static QueueWriter getInstance() {
        return QueueWriterHolder.instance;
    }

    private static class QueueWriterHolder {
        private static final QueueWriter instance = new QueueWriter();
    }
}
