package com.stitchcentral.stitchcentralservices.client.dto;

import com.stitchcentral.stitchcentralservices.admin.dto.OrderDetailsDTO;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
    private String cancellationReason;
    private ClientSampleDTO client_sample;
    private CustomerDTO customer;
//    private MultipartFile file;
    private List<OrderDetailsDTO> orderDetails;

}
