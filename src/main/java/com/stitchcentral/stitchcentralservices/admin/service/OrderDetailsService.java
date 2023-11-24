package com.stitchcentral.stitchcentralservices.admin.service;

import com.stitchcentral.stitchcentralservices.admin.dto.OrderDetailsDTO;
import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;

public interface OrderDetailsService {

    String saveOrderDetails(AppointmentsDTO appointmentsDTO);

}
