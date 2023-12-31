package com.stitchcentral.stitchcentralservices.controller;

import com.stitchcentral.stitchcentralservices.client.dto.CustomerDTO;
import com.stitchcentral.stitchcentralservices.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "api/v1/customer")
@CrossOrigin("*")
public class clientController {
    private static final Logger LOGGER = Logger.getLogger(clientController.class.getName());

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        LOGGER.info("saveCustomer method is called");
        return new ResponseEntity<String>(clientService.saveCustomer(customerDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/getCustomer/{email}", method = RequestMethod.GET, produces = "application/json")
    public List<CustomerDTO> getCustomer(@PathVariable String email) {
        LOGGER.info("getCustomer method is called");
        return clientService.getCustomer(email);
    }

    @RequestMapping(value = "/getAllCustomer", method = RequestMethod.GET, produces = "application/json")
    public List<CustomerDTO> getAllCustomer() {
        LOGGER.info("getAllCustomer method is called");
        return clientService.getAllCustomer();
    }

    @RequestMapping(value = "/updateCustomer", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customerDTO) {
        LOGGER.info("updateCustomer method is called");
        return new ResponseEntity<String>(clientService.updateCustomer(customerDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/saveContactUs", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveContactUs(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("message") String message) {
        LOGGER.info("saveContactUs method is called");
        return new ResponseEntity<String>(clientService.saveContactUs(name, email, message), HttpStatus.OK);
    }
}
