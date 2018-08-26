package org.smartinrub.jmsqueueexample;

import javax.jms.*;

public class MyConsumer implements MessageListener {

    private Connection connection;

    public void startListener() throws JMSException {
        ConnectionFactory factory = ConnectionSingleton.getInstance().getConnectionFactory();
        connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("example.queue");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(this);
    }

    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("Message received: " + textMessage.getText());
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void closeConnection() throws JMSException {
        connection.close();
    }
}
