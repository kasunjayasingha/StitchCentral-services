package com.stitchcentral.stitchcentralservices.controller;

import com.stitchcentral.stitchcentralservices.auth.dto.LoginReqestDTO;
import com.stitchcentral.stitchcentralservices.auth.service.AuthService;
import com.stitchcentral.stitchcentralservices.client.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    private final static Logger LOGGER = Logger.getLogger(clientController.class.getName());
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/checkEmailIsPresent/{email}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> checkEmailIsPresent(@PathVariable String email) {
        LOGGER.info("checkEmailIsPresent method is called");
        return new ResponseEntity<String>(authService.checkEmailIsPresent(email), HttpStatus.OK);
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<?> Login(@RequestBody LoginReqestDTO loginReqestDTO) {
        LOGGER.info("Login method is called");
        return new ResponseEntity<CustomerDTO>(authService.Login(loginReqestDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/checkOnlyEmailIPresent/{email}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> checkOnlyEmailIPresent(@PathVariable String email) {
        LOGGER.info("checkEmailIsPresent method is called");
        return new ResponseEntity<String>(authService.checkOnlyEmailIPresent(email), HttpStatus.OK);
    }

    @RequestMapping(value = "/checkUsernameIsPresent/{username}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> checkUsernameIsPresent(@PathVariable String username) {
        LOGGER.info("checkUsernameIsPresent method is called");
        return new ResponseEntity<String>(authService.checkUsernameIsPresent(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestPart("username") String username,
                                        @RequestPart("password") String password) {

        LOGGER.info("uploadFile method is called");
        return new ResponseEntity<String>(authService.adminLogin(username, password), HttpStatus.OK);
    }


}
