package com.stitchcentral.stitchcentralservices.admin.service.Impl;

import com.stitchcentral.stitchcentralservices.admin.dto.UserDTO;
import com.stitchcentral.stitchcentralservices.admin.entity.Users;
import com.stitchcentral.stitchcentralservices.admin.repository.UsersRepo;
import com.stitchcentral.stitchcentralservices.admin.service.UserService;
import com.stitchcentral.stitchcentralservices.util.CommonResponse;
import com.stitchcentral.stitchcentralservices.util.enums.UserRoles;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UsersRepo usersRepo;

    @Override
    public String saveNewUser(UserDTO userDTO) {
        Logger.getLogger("saveNewUser method is called");
        System.out.println("UserDTO--- " + userDTO.toString());
        try {
            if (userDTO == null || userDTO.getUserName().isEmpty()) {
                return new CommonResponse(false, "UserDTO details are empty").toString();
            } else {
                Optional<Users> checkUser = usersRepo.findByUserName(userDTO.getUserName());
                if (checkUser.isPresent()) {
                    Logger.getLogger("User is present");

                    checkUser.get().setRole(UserRoles.valueOf(userDTO.getRole().toString()));
                    checkUser.get().setEmail(userDTO.getEmail());
                    checkUser.get().setFirstName(userDTO.getFirstName());
                    checkUser.get().setLastName(userDTO.getLastName());
                    checkUser.get().setStatus(userDTO.getStatus());
                    checkUser.get().setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
                    usersRepo.save(checkUser.get());
                    return new CommonResponse(true, "User is updated").toString();
                } else {
                    Logger.getLogger("User is not present");
                    Users users = new Users();
                    users.setUserName(userDTO.getUserName());
                    users.setRole(UserRoles.valueOf(userDTO.getRole().toString()));
                    users.setEmail(userDTO.getEmail());
                    users.setFirstName(userDTO.getFirstName());
                    users.setLastName(userDTO.getLastName());
                    users.setStatus(userDTO.getStatus());
                    users.setPassword(userDTO.getPassword());
                    users.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
                    users.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
                    usersRepo.save(users);
                    return new CommonResponse(true, "User is saved").toString();
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse(false, e.getMessage()).toString();

        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        Logger.getLogger("getAllUsers method is called");
        try {
            List<UserDTO> userDTOList = usersRepo.findAll().stream().map(data -> modelMapper.map(data, UserDTO.class)).collect(java.util.stream.Collectors.toList());
            return userDTOList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String deleteUser(UserDTO userDTO) {
        Logger.getLogger("deleteUser method is called");
        try {
            if (userDTO == null || userDTO.getUserName().isEmpty()) {
                return new CommonResponse(false, "UserDTO details are empty").toString();
            } else {
                Optional<Users> checkUser = usersRepo.findByUserName(userDTO.getUserName());
                if (checkUser.isPresent()) {
                    usersRepo.delete(checkUser.get());
                    return new CommonResponse(true, "User is deleted").toString();
                } else {
                    return new CommonResponse(false, "User is not present").toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse(false, e.getMessage()).toString();
        }
    }

    @Override
    public UserDTO getUserDetails(String username) {
        Logger.getLogger("getUserDetails method is called");
        try {
            if (username.isEmpty()) {
                return null;
            } else {
                Optional<Users> checkUser = usersRepo.findByUserName(username);
                if (checkUser.isPresent()) {
                    return modelMapper.map(checkUser.get(), UserDTO.class);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
