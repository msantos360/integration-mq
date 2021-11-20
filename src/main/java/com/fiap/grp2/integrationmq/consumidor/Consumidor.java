package com.fiap.grp2.integrationmq.consumidor;

import com.fiap.grp2.integrationmq.configuracao.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.fiap.grp2.integrationmq.util.Constants.RABBITMQ_QUEUE;

public class Consumidor {
    public static void main(String[] args){
        RabbitTemplate template = new RabbitTemplate(RabbitMqConfig.getConnection());

        while(true){
            try{

                // TODO a cada 10seg -> multithread com sleep? batch?
                // TODO Mock -> Implementar

                byte[] body = template.receive(RABBITMQ_QUEUE).getBody();
                System.out.println(new String(body));
            } catch (NullPointerException e){
                System.out.println("Nada a processar");
                continue;
            }
        }
    }
}
