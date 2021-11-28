package com.fiap.grp2.integrationmq.model;

public class SensorDto {
	private Long id;
	private Float latitude;
	private Float longitude;
	private Float temperatura;
	private Float umidade;
	private String alarmeTemperatura;
	private String alarmeUmidade;

	public String getAlarmeTemperatura() {
		return alarmeTemperatura;
	}

	public void setAlarmeTemperatura(String alarmeTemperatura) {
		this.alarmeTemperatura = alarmeTemperatura;
	}

	public String getAlarmeUmidade() {
		return alarmeUmidade;
	}

	public void setAlarmeUmidade(String alarmeUmidade) {
		this.alarmeUmidade = alarmeUmidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Float temperatura) {
		this.temperatura = temperatura;
	}

	public Float getUmidade() {
		return umidade;
	}

	public void setUmidade(Float umidade) {
		this.umidade = umidade;
	}

}
