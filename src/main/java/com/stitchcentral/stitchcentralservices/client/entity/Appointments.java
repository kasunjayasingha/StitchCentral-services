package com.stitchcentral.stitchcentralservices.client.entity;

import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentType;
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

    @Temporal(TemporalType.DATE)
    private Date appointment_date;

    @Enumerated(EnumType.STRING)
    private AppoinmentType type;

    private String description;

    @Enumerated(EnumType.STRING)
    private AppoinmentStatus status;

    @OneToOne
    @JoinColumn(name = "customer_Id")
    private Customer customer;

    @OneToOne(mappedBy = "appointments")
    private Client_Sample client_sample;
}
