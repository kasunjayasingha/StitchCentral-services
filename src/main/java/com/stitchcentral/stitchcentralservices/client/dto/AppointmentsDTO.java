package com.stitchcentral.stitchcentralservices.client.dto;

import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentsDTO {
    private int id;
    private int customer_Id;
    private Date appointment_date;
    private String type;
    private AppoinmentStatus status;
    private String description;
    private ClientSampleDTO sample;
    private CustomerDTO customer;

}
