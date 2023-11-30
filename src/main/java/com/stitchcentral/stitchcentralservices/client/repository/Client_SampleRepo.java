package com.stitchcentral.stitchcentralservices.client.repository;

import com.stitchcentral.stitchcentralservices.client.entity.Client_Sample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Client_SampleRepo extends JpaRepository<Client_Sample, Integer> {

    Optional<Client_Sample>  findByAppointments_Id(int id);
    List<Client_Sample> findByAppointments_IdOrAppointments_Customer_Email(int id, String email);

    Optional<Client_Sample> existsByAppointments_Id(int id);
    int deleteByAppointments_Id(int id);

}
