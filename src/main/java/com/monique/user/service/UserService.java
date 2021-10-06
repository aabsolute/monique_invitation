package com.monique.user.service;

import com.monique.domain.User;
import com.monique.user.dto.UserDTO;
import com.monique.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    // 좋음
//    Optional<Member> member = ...;
//return member.orElseGet(Member::new);  // member에 값이 없을 때만 new Member()가 실행됨

    // 1.register user
    @Transactional
    public void postUser(UserDTO userDTO) {
        userRepository.save(User.builder(userDTO).build());
    }

    // 2.search user by id,password for log-in
    @Transactional(readOnly = true)
    public UserDTO findUser(UserDTO userDTO) {
        User user = User.builder(userDTO).build();
        User result = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).orElseThrow();

        return modelMapper.map(result, UserDTO.class);
    }

    // 3. admin userList -> All user except password
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUserListExceptPassword()
    {
        return userRepository.findAllUserForManagement().stream().map(p-> modelMapper.map(p,UserDTO.class)).collect(Collectors.toList());
    }

    // 4. admin userList -> All user with paging
    public Page<User> getAllUserWithPaging(int startAt) {
        Pageable pageable = PageRequest.of(startAt, 10);
        return  userRepository.findAll(pageable);
    }




}
