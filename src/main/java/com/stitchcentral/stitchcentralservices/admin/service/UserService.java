package com.stitchcentral.stitchcentralservices.admin.service;


import com.stitchcentral.stitchcentralservices.admin.dto.UserDTO;

import java.util.List;

public interface UserService {
    String saveNewUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    String deleteUser(UserDTO userDTO);

    UserDTO getUserDetails(String username);


}
