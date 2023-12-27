package com.stitchcentral.stitchcentralservices.admin.service;


import com.stitchcentral.stitchcentralservices.admin.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    String saveNewUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    String deleteUser(UserDTO userDTO);

    UserDTO getUserDetails(String username);

    String uploadDesign(MultipartFile file, String orderId);
}
