package com.home;

import org.junit.Assert;
import org.junit.Test;

public class MessageGeneratorTest {
    @Test
    public void generateTest() {
        int msgLength = 20;
        int maxPriority = 10;
        int duration = 1;
        int delay = 10;

        MessageGenerator generator = new MessageGenerator(msgLength, maxPriority, duration, delay);
        Message message = generator.generateMessage();

        Assert.assertNotNull(message);
        Assert.assertNotNull(message.getMessage());
        Assert.assertEquals(msgLength, message.getMessage().length());
    }
}
