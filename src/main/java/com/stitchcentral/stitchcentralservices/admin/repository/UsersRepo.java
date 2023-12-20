package com.stitchcentral.stitchcentralservices.admin.repository;

import com.stitchcentral.stitchcentralservices.admin.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByUserName(String userName);

    Optional<Users> findByUserNameAndPassword(String userName, String password);

}
