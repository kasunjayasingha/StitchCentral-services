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
@Table(name = "Client_Sample")
public class Client_Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String file_name;
    private String file_type;
    private String path;
    private String relative_path;

//    private int customer_id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointments appointments;

    @Temporal(TemporalType.DATE)
    private Date create_date;

    @Temporal(TemporalType.DATE)
    private Date update_date;
}
