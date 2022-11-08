package com.tecno.service.ligrapro.restModel;

import lombok.Data;

@Data
public class ApiResponse {

	private int code = 200;
	private String message = "Petición procesada exitosamente.";
}
