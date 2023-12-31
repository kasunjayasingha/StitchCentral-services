package com.stitchcentral.stitchcentralservices.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactUsDTO {
    private String name;
    private String email;
    private String message;
    private Date create_date;
}
