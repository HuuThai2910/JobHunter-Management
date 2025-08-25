/*
 * @ (#) .java    1.0       
 * Copyright (c)  IUH. All rights reserved.
 */
package edu.iuh.fit.backend.mapper;

import edu.iuh.fit.backend.domain.Company;
import edu.iuh.fit.backend.domain.User;
import edu.iuh.fit.backend.dto.response.CompanySummaryResponse;
import edu.iuh.fit.backend.dto.response.CreateUserResponse;
import edu.iuh.fit.backend.dto.response.UpdateUserResponse;
import edu.iuh.fit.backend.dto.response.UserResponse;
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
    @Mapping(target = "createdAt", source = "createdAt")
    CreateUserResponse toResCreateUserDTO(User user);

    // Map User -> ResUpdateUserDTO
    UpdateUserResponse toResUpdateUserDTO(User user);

    //    Map User -> ResUserDTO
    UserResponse toResUserDTO(User user);

    List<UserResponse> toResListUserDTO(List<User> users);

    CompanySummaryResponse toCompanySummaryResponse(Company company);
}
