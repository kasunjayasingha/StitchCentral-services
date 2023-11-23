package com.stitchcentral.stitchcentralservices.client.dto;

import com.stitchcentral.stitchcentralservices.admin.dto.OrderDetailsDTO;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentsDTO {
    private int id;
    private int customer_Id;
    private Date appointment_date;
    private AppoinmentType type;
    private AppoinmentStatus status;
    private String description;
    private ClientSampleDTO client_sample;
    private CustomerDTO customer;
    private List<OrderDetailsDTO> orderDetails;

}
