package org.smartinrub.jmsqueueexample;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MyProducer {

    private final MessageProducer producer;
    private final Session session;
    private final Connection connection;

    public MyProducer() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");
        connection = factory.createConnection();
        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("example.queue");
        producer = session.createProducer(queue);
    }

    public void sendMessage(String message) throws JMSException {
        System.out.println("Sending message " + message);

        TextMessage textMessage = session.createTextMessage(message);
        producer.send(textMessage);
    }

    public void closeConnection() throws JMSException {
        connection.close();
    }

}
