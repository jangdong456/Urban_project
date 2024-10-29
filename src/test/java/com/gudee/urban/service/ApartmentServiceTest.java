package com.gudee.urban.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApartmentServiceTest {
	
	@Autowired
	private ApartmentService apartmentService;

	@Test
	void fetchAndSaveRealEstateDataTest() {
		apartmentService.fetchAndSaveRealEstateData();
	}

}
