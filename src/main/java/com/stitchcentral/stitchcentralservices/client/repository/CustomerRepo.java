package com.stitchcentral.stitchcentralservices.client.repository;

import com.stitchcentral.stitchcentralservices.client.entity.Customer;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);

    List<Customer> findByEmailAndId(String email,int Id);

    Optional<Customer> existsByEmail(String email);

    Optional<Customer> findByEmailAndAppointments_Status(String email, AppoinmentStatus status);

    Optional<Customer> findByEmailAndPassword(String email, String password);

}
