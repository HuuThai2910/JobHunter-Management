/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package edu.iuh.fit.backend.controller;

import edu.iuh.fit.backend.domain.ApiResponse;
import edu.iuh.fit.backend.domain.User;
import edu.iuh.fit.backend.service.UserService;
import edu.iuh.fit.backend.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;

    }

    @PostMapping("/users")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody @Valid User user) {
        User created = userService.createUser(user);
        var result = new ApiResponse<>(HttpStatus.CREATED.value(), "createUser", created, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        var result = new ApiResponse<>(HttpStatus.OK.value(), "getAllUsers", userService.getAllUsers(), null);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        return userService.getUserById(id).map(user -> {
            var response = new ApiResponse<>(HttpStatus.OK.value(), "getUserById", user, null);
            return ResponseEntity.ok(response);
        }).orElseGet(() -> {
            ApiResponse<User> errorResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(),
                    "Không tìm thấy user với ID: " + id, null, "USER_NOT_FOUND");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        });
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updated = userService.updateUser(id, user);
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
