package com.stitchcentral.stitchcentralservices.client.service;

import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;
import com.stitchcentral.stitchcentralservices.client.entity.Appointments;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;

import java.util.List;

public interface AppointmentsService {

    String saveAppointment(AppointmentsDTO appointmentsDTO);
    List<AppointmentsDTO> getAppoinment(String email);
    String updateAppoinment(AppointmentsDTO appointmentsDTO);
    String deleteAppoinment(String email);
    List<AppointmentsDTO> getAllAppoinment(AppoinmentStatus status);
    String cancelAppoinment(AppointmentsDTO appointmentsDTO);

}
