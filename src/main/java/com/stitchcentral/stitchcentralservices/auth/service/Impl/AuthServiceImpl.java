package com.stitchcentral.stitchcentralservices.auth.service.Impl;


import com.stitchcentral.stitchcentralservices.auth.service.AuthService;
import com.stitchcentral.stitchcentralservices.client.entity.Customer;
import com.stitchcentral.stitchcentralservices.client.repository.CustomerRepo;
import com.stitchcentral.stitchcentralservices.util.CommonResponse;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("authService")
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    CustomerRepo customerRepo;

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
}
