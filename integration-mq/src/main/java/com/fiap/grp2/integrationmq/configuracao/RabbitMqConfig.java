package com.fiap.grp2.integrationmq.configuracao;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

import static com.fiap.grp2.integrationmq.util.Constants.*;

public class RabbitMqConfig {
    private static CachingConnectionFactory connectionFactory;

    public static CachingConnectionFactory getConnection(){
        if(connectionFactory == null){
            connectionFactory = new CachingConnectionFactory("jackal.rmq.cloudamqp.com");

            connectionFactory.setUsername(RABBITMQ_USERNAME);
            connectionFactory.setPassword(RABBITMQ_PASSWD);
            connectionFactory.setVirtualHost(RABBITMQ_VIRTUAL_HOST);

            connectionFactory.setRequestedHeartBeat(30);
            connectionFactory.setConnectionTimeout(30000);
        }

        return connectionFactory;
    }
}
