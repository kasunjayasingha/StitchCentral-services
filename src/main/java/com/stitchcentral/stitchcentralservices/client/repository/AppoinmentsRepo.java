package com.stitchcentral.stitchcentralservices.client.repository;

import com.stitchcentral.stitchcentralservices.client.entity.Appointments;
import com.stitchcentral.stitchcentralservices.client.entity.Customer;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppoinmentsRepo extends JpaRepository<Appointments, Integer> {

    Optional<Appointments> findByCustomer_Id(int id);
    List<Appointments> findById(int id);
    Optional<Appointments> existsByCustomer_Id(int id);
    Optional<Appointments> findByCustomer_IdAndStatus(int id, AppoinmentStatus status);

    List<Appointments> findByCustomer_Email(String email);
    Optional<Appointments> findByStatusAndCustomer_Email(AppoinmentStatus status, String email);

}
