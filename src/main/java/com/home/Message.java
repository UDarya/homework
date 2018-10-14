package com.home;

import java.util.Objects;

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
        if (this.priority < message.getPriority()) {
            return -1;
        } else if (this.priority > message.getPriority()) {
            return 1;
        } else {
            return this.message.compareTo(message.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(message, message1.message) &&
                Objects.equals(priority, message1.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, priority);
    }

    @Override
    public String toString() {
        return "message='" + message + " priority=" + priority + '\n';
    }
}
