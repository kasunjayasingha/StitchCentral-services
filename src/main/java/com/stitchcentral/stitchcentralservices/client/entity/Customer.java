package com.stitchcentral.stitchcentralservices.client.entity;

import com.stitchcentral.stitchcentralservices.util.enums.CustomerTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "customer")
    private List<Appointments> appointments;

    @Enumerated(EnumType.STRING)
    private CustomerTypes customer_type;


    @Temporal(TemporalType.DATE)
    private Date create_date;

    @Temporal(TemporalType.DATE)
    private Date update_date;

}
