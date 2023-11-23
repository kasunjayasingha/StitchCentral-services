package com.stitchcentral.stitchcentralservices.controller;

import com.stitchcentral.stitchcentralservices.admin.dto.OrderDetailsDTO;
import com.stitchcentral.stitchcentralservices.admin.service.OrderDetailsService;
import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;
import com.stitchcentral.stitchcentralservices.client.service.AppointmentsService;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/appoinments")
@CrossOrigin("*")
public class AppoinmentsController {
    private static final Logger LOGGER = Logger.getLogger(clientController.class.getName());
    @Autowired
    AppointmentsService appointmentsService;

    @Autowired
    OrderDetailsService orderDetailsService;

    @RequestMapping(value = "/saveAppoinment", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveAppoinment(@RequestBody AppointmentsDTO appointmentsDTO) {

        LOGGER.info("saveAppoinment method is called");
        return new ResponseEntity<String>(appointmentsService.saveAppointment(appointmentsDTO), HttpStatus.OK);

    }

    @RequestMapping(value = "/getAppoinment/{email}", method = RequestMethod.GET, produces = "application/json")
    public List<AppointmentsDTO> getAppoinment(@PathVariable String email) {
        LOGGER.info("getAppoinment method is called");
        return appointmentsService.getAppoinment(email);
    }

    @RequestMapping(value = "/updateAppoinment", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> updateAppoinment(@RequestBody AppointmentsDTO appointmentsDTO) {
        LOGGER.info("updateAppoinment method is called");
        return new ResponseEntity<String>(appointmentsService.updateAppoinment(appointmentsDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteAppoinment/{email}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> deleteAppoinment(@PathVariable String email) {
        LOGGER.info("deleteAppoinment method is called");
        return new ResponseEntity<String>(appointmentsService.deleteAppoinment(email), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllAppoinment/{status}", method = RequestMethod.GET, produces = "application/json")
    public List<AppointmentsDTO> getAllAppoinment(@PathVariable AppoinmentStatus status) {
        LOGGER.info("getAllAppoinment method is called");
        return appointmentsService.getAllAppoinment(status);
    }

    @RequestMapping(value = "/cancelAppoinment", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> cancelAppoinment(@RequestBody AppointmentsDTO appointmentsDTO) {
        LOGGER.info("cancelAppoinment method is called");
        return new ResponseEntity<String>(appointmentsService.cancelAppoinment(appointmentsDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/saveOrderDetails", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveOrderDetails(@RequestBody OrderDetailsDTO orderDetailsDTO) {
        LOGGER.info("saveOrderDetails method is called");
        return new ResponseEntity<String>(orderDetailsService.saveOrderDetails(orderDetailsDTO), HttpStatus.OK);
    }

}
