/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package edu.iuh.fit.backend.service.impl;

import edu.iuh.fit.backend.domain.User;
import edu.iuh.fit.backend.domain.dto.ResCreateUserDTO;
import edu.iuh.fit.backend.domain.dto.ResUpdateUserDTO;
import edu.iuh.fit.backend.domain.dto.ResUserDTO;
import edu.iuh.fit.backend.mapper.UserMapper;
import edu.iuh.fit.backend.repository.UserRepository;
import edu.iuh.fit.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ResCreateUserDTO createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        User newUser = this.userRepository.save(user);
        return this.userMapper.toResCreateUserDTO(newUser);
    }

    @Override
    public List<ResUserDTO> getAllUsers() {
        List<User> users =  userRepository.findAll();
        return this.userMapper.toResListUserDTO(users);
    }

    @Override
    public ResUserDTO getUserById(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return this.userMapper.toResUserDTO(user);
    }

    @Override
    public ResUpdateUserDTO updateUser(Long id, User updatedUser) {
        return this.userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setGender(updatedUser.getGender());
            user.setAddress(updatedUser.getAddress());
            user.setAge(updatedUser.getAge());
            User currentUser = this.userRepository.save(user);
            return this.userMapper.toResUpdateUserDTO(currentUser);
        }).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByUserName(String userName) {
        return this.userRepository.findByEmail(userName);
    }

    @Override
    public ResCreateUserDTO convertToResCreateUserDTO(User user) {
        ResCreateUserDTO resCreateUserDTO = new ResCreateUserDTO();
        resCreateUserDTO.setId(user.getId());
        resCreateUserDTO.setName(user.getName());
        resCreateUserDTO.setEmail(user.getEmail());
        resCreateUserDTO.setAge(user.getAge());
        resCreateUserDTO.setGender(user.getGender());
        resCreateUserDTO.setAddress(user.getAddress());
        resCreateUserDTO.setCreateAt(user.getCreateAt());
        return resCreateUserDTO;
    }
}
