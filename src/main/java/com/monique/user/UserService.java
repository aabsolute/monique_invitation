package com.monique.user;

import com.monique.domain.User;
import com.monique.user.dto.UserDTO;
import com.monique.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    //1. register user
    public void postUser(UserDTO userDTO){
        userRepository.save(User.builder(userDTO).build());
    }


    public UserDTO findUser(UserDTO userDTO){
        User user = User.builder(userDTO).build();
        return modelMapper.map(userRepository.findUserForLogIn(user.getEmail(), user.getPassword()), UserDTO.class);
    }




}
