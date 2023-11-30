package com.stitchcentral.stitchcentralservices.auth.service.Impl;


import com.stitchcentral.stitchcentralservices.auth.dto.LoginReqestDTO;
import com.stitchcentral.stitchcentralservices.auth.service.AuthService;
import com.stitchcentral.stitchcentralservices.client.dto.ClientSampleDTO;
import com.stitchcentral.stitchcentralservices.client.dto.CustomerDTO;
import com.stitchcentral.stitchcentralservices.client.entity.Appointments;
import com.stitchcentral.stitchcentralservices.client.entity.Customer;
import com.stitchcentral.stitchcentralservices.client.repository.AppoinmentsRepo;
import com.stitchcentral.stitchcentralservices.client.repository.CustomerRepo;
import com.stitchcentral.stitchcentralservices.controller.clientController;
import com.stitchcentral.stitchcentralservices.util.CommonResponse;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import com.stitchcentral.stitchcentralservices.util.enums.CustomerTypes;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service("authService")
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    CustomerRepo customerRepo;
    AppoinmentsRepo appoinmentsRepo;
    private  final static Logger LOGGER = Logger.getLogger(clientController.class.getName());

    @Override
    public String checkEmailIsPresent(String email) {
        try{
            if(!email.isEmpty()){
                Optional<Customer> checkEmail = customerRepo.findByEmailAndAppointments_Status(email, AppoinmentStatus.PENDING);
                if(checkEmail.isPresent()) {
                    return new CommonResponse(true, "Email is present").toString();
                }else{
                    return new CommonResponse(false, "Email is not present").toString();
                }
            }else {
                return new CommonResponse(false, "Email is empty").toString();
            }
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResponse(false, e.getMessage()).toString();
        }

    }

    @Override
    public String checkOnlyEmailIPresent(String email) {
        try{
            if(!email.isEmpty()){
                Optional<Customer> checkEmail = customerRepo.findByEmail(email);
                if(checkEmail.isPresent()) {
                    return new CommonResponse(true, "Email is present").toString();
                }else{
                    return new CommonResponse(false, "Email is not present").toString();
                }
            }else {
                return new CommonResponse(false, "Email is empty").toString();
            }
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResponse(false, e.getMessage()).toString();
        }
    }

    @Override
    public CustomerDTO Login(LoginReqestDTO loginReqestDTO) {
        LOGGER.info("Login IMPL method is called");
        try{
            if (!loginReqestDTO.getEmail().isEmpty() && !loginReqestDTO.getPassword().isEmpty()){
                Optional<Customer> checkAuth = customerRepo.findByEmailAndPassword(loginReqestDTO.getEmail(), loginReqestDTO.getPassword());

                if (checkAuth.isPresent() && checkAuth.get().getCustomer_type().equals(CustomerTypes.REGULAR)) {
                    CustomerDTO customerDTO = new CustomerDTO();
                    customerDTO.setId(checkAuth.get().getId());
                    customerDTO.setFirst_name(checkAuth.get().getFirst_name());
                    customerDTO.setLast_name(checkAuth.get().getLast_name());
                    customerDTO.setAddress(checkAuth.get().getAddress());
                    customerDTO.setCity(checkAuth.get().getCity());
                    customerDTO.setCompany(checkAuth.get().getCompany());
                    customerDTO.setEmail(checkAuth.get().getEmail());
                    customerDTO.setPhone_no(checkAuth.get().getPhone_no());
                    customerDTO.setCustomer_type(checkAuth.get().getCustomer_type());

                    return customerDTO;
                }else{
                    return null;
                }

            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
