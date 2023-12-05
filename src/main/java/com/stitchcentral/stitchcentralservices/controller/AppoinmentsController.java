package com.stitchcentral.stitchcentralservices.controller;

import com.stitchcentral.stitchcentralservices.admin.dto.DashBoardDTO;
import com.stitchcentral.stitchcentralservices.admin.dto.OrderDetailsDTO;
import com.stitchcentral.stitchcentralservices.admin.service.OrderDetailsService;
import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;
import com.stitchcentral.stitchcentralservices.client.dto.ClientSampleDTO;
import com.stitchcentral.stitchcentralservices.client.service.AppointmentsService;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("api/v1/appoinments")
@CrossOrigin("*")
public class AppoinmentsController {
    private static final Logger LOGGER = Logger.getLogger(clientController.class.getName());
    @Autowired
    AppointmentsService appointmentsService;

    @Autowired
    OrderDetailsService orderDetailsService;
    String appointmentId;

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
    public List<AppointmentsDTO> getAllAppoinment(@PathVariable AppoinmentStatus status) throws DataFormatException, IOException {
        LOGGER.info("getAllAppoinment method is called");
        return appointmentsService.getAllAppoinment(status);
    }

    @RequestMapping(value = "/cancelAppoinment", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> cancelAppoinment(@RequestBody AppointmentsDTO appointmentsDTO) {
        LOGGER.info("cancelAppoinment method is called");
        return new ResponseEntity<String>(appointmentsService.cancelAppoinment(appointmentsDTO), HttpStatus.OK);
    }

//    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> uploadFile(@RequestPart("appointmentInfo") AppointmentsDTO appointmentsDTO,
//                                        @RequestPart("file")  MultipartFile file) {
//        LOGGER.info("uploadFile method is called");
//        return new ResponseEntity<String>(appointmentsService.uploadFile(appointmentsDTO,file), HttpStatus.OK);
//    }

    @RequestMapping(value = "/saveOrderDetails", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveOrderDetails(@RequestBody AppointmentsDTO appointmentsDTO) {
        LOGGER.info("saveOrderDetails method is called");
        return new ResponseEntity<String>(orderDetailsService.saveOrderDetails(appointmentsDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestPart("file") MultipartFile file,
                                        @RequestPart("appointmentId") String appointmentId
    ) {
        LOGGER.info("uploadFile method is called");
        return new ResponseEntity<String>(appointmentsService.uploadFile(file, appointmentId), HttpStatus.OK);
    }

    @RequestMapping(value = "/downloadFile/{appointmentId}", method = RequestMethod.GET)
    public ResponseEntity<?> downloadFile(@PathVariable Integer appointmentId) {
        LOGGER.info("downloadFile method is called");
        return new ResponseEntity<ClientSampleDTO>(appointmentsService.downloadFile(appointmentId), HttpStatus.OK);
    }

    @RequestMapping(value = "/getOrderDetails", method = RequestMethod.GET, produces = "application/json")
    public List<OrderDetailsDTO> getOrderDetails() {
        LOGGER.info("getOrderDetails method is called");
        return orderDetailsService.getOrderDetails();
    }

    @RequestMapping(value = "/updateOrderDetails", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateOrderDetails(@RequestBody OrderDetailsDTO orderDetailsDTO) {
        LOGGER.info("updateOrderDetails method is called");
        return new ResponseEntity<String>(orderDetailsService.updateOrderDetails(orderDetailsDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/getOrderCount", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getOrderCount() {
        LOGGER.info("getOrderCount method is called");
        return new ResponseEntity<Integer>(orderDetailsService.getOrderCount(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getDashboardDetails/{year}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getDashboardDetails(@PathVariable Integer year) {
        LOGGER.info("getDashboardDetails method is called");
        return new ResponseEntity<List<DashBoardDTO>>(appointmentsService.getDashboardDetails(year), HttpStatus.OK);
    }


//    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//    public ResponseEntity<?> uploadFile(@RequestParam("appoinment") AppointmentsDTO appointmentsDTO) {
//        LOGGER.info("uploadFile method is called");
//        return new ResponseEntity<String>(appointmentsService.uploadFile(appointmentsDTO), HttpStatus.OK);
//    }


}
