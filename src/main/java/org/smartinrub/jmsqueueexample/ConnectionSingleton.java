package org.smartinrub.jmsqueueexample;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.ConnectionFactory;

public class ConnectionSingleton {
    private static ConnectionSingleton instance = new ConnectionSingleton();

    private ConnectionFactory connectionFactory;

    public static ConnectionSingleton getInstance() {
        if (instance == null) {
            synchronized (ConnectionSingleton.class) {
                if (instance == null) {
                    return new ConnectionSingleton();
                }
            }
        }
        return instance;
    }

    private ConnectionSingleton() {
        this.connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
    }

    public ActiveMQConnectionFactory getConnectionFactory() {
        return (ActiveMQConnectionFactory) connectionFactory;
    }
}
