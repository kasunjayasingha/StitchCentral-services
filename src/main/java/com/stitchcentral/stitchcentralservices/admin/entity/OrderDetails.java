package com.stitchcentral.stitchcentralservices.admin.entity;

import com.stitchcentral.stitchcentralservices.client.entity.Appointments;
import com.stitchcentral.stitchcentralservices.client.entity.Client_Sample;
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

    @Column(name = "order_type")
    private String orderType;

    private String material;
    private int quantity;

    @Column(name = "swing_place")
    private String swingPlace;

    private String payment;
    private Integer advance;
    private String description;

    @Column(name = "dispatch_date")
    @Temporal(TemporalType.DATE)
    private Date dispatchDate;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointments appointments;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "order_status")
    private String orderStatus;

    @OneToOne
    @JoinColumn(name = "client_sample_id")
    private Client_Sample clientSample;


}
