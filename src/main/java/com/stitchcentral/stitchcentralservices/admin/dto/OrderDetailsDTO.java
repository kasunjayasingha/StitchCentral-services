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
    private String orderType;
    private String material;
    private int quantity;
    private String swingPlace;
    private String payment;
    private Integer advance;
    private String description;
    private Date dispatchDate;
    private int appointment_id;
    private Date createDate;
    private Date updateDate;
    private String invoiceNo;
    private String orderStatus;
}
