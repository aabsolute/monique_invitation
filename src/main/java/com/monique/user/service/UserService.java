package com.monique.user.service;

import com.monique.common.enums.CommonCode;
import com.monique.domain.Gallery;
import com.monique.domain.User;
import com.monique.gallery.dto.GalleryDTO;
import com.monique.user.dto.UserDTO;
import com.monique.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    ModelMapper modelMapper;

    // 1.register user
    @Transactional
    public void postUser(UserDTO userDTO) {
        userRepo.save(User.builder(userDTO).build());
    }

    // 2.search user by id,password for log-in
    @Transactional(readOnly = true)
    public UserDTO findUser(UserDTO userDTO)
    {
        User user = User.builder(userDTO).build();
        User result = userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword()).orElseThrow();

        return modelMapper.map(result, UserDTO.class);
    }

    // 3. admin userList -> All user except password
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUserListExceptPassword()
    {
        return userRepo.findAllUserForManagement().stream().map(p-> modelMapper.map(p,UserDTO.class)).collect(Collectors.toList());
    }


    // 4. admin userList -> All user with paging order by createdDATE
    public Page<User> getAllUserWithPaging(int pageNum)
    {
        return userRepo.findAll(PageRequest.of(pageNum==0? 0:pageNum-1,CommonCode.PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    }




}
