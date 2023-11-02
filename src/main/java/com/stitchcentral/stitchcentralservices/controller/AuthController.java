package com.stitchcentral.stitchcentralservices.controller;

import com.stitchcentral.stitchcentralservices.auth.service.AuthService;
import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;
import com.stitchcentral.stitchcentralservices.client.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    private static final Logger LOGGER = Logger.getLogger(clientController.class.getName());

    @RequestMapping(value = "/checkEmailIsPresent/{email}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> checkEmailIsPresent(@PathVariable String email) {
        LOGGER.info("checkEmailIsPresent method is called");
        return new ResponseEntity<String>(authService.checkEmailIsPresent(email), HttpStatus.OK);
    }
}
