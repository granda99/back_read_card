package com.tecno.service.ligrapro.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecno.service.ligrapro.model.Cards;
import com.tecno.service.ligrapro.restModel.ApiResponse;
import com.tecno.service.ligrapro.service.CardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cardService/v1")
@RequiredArgsConstructor
public class ApiController {

	private final CardService cardService;

	@GetMapping("/getAll")
	public List<Cards> getAllCards() {
		return cardService.findAll();
	}

	@GetMapping("/findByCode/{code}")
	public Object findByCode(@PathVariable String code) {
		ApiResponse response = new ApiResponse();
		response.setCode(401);
		response.setMessage("Ingreso negado, verificar tarjeta.");
		List<Cards> lista = cardService.findByCode(code);
		if (!lista.isEmpty()) {
			lista.sort((c1, c2) -> c2.getFecha_hora().compareTo(c1.getFecha_hora()));
			if (lista.get(0).getAccion().equals("D")) {
				lista.get(0).setEstado("S");
				lista.get(0).setFecha_uso(this.getFormatDateShort(new Date()));
				this.updateState(lista.get(0));
				response.setCode(403);
				response.setMessage("Ingreso negado, verificar deuda.");
				return response;
			}
			if (lista.get(0).getEstado().equals("S")) {
				response.setCode(403);
				response.setMessage("AntiPassBack: " + lista.get(0).getFecha_uso());
				return response;
			} else
				return lista;
		} else
			return response;
	}

	private String getFormatDateShort(Date date) {
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(date);
	}

	@PutMapping("/updateState")
	public ApiResponse updateState(@RequestBody Cards card) {
		ApiResponse response = new ApiResponse();
		try {
			if (card.getId() == null)
				throw new RuntimeException("El id no puede estar vacío.");
			if (card.getFecha_uso() == null || card.getFecha_uso().equals(""))
				throw new RuntimeException("La fecha no puede estar vacía.");
			cardService.save(card);
		} catch (Exception ex) {
			response.setCode(500);
			response.setMessage("Eror en la petición: " + ex.getMessage());
		}
		return response;
	}
}