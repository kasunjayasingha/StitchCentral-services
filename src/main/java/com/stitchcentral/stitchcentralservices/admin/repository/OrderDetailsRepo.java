package com.stitchcentral.stitchcentralservices.admin.repository;

import com.stitchcentral.stitchcentralservices.admin.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer> {

    List<OrderDetails> getAllByAppointments_Id(Integer id);

    List<OrderDetails> findAllByOrderStatus(String status);

    List<OrderDetails> findAllByUpdateDateBetween(Date startDate, Date endDate);


}
