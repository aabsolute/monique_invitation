package com.monique.service;

import com.monique.common.enums.RoleType;
import com.monique.domain.User;
import com.monique.user.UserService;
import com.monique.user.dto.UserDTO;
import com.monique.user.repository.UserRepository;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@DataJpaTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void test_postUser() {

        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("fwmnefwe@nate.com");
        userDTO.setUserName("本田");
        userDTO.setLuckyNumber("34");
        userDTO.setRole(RoleType.USER);
        User user = User.builder(userDTO).build();
        userRepository.save(user);

        userDTO = new UserDTO();
        userDTO.setEmail("vvfrrr@nate.com");
        userDTO.setUserName("홍길동");
        userDTO.setLuckyNumber("99");
        userDTO.setRole(RoleType.ADMIN);
        // Given
        userRepository.save(User.builder(userDTO).build());

        // when
        String toFindKeyword ="Cat";
        List<User> userList = userRepository.findAll();
        // then
        User user0 = (User) userList.get(0);
        User user1 = (User) userList.get(1);
        System.out.println(userList);
        assertThat("本田".equals(user0.getUserName()));
        assertThat("fwmnefwe@nate.com".equals(user0.getEmail()));
        assertThat("vvfrrr@nate.com".equals(user1.getEmail()));
        assertThat(RoleType.ADMIN.toString().equals(user1.getEmail()));
    }
}
