package com.stitchcentral.stitchcentralservices.auth.service;

import com.stitchcentral.stitchcentralservices.auth.dto.LoginReqestDTO;

public interface AuthService {

    String checkEmailIsPresent(String email);
    String Login(LoginReqestDTO loginReqestDTO);
}
