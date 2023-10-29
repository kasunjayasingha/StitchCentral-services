package com.stitchcentral.stitchcentralservices.client.service;

import com.stitchcentral.stitchcentralservices.client.dto.CustomerDTO;
import com.stitchcentral.stitchcentralservices.util.CommonResponse;

import java.util.List;

public interface ClientService {

    String saveCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> getCustomer(String email);
}
