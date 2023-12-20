package com.stitchcentral.stitchcentralservices.admin.entity;

import com.stitchcentral.stitchcentralservices.util.enums.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @Column(name = "user_name")
    private String userName;

    private String email;
    private String password;
    private String status;

    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
}
