package com.stitchcentral.stitchcentralservices.client.service;

import com.stitchcentral.stitchcentralservices.client.dto.CustomerDTO;

import java.util.List;

public interface ClientService {

    String saveCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getCustomer(String email);

    List<CustomerDTO> getAllCustomer();

    String updateCustomer(CustomerDTO customerDTO);

    String saveContactUs(String name, String email, String message);
}
