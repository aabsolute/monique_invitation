package com.monique.user;

import com.monique.domain.User;
import com.monique.user.dto.UserDTO;
import com.monique.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public void postUser(User user){
        userRepository.save(user);
    }

    public UserDTO getUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        //List<BoardDTO> boardDTO = board.stream().map(p-> modelMapper.map(p,BoardDTO.class)).collect(Collectors.toList());
        return modelMapper.map(user, UserDTO.class);
    }


}
