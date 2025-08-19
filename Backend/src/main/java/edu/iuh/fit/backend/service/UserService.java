/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package edu.iuh.fit.backend.service;

import edu.iuh.fit.backend.domain.User;
import edu.iuh.fit.backend.domain.dto.ResCreateUserDTO;
import edu.iuh.fit.backend.domain.dto.ResUpdateUserDTO;
import edu.iuh.fit.backend.domain.dto.ResUserDTO;

import java.util.List;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
public interface UserService {
    ResCreateUserDTO createUser(User user);

    List<ResUserDTO> getAllUsers();

    ResUserDTO getUserById(Long id);

    ResUpdateUserDTO updateUser(Long id, User updatedUser);

    void deleteUser(Long id);

    User getUserByUserName(String userName);

    ResCreateUserDTO convertToResCreateUserDTO(User user);
}