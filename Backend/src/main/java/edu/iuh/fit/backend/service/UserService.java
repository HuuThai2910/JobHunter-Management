/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package edu.iuh.fit.backend.service;

import edu.iuh.fit.backend.domain.User;
import edu.iuh.fit.backend.domain.dto.ResCreateUserDTO;
import edu.iuh.fit.backend.domain.dto.ResUpdateUserDTO;
import edu.iuh.fit.backend.domain.dto.ResUserDTO;
import edu.iuh.fit.backend.domain.dto.ResultPaginationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
public interface UserService {
    ResCreateUserDTO createUser(User user);

    ResultPaginationDTO getAllUsers(Specification<User> specification, Pageable pageable);

    ResUserDTO getUserById(Long id);

    ResUpdateUserDTO updateUser(Long id, User updatedUser);

    void deleteUser(Long id);

    User getUserByUserName(String userName);


    void updateUserToken(String token, String email);

    User getUserByRefreshTokenAndEmail(String token, String email);
}