package com.stitchcentral.stitchcentralservices.auth.service;

import com.stitchcentral.stitchcentralservices.auth.dto.LoginReqestDTO;
import com.stitchcentral.stitchcentralservices.client.dto.CustomerDTO;

public interface AuthService {

    String checkEmailIsPresent(String email);

    String checkOnlyEmailIPresent(String email);

    CustomerDTO Login(LoginReqestDTO loginReqestDTO);

    String checkUsernameIsPresent(String username);

    String adminLogin(String username, String password);
}
