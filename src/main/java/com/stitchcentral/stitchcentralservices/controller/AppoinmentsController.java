package com.stitchcentral.stitchcentralservices.controller;

import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;
import com.stitchcentral.stitchcentralservices.client.service.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/appoinments")
public class AppoinmentsController {
    private static final Logger LOGGER = Logger.getLogger(clientController.class.getName());
    @Autowired
    AppointmentsService appointmentsService;

    @RequestMapping(value = "/saveAppoinment", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveAppoinment(@RequestBody AppointmentsDTO appointmentsDTO) {

        LOGGER.info("saveAppoinment method is called ");
        return new ResponseEntity<String>(appointmentsService.saveAppointment(appointmentsDTO), HttpStatus.OK);

    }
}
