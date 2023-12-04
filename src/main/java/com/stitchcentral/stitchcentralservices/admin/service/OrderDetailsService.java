package com.stitchcentral.stitchcentralservices.admin.service;

import com.stitchcentral.stitchcentralservices.admin.dto.OrderDetailsDTO;
import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;

import java.util.List;

public interface OrderDetailsService {

    String saveOrderDetails(AppointmentsDTO appointmentsDTO);

    String updateOrderDetails(OrderDetailsDTO orderDetailsDTO);

    List<OrderDetailsDTO> getOrderDetails();

}
