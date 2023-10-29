package com.stitchcentral.stitchcentralservices.client.entity;

import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Appointments")
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date appointment_date;
    private String type;
    private String description;

    @Enumerated(EnumType.STRING)
    private AppoinmentStatus status;

    @OneToOne
    @JoinColumn(name = "customer_Id")
    private Customer customer;

    @OneToOne(mappedBy = "appointments")
    @JoinColumn(name = "client_sample_id", referencedColumnName = "id")
    private Client_Sample client_sample;
}
