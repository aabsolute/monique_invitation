package com.monique.user;

import com.monique.domain.User;
import com.monique.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void registUser(User user){
        userRepository.save(user);
    }


}
