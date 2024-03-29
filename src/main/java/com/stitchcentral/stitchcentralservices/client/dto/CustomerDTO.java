package com.stitchcentral.stitchcentralservices.client.dto;


import com.stitchcentral.stitchcentralservices.util.enums.CustomerTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private int id;
    private String first_name;
    private String last_name;
    private String address;
    private String city;
    private int postal_code;
    private String company;
    private String university;
    private String club;
    private String email;
    private String phone_no;
    private String password;
    private Date create_date;
    private Date update_date;
    private CustomerTypes customer_type;
}
