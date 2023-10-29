package com.stitchcentral.stitchcentralservices.client.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String first_name;
    private String last_name;
    private String address;
    private String city;
    private String postal_code;
    private String company;
    private String university;
    private String club;
    private String email;
    private String phone_no;
    private String password;

    @OneToOne(mappedBy = "customer")
    private Appointments appointments;


    @Temporal(TemporalType.DATE)
    private Date create_date;

    @Temporal(TemporalType.DATE)
    private Date update_date;

}
