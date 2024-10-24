package com.gudee.urban.controller;

import lombok.Data;

@Data
public class UrbanVO {
	private Long id;
	private String building_name;
	private Double area;
	private String lot_number;
	private Double transaction_price;
	private String district;
	private Long floor;
}
