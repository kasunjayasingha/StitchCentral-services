package com.stitchcentral.stitchcentralservices.admin.entity;

import com.stitchcentral.stitchcentralservices.client.entity.Appointments;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String material;
    private int quantity;

    @Column(name = "swing_place")
    private String swingPlace;

    private double advance;
    private String description;

    @Column(name = "dispatch_date")
    @Temporal(TemporalType.DATE)
    private Date dispatchDate;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointments appointments;


}
