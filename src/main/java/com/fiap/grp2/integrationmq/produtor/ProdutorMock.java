package com.fiap.grp2.integrationmq.produtor;

import com.fiap.grp2.integrationmq.configuracao.RabbitMqConfig;
import com.fiap.grp2.integrationmq.model.SensorDto;
import com.google.gson.Gson;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Random;

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
        Random random = new Random();
        SensorDto sensorDto = new SensorDto();

        float temp_min = -35f;
        float temp_max = 40f;
        float umid_min = 0f;
        float umid_max = 100f;

        for(int i =0; i < 350; i++){
            sensorDto.setId(Long.valueOf(i));
            sensorDto.setLatitude(random.nextFloat());
            sensorDto.setLongitude(random.nextFloat());
            sensorDto.setTemperatura(random.nextFloat() * (temp_max - temp_min) + temp_min);
            sensorDto.setUmidade(random.nextFloat() * (umid_max - umid_min) + umid_min);

            template.convertAndSend(RABBITMQ_EXCHANGE, RABBITMQ_ROUTING_KEY, jsonGson.toJson(sensorDto));
        }

    }

}
