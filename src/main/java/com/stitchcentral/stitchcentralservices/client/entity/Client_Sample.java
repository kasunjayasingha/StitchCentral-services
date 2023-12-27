package com.stitchcentral.stitchcentralservices.client.entity;

import com.stitchcentral.stitchcentralservices.admin.entity.OrderDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Client_Sample")
@Builder
public class Client_Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "file_uuid")
    private String fileId;

    private String file_name;
    private String file_type;

    @Lob
    @Column(name = "file_data", length = 100000)
    private byte[] fileData;

    private String relative_path;

    //    private int customer_id;
    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointments appointments;

    @Temporal(TemporalType.DATE)
    private Date create_date;

    @Temporal(TemporalType.DATE)
    private Date update_date;

    @OneToOne(mappedBy = "clientSample")
    private OrderDetails orderDetails;

}
