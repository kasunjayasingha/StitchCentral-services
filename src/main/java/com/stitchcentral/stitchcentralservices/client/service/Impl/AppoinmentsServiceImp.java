package com.stitchcentral.stitchcentralservices.client.service.Impl;

import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;
import com.stitchcentral.stitchcentralservices.client.entity.Appointments;
import com.stitchcentral.stitchcentralservices.client.entity.Client_Sample;
import com.stitchcentral.stitchcentralservices.client.entity.Customer;
import com.stitchcentral.stitchcentralservices.client.repository.AppoinmentsRepo;
import com.stitchcentral.stitchcentralservices.client.repository.CustomerRepo;
import com.stitchcentral.stitchcentralservices.client.service.AppointmentsService;
import com.stitchcentral.stitchcentralservices.util.CommonResponse;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("appointmentsService")
@Transactional
public class AppoinmentsServiceImp implements AppointmentsService {

    @Autowired
    AppoinmentsRepo appoinmentsRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public String saveAppointment(AppointmentsDTO appointmentsDTO) {
        System.out.println("saveAppointment method is called-- "+appointmentsDTO.toString());
        try{
            Optional<Customer> customerFind = customerRepo.findByEmail(appointmentsDTO.getCustomer().getEmail());
//            System.out.println("customerFind "+customerFind);
            if(customerFind.isPresent()) {

                Optional<Appointments> appointmentsFind = appoinmentsRepo.findByCustomer_Id(customerFind.get().getId());

                if(appointmentsFind.isPresent() && (appointmentsFind.get().getStatus().toString().equals("PENDING"))){
                    return new CommonResponse(false, "Appointment already exists").toString();
                }else{
                    Appointments appointments = new Appointments();
                    appointments.setCustomer(customerFind.get());
                    appointments.setStatus(AppoinmentStatus.PENDING);
                    appointments.setAppointment_date(appointmentsDTO.getAppointment_date());
                    appointments.setDescription(appointmentsDTO.getDescription());
                    appointments.setType(appointmentsDTO.getType());
//                    appointments.setClient_sample(appointmentsDTO.getSample());
                    Client_Sample clientSample = new Client_Sample();
                    System.out.println("appointmentsDTO.getSample() "+appointmentsDTO.getSample());
                    clientSample.setId(appointmentsDTO.getSample().getId());
                    clientSample.setFile_name(appointmentsDTO.getSample().getFile_name());
                    clientSample.setFile_type(appointmentsDTO.getSample().getFile_type());
                    clientSample.setPath(appointmentsDTO.getSample().getPath());
                    clientSample.setRelative_path(appointmentsDTO.getSample().getRelative_path());
//                    clientSample.setCreate_date(appointmentsDTO.getSample().getCreate_date());
//                    clientSample.setUpdate_date(appointmentsDTO.getSample().getUpdate_date());

                    appointments.setClient_sample(clientSample);

                    appoinmentsRepo.save(appointments);

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
}
