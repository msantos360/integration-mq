package com.fiap.grp2.integrationmq.configuracao;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

public class RabbitMqConfig {
    private static CachingConnectionFactory connectionFactory;

    public static CachingConnectionFactory getConnection(){
        if(connectionFactory == null){
            connectionFactory = new CachingConnectionFactory("jackal.rmq.cloudamqp.com");

            connectionFactory.setUsername("fouqwuxa");
            connectionFactory.setPassword("cS84RNzvH92ilOQwdFfhrTcB7ANvsEgp");
            connectionFactory.setVirtualHost("fouqwuxa");

            connectionFactory.setRequestedHeartBeat(30);
            connectionFactory.setConnectionTimeout(30000);
        }

        return connectionFactory;
    }
}
