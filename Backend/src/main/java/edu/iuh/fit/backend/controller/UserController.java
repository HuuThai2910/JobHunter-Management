/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package edu.iuh.fit.backend.controller;

import edu.iuh.fit.backend.domain.dto.ResCreateUserDTO;
import edu.iuh.fit.backend.domain.dto.ResUpdateUserDTO;
import edu.iuh.fit.backend.domain.dto.ResUserDTO;
import edu.iuh.fit.backend.domain.response.ApiResponse;
import edu.iuh.fit.backend.domain.User;
import edu.iuh.fit.backend.service.UserService;
import edu.iuh.fit.backend.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserServiceImpl userServiceImpl, PasswordEncoder passwordEncoder) {
        this.userService = userServiceImpl;
        this.passwordEncoder = passwordEncoder;

    }

    @PostMapping("/users")
    public ResponseEntity<ApiResponse<ResCreateUserDTO>> createUser(@RequestBody @Valid User user) {
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        ResCreateUserDTO dto = this.userService.createUser(user);
        var result = new ApiResponse<>(HttpStatus.CREATED.value(), "createUser", dto, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<ResUserDTO>>> getAllUsers() {
        List<ResUserDTO> resUserDTOS = this.userService.getAllUsers();
        var result = new ApiResponse<>(HttpStatus.OK.value(), "getAllUsers", resUserDTOS, null);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<ResUserDTO>> getUserById(@PathVariable Long id) {
        ResUserDTO resUserDTO = this.userService.getUserById(id);
        var response = new ApiResponse<>(HttpStatus.OK.value(), "getUserById", resUserDTO, null);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<ApiResponse<ResUpdateUserDTO>> updateUser(@PathVariable Long id, @RequestBody User user) {
        ResUpdateUserDTO updated = userService.updateUser(id, user);
        var result = new ApiResponse<>(HttpStatus.CREATED.value(), "updateUser", updated, null);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        var result = new ApiResponse<>(HttpStatus.OK.value(), "deleteUser", "delete successfully", null);
        return ResponseEntity.ok(result);
    }
}
