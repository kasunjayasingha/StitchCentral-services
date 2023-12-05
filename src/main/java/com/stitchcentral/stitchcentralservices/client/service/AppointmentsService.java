package com.stitchcentral.stitchcentralservices.client.service;

import com.stitchcentral.stitchcentralservices.admin.dto.DashBoardDTO;
import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;
import com.stitchcentral.stitchcentralservices.client.dto.ClientSampleDTO;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

public interface AppointmentsService {

    String saveAppointment(AppointmentsDTO appointmentsDTO);

    List<AppointmentsDTO> getAppoinment(String email);

    String updateAppoinment(AppointmentsDTO appointmentsDTO);

    String deleteAppoinment(String email);

    List<AppointmentsDTO> getAllAppoinment(AppoinmentStatus status) throws DataFormatException, IOException;

    String cancelAppoinment(AppointmentsDTO appointmentsDTO);

    String uploadFile(MultipartFile file, String appointmentId);

    ClientSampleDTO downloadFile(Integer appointmentId);

    List<DashBoardDTO> getDashboardDetails(Integer year);


}
