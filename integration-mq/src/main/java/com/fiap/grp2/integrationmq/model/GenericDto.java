package com.fiap.grp2.integrationmq.model;

import java.util.ArrayList;
import java.util.List;

public class GenericDto {

	private String responseMessage;
	private List<String> errorList = new ArrayList<>();
	private Object objeto;

	public GenericDto(String responseMessage, List<String> errorList, Object objeto) {
		this.responseMessage = responseMessage;
		this.errorList = errorList;
		this.objeto = objeto;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public List<String> getErrorList() {
		return errorList;
	}

	public Object getObjeto() {
		return objeto;
	}

}
