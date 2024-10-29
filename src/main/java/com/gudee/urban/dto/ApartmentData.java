package com.gudee.urban.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ApartmentData {

    private Long id;
    private String year;
    private int dealAmount;
    private String legalDongName;
    private int floor;
    private String lotNumber;
    private String apartmentName;
    private String buildYear;
    private String referenceDay;
    private String referenceMonth;
    private double exclusiveArea;
    private double xCoordinate;
    private double yCoordinate;

}

