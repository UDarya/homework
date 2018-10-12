package com.home;

public class Message implements Comparable<Message> {
    private String message;
    private Integer priority;

    public Message(String message, Integer pririty) {
        this.message = message;
        this.priority = pririty;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public int compareTo(Message message) {
        return this.priority.compareTo(message.getPriority());
    }

    @Override
    public String toString() {
        return "message='" + message + " priority=" + priority + '\n';
    }
}
