package com.stitchcentral.stitchcentralservices.client.service.Impl;

import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;
import com.stitchcentral.stitchcentralservices.client.service.AppointmentsService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service("appointmentsService1")
@Transactional
public class testImpl implements AppointmentsService {

    @Override
    public String saveAppointment(AppointmentsDTO appointmentsDTO) {
        return null;
    }
}
