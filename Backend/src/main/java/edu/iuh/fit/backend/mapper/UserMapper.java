/*
 * @ (#) .java    1.0       
 * Copyright (c)  IUH. All rights reserved.
 */
package edu.iuh.fit.backend.mapper;

import edu.iuh.fit.backend.domain.User;
import edu.iuh.fit.backend.domain.dto.ResCreateUserDTO;
import edu.iuh.fit.backend.domain.dto.ResUpdateUserDTO;
import edu.iuh.fit.backend.domain.dto.ResUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/*
 * @description
 * @author: Huu Thai
 * @date:   
 * @version: 1.0
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    // Map User -> ResCreateUserDTO
    @Mapping(target = "createAt", source = "createAt")
    ResCreateUserDTO toResCreateUserDTO(User user);

    // Map User -> ResUpdateUserDTO
    ResUpdateUserDTO toResUpdateUserDTO(User user);

    //    Map User -> ResUserDTO
    ResUserDTO toResUserDTO(User user);

    List<ResUserDTO> toResListUserDTO(List<User> users);
}
