package com.fiap.grp2.integrationmq.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.AmqpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.grp2.integrationmq.model.GenericDto;
import com.fiap.grp2.integrationmq.produtor.ProdutorMock;

@RestController
@RequestMapping("/produtor")
public class ProdutorController {

	@PostMapping
	public ResponseEntity<GenericDto> startProdutor() {

		List<String> errorList = new ArrayList<String>();

		try {
			ProdutorMock.START_PRODUTOR();
			
			return ResponseEntity.ok(new GenericDto("Mensagens enviadas com sucesso", errorList, null));

		} catch (AmqpException ex) {
			errorList.add("Hmm, parece que houve uma falha ao tentar se comunicar com o RabbitMq.");
			return ResponseEntity.ok(new GenericDto("Ocorreu uma falha.", errorList, ex));

		} catch (Exception ex) {
			errorList.add("Hmm, parece que houve uma falha ao tentar executar esta solicitação.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new GenericDto("Ocorreu uma falha.", errorList, ex));
		}

	}
}
