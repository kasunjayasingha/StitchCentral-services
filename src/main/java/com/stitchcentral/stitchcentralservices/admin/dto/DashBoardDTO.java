package com.stitchcentral.stitchcentralservices.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DashBoardDTO {
    private int totalOrders;
    private int totalCustomers;
    private int totalPendingOrders;
    private int totalPendingAppointments;
    private int totalRevenue;
    private int month;
    private int year;
    private int montlyRevenue;
    private int montlyOrders;
    private List<DashBoardDTO> dashBoardDTOList;
}
