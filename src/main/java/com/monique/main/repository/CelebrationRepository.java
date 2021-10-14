package com.monique.main.repository;

import com.monique.domain.Celebration;
import com.monique.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CelebrationRepository extends JpaRepository<Celebration, Long> {

}
