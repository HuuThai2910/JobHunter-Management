/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package edu.iuh.fit.backend.controller;

import edu.iuh.fit.backend.domain.User;
import edu.iuh.fit.backend.domain.dto.LoginDTO;
import edu.iuh.fit.backend.domain.dto.ResLoginDTO;
import edu.iuh.fit.backend.service.UserService;
import edu.iuh.fit.backend.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityUtil securityUtil;
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<ResLoginDTO> login(@RequestBody @Valid LoginDTO loginDTO){
        //Nạp input gồm username/password vào Security
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword());

        //xác thực người dùng => cần viết hàm loadUserByUsername
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

//        Create a token
        String access_token = this.securityUtil.createToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ResLoginDTO res = new ResLoginDTO();
        User currentUser = this.userService.getUserByUserName(loginDTO.getUserName());
        ResLoginDTO.UserLogin userLogin = new ResLoginDTO.UserLogin(currentUser.getId(), currentUser.getEmail(), currentUser.getName());
        res.setUserLogin(userLogin);
        res.setAccessToken(access_token);
        return ResponseEntity.ok().body(res);
    }
}
