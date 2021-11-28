package com.fiap.grp2.integrationmq.consumidor;

import static com.fiap.grp2.integrationmq.util.Constants.RABBITMQ_QUEUE;
import static com.fiap.grp2.integrationmq.util.Constants.SLEEP_TIME_MILLIS;
import static com.fiap.grp2.integrationmq.util.Constants.TEMPERATURA_MAX;
import static com.fiap.grp2.integrationmq.util.Constants.TEMPERATURA_MIN;
import static com.fiap.grp2.integrationmq.util.Constants.UMIDADE_MIN;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.fiap.grp2.integrationmq.configuracao.RabbitMqConfig;
import com.fiap.grp2.integrationmq.model.SensorDto;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Consumidor {
	public static List<SensorDto> CONSOME_MENSAGENS() {
		RabbitTemplate template = new RabbitTemplate(RabbitMqConfig.getConnection());

		final String SKIP_LINE = System.getProperty("line.separator");
		// Email email = new Email();
		String message;
		List<SensorDto> listSensorDto = new ArrayList<SensorDto>();
		Gson gson = new Gson();

		while (true) {
			SensorDto sensorDto = new SensorDto();

			try {
				byte[] msg_byte = template.receive(RABBITMQ_QUEUE).getBody();
				message = new String(msg_byte);
				sensorDto = gson.fromJson(message, SensorDto.class);

				if (ativarAlarmTemp(sensorDto.getTemperatura())) {
					StringBuilder assunto = new StringBuilder().append("ALARME : TEMPERATURA : ")
							.append(sensorDto.getTemperatura());

					StringBuilder conteudo = new StringBuilder().append("DRONE ID: ").append(sensorDto.getId())
							.append(SKIP_LINE).append("TEMPERATURA: ").append(sensorDto.getTemperatura())
							.append(SKIP_LINE).append("Long.:").append(sensorDto.getLongitude()).append(SKIP_LINE)
							.append("Lat.: ").append(sensorDto.getLatitude());

					// email.sendGmailSimpleMail(EMAIL_DESTINO, assunto.toString(),
					// conteudo.toString());
				}

				if (ativarAlarmUmid(sensorDto.getUmidade())) {
					StringBuilder assunto = new StringBuilder().append("ALARME : UMIDADE : ")
							.append(sensorDto.getUmidade());

					StringBuilder conteudo = new StringBuilder().append("DRONE ID: ").append(sensorDto.getId())
							.append(SKIP_LINE).append("UMIDADE: ").append(sensorDto.getUmidade()).append(SKIP_LINE)
							.append("Long.:").append(sensorDto.getLongitude()).append(SKIP_LINE).append("Lat.: ")
							.append(sensorDto.getLatitude());

					// email.sendGmailSimpleMail(EMAIL_DESTINO, assunto.toString(),
					// conteudo.toString());
				}
								
				Thread.sleep(SLEEP_TIME_MILLIS);
			} catch (NullPointerException | JsonSyntaxException | InterruptedException e) {
				System.out.println("[Nenhuma mensagem para ser processada]");
				//continue;
				return listSensorDto;
			}
			listSensorDto.add(sensorDto);
		}
	}

	public static boolean ativarAlarmTemp(Float temp) {
		return temp <= TEMPERATURA_MIN || temp >= TEMPERATURA_MAX ? true : false;
	}

	public static boolean ativarAlarmUmid(Float umid) {
		return umid <= UMIDADE_MIN ? true : false;
	}

}
