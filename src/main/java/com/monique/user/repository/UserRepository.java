package com.monique.user.repository;

import com.monique.domain.User;
import com.monique.user.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value="select u.id, u.email, u.role, u.user_Name, u.lucky_Number from User u", nativeQuery = true)
    List<User> findAllUserForManagement();

    User findByEmailAndPassword(String email, String password);

}
