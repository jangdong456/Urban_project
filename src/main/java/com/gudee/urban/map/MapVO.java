package com.gudee.urban.map;

import lombok.Data;

@Data
public class MapVO {
		private Long id;
		private String apt_name;
		private int transaction_price;
		private String transaction_yy;
		private String transaction_mm;
		private String transaction_dd;
		private String rode_name;
		private String street_number;
		private String build_yy;
		private int floor;
		private Double area;
		private Double x_coordinate;
		private Double y_coordinate;
}
