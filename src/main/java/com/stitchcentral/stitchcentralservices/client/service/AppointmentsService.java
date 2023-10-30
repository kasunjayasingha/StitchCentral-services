package com.stitchcentral.stitchcentralservices.client.service;

import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;
import com.stitchcentral.stitchcentralservices.client.entity.Appointments;

import java.util.List;

public interface AppointmentsService {

    String saveAppointment(AppointmentsDTO appointmentsDTO);
    List<AppointmentsDTO> getAppoinment(String email);
    String updateAppoinment(AppointmentsDTO appointmentsDTO);

}
