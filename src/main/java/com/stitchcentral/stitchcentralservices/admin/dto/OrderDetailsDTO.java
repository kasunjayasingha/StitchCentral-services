package com.stitchcentral.stitchcentralservices.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailsDTO {
    private int id;
    private String material;
    private int quantity;
    private String swingPlace;
    private double advance;
    private String description;
    private Date dispatchDate;
    private int appointment_id;
}
