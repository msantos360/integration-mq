package com.fiap.grp2.integrationmq.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.grp2.integrationmq.consumidor.Consumidor;
import com.fiap.grp2.integrationmq.model.ConsumidorDto;
import com.fiap.grp2.integrationmq.model.GenericDto;
import com.fiap.grp2.integrationmq.model.SensorDto;

@RestController
@RequestMapping("/consumidor")
public class ConsumidorController {

	@GetMapping
	public ResponseEntity<GenericDto> startConsumidor() {
		
		List<String> errorList = new ArrayList<String>();
		
		try {
			List<SensorDto> mensagens = Consumidor.CONSOME_MENSAGENS();
			
			ConsumidorDto consumidorDto = new ConsumidorDto(mensagens);
			return ResponseEntity.ok(new GenericDto("Mensagens consumidas com sucesso.", errorList, consumidorDto));
		
		} catch (Exception ex) {			
			errorList.add("Por favor, tente novamente.");			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new GenericDto("Falha ao tentar executar esta solicitação", errorList, ex));
		}
	}
}
