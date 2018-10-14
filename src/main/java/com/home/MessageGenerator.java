package com.home;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MessageGenerator implements Runnable {
    private QueueWriter queueWriter = QueueWriter.getInstance();
    private int maxPriority;
    private int delay;
    private int duration;
    private int msgLength;

    private static final String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Random random = new Random();
    private StringBuilder builder = new StringBuilder();

    public MessageGenerator(int msgLength, int maxPriority, int duration, int delay) {
        if (msgLength <= 0 || maxPriority <= 0
                || duration <= 0 || delay <= 0) {
            throw new IllegalArgumentException("Input parameters are not valid! Need to be bigger zero");
        }
        this.msgLength = msgLength;
        this.maxPriority = maxPriority;
        this.delay = delay;
        this.duration = duration;
    }

    @Override
    public void run() {
        long start;
        long durationMillis = TimeUnit.SECONDS.toMillis(duration);
        ArrayList<Message> batch;
        while (true) {
            start = System.currentTimeMillis();
            batch = new ArrayList<>();
            while (System.currentTimeMillis() - start <= durationMillis) {
                Message msg = generateMessage();
                batch.add(msg);
            }

            queueWriter.write(batch);

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.print(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    Message generateMessage() {
        int priority = random.nextInt(maxPriority) + 1;

        return new Message(generateRandomString(), priority);
    }

    private String generateRandomString() {
        builder.setLength(0);
        int length = msgLength;
        while (length-- != 0) {
            int character = (int) (Math.random() * ALPHA_STRING.length());
            builder.append(ALPHA_STRING.charAt(character));
        }
        return builder.toString();
    }
}
