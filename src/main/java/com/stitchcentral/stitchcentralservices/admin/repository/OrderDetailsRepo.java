package com.stitchcentral.stitchcentralservices.admin.repository;

import com.stitchcentral.stitchcentralservices.admin.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer> {

    List<OrderDetails> getAllByAppointments_Id(Integer id);



}
