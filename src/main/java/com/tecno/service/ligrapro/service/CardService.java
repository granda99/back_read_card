package com.tecno.service.ligrapro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tecno.service.ligrapro.model.Cards;
import com.tecno.service.ligrapro.model.CardsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {
	private final CardsRepository cardsRepository;

	public void save(Cards t) {
		cardsRepository.save(t);
	}

	public List<Cards> findAll() {
		return cardsRepository.findAll();
	}

	public List<Cards> findByCode(String code) {
		return cardsRepository.findByCode(code);
	}

	public void deleteById(String id) {
		cardsRepository.deleteById(id);
	}
}
