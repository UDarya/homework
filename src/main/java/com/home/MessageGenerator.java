package com.home;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MessageGenerator extends Thread {
    private QueueWriter queueWriter = QueueWriter.getInstance();
    private int maxPriority;
    private int delay;
    private int duration;
    private int msgLength;

    private static final String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Random random = new Random();


    public MessageGenerator(int msgLength, int maxPriority, int duration, int delay) {
        this.msgLength = msgLength;
        this.maxPriority = maxPriority;
        this.delay = delay;
        this.duration = duration;
    }

    @Override
    public void run() {
        long start;
        ArrayList<Message> batch;
        while (true) {
            start = System.currentTimeMillis();
            batch = new ArrayList<>();
            while (System.currentTimeMillis() - start <= TimeUnit.SECONDS.toMillis(duration)) {
                Message msg = generate();
                batch.add(msg);
            }

            queueWriter.write(batch);

            try {
                sleep(delay);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    Message generate() {
        int priority = random.nextInt(maxPriority) + 1;

        return new Message(generateRandomString(), priority);
    }

    private String generateRandomString() {
        StringBuilder builder = new StringBuilder();
        int length = msgLength;
        while (length-- != 0) {
            int character = (int) (Math.random() * ALPHA_STRING.length());
            builder.append(ALPHA_STRING.charAt(character));
        }
        return builder.toString();
    }
}
