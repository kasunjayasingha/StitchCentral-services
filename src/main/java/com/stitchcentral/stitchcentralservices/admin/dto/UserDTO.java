package com.stitchcentral.stitchcentralservices.admin.dto;

import com.stitchcentral.stitchcentralservices.util.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private UserRoles role;
    private String userName;
    private String email;
    private String password;
    private String status;
    private Date createDate;
    private Date updateDate;
}
