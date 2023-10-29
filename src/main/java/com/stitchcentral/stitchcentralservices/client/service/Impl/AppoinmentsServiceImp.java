package com.stitchcentral.stitchcentralservices.client.service.Impl;

import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;
import com.stitchcentral.stitchcentralservices.client.entity.Appointments;
import com.stitchcentral.stitchcentralservices.client.entity.Client_Sample;
import com.stitchcentral.stitchcentralservices.client.entity.Customer;
import com.stitchcentral.stitchcentralservices.client.repository.AppoinmentsRepo;
import com.stitchcentral.stitchcentralservices.client.repository.Client_SampleRepo;
import com.stitchcentral.stitchcentralservices.client.repository.CustomerRepo;
import com.stitchcentral.stitchcentralservices.client.service.AppointmentsService;
import com.stitchcentral.stitchcentralservices.util.CommonResponse;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("appointmentsService")
@Transactional
public class AppoinmentsServiceImp implements AppointmentsService {

    @Autowired
    AppoinmentsRepo appoinmentsRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    Client_SampleRepo clientSampleRepo;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public String saveAppointment(AppointmentsDTO appointmentsDTO) {
        System.out.println("saveAppointment method is called-- "+appointmentsDTO.toString());
        try{
//            Get existing customer
            Optional<Customer> customerFind = customerRepo.findByEmail(appointmentsDTO.getCustomer().getEmail());

//            System.out.println("customerFind "+customerFind);
            if(customerFind.isPresent()) {

//                Check if appointment already exists
                Optional<Appointments> appointmentsFind = appoinmentsRepo.findByCustomer_Id(customerFind.get().getId());

//              Check if appointment already exists with status PENDING
                if(appointmentsFind.isPresent() && (appointmentsFind.get().getStatus().toString().equals("PENDING"))){
                    return new CommonResponse(false, "Appointment already exists").toString();
                }else{
//                    Save appointment
                    Appointments appointments = new Appointments();
                    appointments.setCustomer(customerFind.get());
                    appointments.setStatus(AppoinmentStatus.PENDING);
                    appointments.setAppointment_date(appointmentsDTO.getAppointment_date());
                    appointments.setDescription(appointmentsDTO.getDescription());
                    appointments.setType(appointmentsDTO.getType());

//                  appoinment save and assign again save appointment to appointments object
                    appointments = appoinmentsRepo.save(appointments);
                    System.out.println("appointments----- "+appointments);

                    Client_Sample clientSample = new Client_Sample();
                    System.out.println("appointmentsDTO.getSample() "+appointmentsDTO.getSample());

                    clientSample.setFile_name(appointmentsDTO.getSample().getFile_name());
                    clientSample.setFile_type(appointmentsDTO.getSample().getFile_type());
                    clientSample.setPath(appointmentsDTO.getSample().getPath());
                    clientSample.setRelative_path(appointmentsDTO.getSample().getRelative_path());
                    clientSample.setCreate_date(appointmentsDTO.getSample().getCreate_date());
                    clientSample.setUpdate_date(appointmentsDTO.getSample().getUpdate_date());
                    clientSample.setAppointments(appointments);
//                    clientSampleRepo.save(clientSample);
                    clientSampleRepo.save(modelMapper.map(clientSample, Client_Sample.class));

//                    appointments.setClient_sample(appointmentsDTO.getSample());
//                    appoinmentsRepo.save(modelMapper.map(appointmentsDTO, Appointments.class));

                    return new CommonResponse(true, "Appointment saved successfully").toString();
                }

//                appoinmentsRepo.save(modelMapper.map(appointmentsDTO, Appointments.class));
//                return "Appointment saved successfully";
            }else{
                return new CommonResponse(false, "Customer not found").toString();
            }
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResponse(false, e.getMessage()).toString();
        }
    }

    @Override
    public List<AppointmentsDTO> getAppoinment(String email) {
        System.out.println("getAppoinment method is called-- "+email);
        try{
            Optional<Customer> customerFind = customerRepo.findByEmail(email);
            if(customerFind.isPresent()) {
                Optional<Appointments> appointmentsList = appoinmentsRepo.findByCustomer_Id(customerFind.get().getId());
                if(appointmentsList.isPresent()){
                    List<AppointmentsDTO> appointmentsDTOList = new ArrayList<>();
                    List<Appointments> appointmentsList1 = appoinmentsRepo.findById(appointmentsList.get().getId());
//                    appointmentsList1.forEach(appointments -> {
//                        AppointmentsDTO appointmentsDTO = new AppointmentsDTO();
//                        appointmentsDTO.setAppointment_date(appointments.getAppointment_date());
//                        appointmentsDTO.setDescription(appointments.getDescription());
//                        appointmentsDTO.setStatus(appointments.getStatus());
//                        appointmentsDTO.setType(appointments.getType());
//                        appointmentsDTO.setCustomer(appointments.getCustomer());
//                        appointmentsDTO.setSample(appointments.getClient_sample());
//                        appointmentsDTOList.add(appointmentsDTO);
//                    });
                    appointmentsList1.forEach(appointments -> {
                        AppointmentsDTO appointmentsDTO = modelMapper.map(appointments, AppointmentsDTO.class);
                        appointmentsDTOList.add(appointmentsDTO);
                    });
                    return appointmentsDTOList;





                }else {
                    return null;
                }

            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
