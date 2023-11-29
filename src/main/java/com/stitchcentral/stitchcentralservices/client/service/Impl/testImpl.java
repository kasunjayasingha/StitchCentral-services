package com.stitchcentral.stitchcentralservices.client.service.Impl;

import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;
import com.stitchcentral.stitchcentralservices.client.dto.ClientSampleDTO;
import com.stitchcentral.stitchcentralservices.client.service.AppointmentsService;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public String updateAppoinment(AppointmentsDTO appointmentsDTO) {
        return null;
    }

    @Override
    public String deleteAppoinment(String email) {
        return null;
    }

    @Override
    public List<AppointmentsDTO> getAllAppoinment(AppoinmentStatus status) {
        return null;
    }

    @Override
    public String cancelAppoinment(AppointmentsDTO appointmentsDTO) {
        return null;
    }

    @Override
    public String uploadFile(MultipartFile file, String appointmentId) {
        return null;
    }

    @Override
    public ClientSampleDTO downloadFile(Integer appointmentId) {
        return null;
    }


}
