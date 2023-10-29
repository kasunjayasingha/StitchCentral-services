package com.stitchcentral.stitchcentralservices.client.repository;

import com.stitchcentral.stitchcentralservices.client.entity.Appointments;
import com.stitchcentral.stitchcentralservices.client.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppoinmentsRepo extends JpaRepository<Appointments, Integer> {

    Optional<Appointments> findByCustomer_Id(int id);
    List<Appointments> findById(int id);
}
