package com.stitchcentral.stitchcentralservices.client.service;

import com.stitchcentral.stitchcentralservices.client.dto.CustomerDTO;
import com.stitchcentral.stitchcentralservices.util.CommonResponse;

public interface ClientService {

    String saveCustomer(CustomerDTO customerDTO);
}
