package com.coranavirus.coronavirus.repository;

import com.coranavirus.coronavirus.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {
    @Query("SELECT u from Users u where u.email = :email")
    Optional<Users> findOneByEmail(@Param("email") String email);
}
