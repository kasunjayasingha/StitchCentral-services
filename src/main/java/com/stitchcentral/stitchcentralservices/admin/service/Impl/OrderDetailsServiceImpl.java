package com.stitchcentral.stitchcentralservices.admin.service.Impl;

import com.stitchcentral.stitchcentralservices.admin.dto.OrderDetailsDTO;
import com.stitchcentral.stitchcentralservices.admin.entity.OrderDetails;
import com.stitchcentral.stitchcentralservices.admin.repository.OrderDetailsRepo;
import com.stitchcentral.stitchcentralservices.admin.service.OrderDetailsService;
import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;
import com.stitchcentral.stitchcentralservices.client.entity.Appointments;
import com.stitchcentral.stitchcentralservices.client.repository.AppoinmentsRepo;
import com.stitchcentral.stitchcentralservices.controller.clientController;
import com.stitchcentral.stitchcentralservices.util.CommonResponse;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service("orderDetailsService")
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private static final Logger LOGGER = Logger.getLogger(clientController.class.getName());

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AppoinmentsRepo appoinmentsRepo;

    @Autowired
    OrderDetailsRepo orderDetailsRepo;

    @Override
    public String saveOrderDetails(AppointmentsDTO appointmentsDTO) {
        LOGGER.info("saveOrderDetails method is called");

        try{
            if(appointmentsDTO.getOrderDetails() == null || appointmentsDTO.getOrderDetails().isEmpty() ){
                return new CommonResponse(false, "Order Details is null").toString();
            }else {
                List<Appointments> appointments = appoinmentsRepo.findById(appointmentsDTO.getId());
                if(!appointments.isEmpty()) {
                    for(OrderDetailsDTO orderDetailsDTO : appointmentsDTO.getOrderDetails()) {
                        OrderDetails od = new OrderDetails();
                        od.setAppointments(appointments.get(0));
                        od.setOrderType(orderDetailsDTO.getOrderType());
                        od.setQuantity(orderDetailsDTO.getQuantity());
                        od.setMaterial(orderDetailsDTO.getMaterial());
                        od.setDescription(orderDetailsDTO.getDescription());
                        od.setDispatchDate(orderDetailsDTO.getDispatchDate());
                        od.setSwingPlace(orderDetailsDTO.getSwingPlace());
                        od.setAdvance(orderDetailsDTO.getAdvance());

                        orderDetailsRepo.save(od);
                    }
                    appointments.get(0).setStatus(appointmentsDTO.getStatus());
                    appoinmentsRepo.save(appointments.get(0));

                    return new CommonResponse(true, "Order Details saved successfully").toString();
                }else{
                    return new CommonResponse(false, "Appointment not found").toString();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            LOGGER.info("saveOrderDetails method is called");
            return new CommonResponse(false, e.getMessage()).toString();
        }

    }
}
