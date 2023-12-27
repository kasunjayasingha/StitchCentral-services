package com.stitchcentral.stitchcentralservices.controller;

import com.stitchcentral.stitchcentralservices.admin.dto.UserDTO;
import com.stitchcentral.stitchcentralservices.admin.service.OrderDetailsService;
import com.stitchcentral.stitchcentralservices.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "api/v1/admin")
@CrossOrigin("*")
public class AdminController {
    private static final Logger LOGGER = Logger.getLogger(AdminController.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @RequestMapping(value = "/newUserSave", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveNewUser(@RequestBody UserDTO userDTO) {
        Logger.getLogger("saveNewUser method is called");
        return new ResponseEntity<String>(userService.saveNewUser(userDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllUsers() {
        Logger.getLogger("getAllUsers method is called");
        return new ResponseEntity<List<UserDTO>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> deleteUser(@RequestBody UserDTO userDTO) {
        Logger.getLogger("deleteUser method is called");
        return new ResponseEntity<String>(userService.deleteUser(userDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/getUserDetails/{username}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getUserDetails(@PathVariable String username) {
        Logger.getLogger("getUserDetails method is called");
        return new ResponseEntity<UserDTO>(userService.getUserDetails(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/uploadDesign", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestPart("file") MultipartFile file,
                                        @RequestPart("orderId") String orderId
    ) {
        LOGGER.info("uploadFile method is called");
        return new ResponseEntity<String>(orderDetailsService.uploadDesign(file, orderId), HttpStatus.OK);
    }
}
