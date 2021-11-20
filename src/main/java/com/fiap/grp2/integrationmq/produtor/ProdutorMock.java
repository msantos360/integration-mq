package com.fiap.grp2.integrationmq.produtor;

import com.fiap.grp2.integrationmq.configuracao.RabbitMqConfig;
import com.fiap.grp2.integrationmq.model.SensorDto;
import com.google.gson.Gson;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.fiap.grp2.integrationmq.util.Constants.*;

public class ProdutorMock {
    public static void main(String[] args){
        RabbitAdmin admin = new RabbitAdmin(RabbitMqConfig.getConnection());

        Queue rabbitQueue = new Queue(RABBITMQ_QUEUE);
        admin.declareQueue(rabbitQueue);

        DirectExchange directExchange = new DirectExchange(RABBITMQ_EXCHANGE);
        admin.declareExchange(directExchange);

        admin.declareBinding(BindingBuilder.bind(rabbitQueue).to(directExchange).with(RABBITMQ_ROUTING_KEY));

        RabbitTemplate template = new RabbitTemplate(RabbitMqConfig.getConnection());

        Gson jsonGson = new Gson();

        SensorDto sensorDto = new SensorDto();
        for(int i =0; i < 100; i++){
            sensorDto.setId(Long.valueOf(i));
            sensorDto.setLatitude("0");
            sensorDto.setLongitude("0");
            sensorDto.setTemperatura("25");
            sensorDto.setUmidade("70");

            template.convertAndSend(RABBITMQ_EXCHANGE, RABBITMQ_ROUTING_KEY, jsonGson.toJson(sensorDto));
        }

    }

}
