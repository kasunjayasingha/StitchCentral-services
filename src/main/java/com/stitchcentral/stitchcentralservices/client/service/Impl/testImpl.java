package com.stitchcentral.stitchcentralservices.client.service.Impl;

import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;
import com.stitchcentral.stitchcentralservices.client.service.AppointmentsService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("appointmentsService1")
@Transactional
public class testImpl implements AppointmentsService {

    @Override
    public String saveAppointment(AppointmentsDTO appointmentsDTO) {
        return null;
    }

    @Override
    public List<AppointmentsDTO> getAppoinment(String email) {
        return null;
    }


}
