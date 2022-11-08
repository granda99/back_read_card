package com.tecno.service.ligrapro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(value = "cards_ligapro")
@Data
public class Cards {

	@Id
	private String id;
	private String codigo;
	private String puerta;
	private String nombre_propietario;
	private String localidad;
	private String accion;
	private String fecha_hora;
	private String estado;
	private String fecha_uso;

}