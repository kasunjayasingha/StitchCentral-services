package com.stitchcentral.stitchcentralservices.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientSampleDTO {

    private int id;
    private String file_name;
    private String file_type;
    private byte[] fileData;
    private String relative_path;
    private int appointment_id;
    private Date create_date;
    private Date update_date;
}
