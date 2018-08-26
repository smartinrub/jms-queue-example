package org.smartinrub.jmsqueueexample;

import javax.jms.JMSException;

public class Main {
    public static void main(String[] args) throws JMSException, InterruptedException {
        MyProducer producer = new MyProducer();

        MyConsumer consumer = new MyConsumer();

        consumer.startListener();

        for (int i = 0; i < 5; i++) {
            String message = "Message " + i;
            producer.sendMessage(message);
            Thread.sleep(300);
        }

        producer.closeConnection();
        consumer.closeConnection();


    }
}
