package com.monique.user.repository;

import com.monique.domain.Board;
import com.monique.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query(value="select new com.monique.domain.User(u.id, u.email,  u.userName, u.role,u.luckyNumber )from User u")
    List<User> findAllUserForManagement();

    Optional<User> findByEmailAndPassword(String email, String password);

}
