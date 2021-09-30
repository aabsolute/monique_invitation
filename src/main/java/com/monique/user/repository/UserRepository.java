package com.monique.user.repository;

import com.monique.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // findByXXX: XXX 컬럼을 키워드로 검색
    // Containing: 특정 키워드 포함 여부
    //    List<Posts> findByContentContainingIgnoreCaseOrTitleContainingIgnoreCase(String title, String content);
    User findByEmail(String email);
}
