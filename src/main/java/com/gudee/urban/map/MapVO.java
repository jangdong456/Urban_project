package com.gudee.urban.map;

import lombok.Data;

@Data
public class MapVO {
		private Long id;
		private String building_name;
		private Double area;
		private String lot_number;
		private Double transaction_price;
		private String district;
		private Long floor;
}
