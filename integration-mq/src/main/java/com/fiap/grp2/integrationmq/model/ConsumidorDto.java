package com.fiap.grp2.integrationmq.model;

import java.util.ArrayList;
import java.util.List;

public class ConsumidorDto {

	private List<SensorDto> mensagens = new ArrayList<>();

	public ConsumidorDto(List<SensorDto> mensagens) {
		this.mensagens = mensagens;
	}

	public List<SensorDto> getMensagens() {
		return mensagens;
	}
}
