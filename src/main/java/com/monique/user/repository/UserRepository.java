package com.monique.user.repository;

import com.monique.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value="select * from User where email = :email and password = :password", nativeQuery = true)
    User findUserForLogIn(@Param("email") String email, @Param("password") String password);

}
