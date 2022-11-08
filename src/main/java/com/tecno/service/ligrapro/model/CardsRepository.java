package com.tecno.service.ligrapro.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends MongoRepository<Cards, String> {

	@Query("{ 'codigo' : ?0 }")
	List<Cards> findByCode(String code);
}
