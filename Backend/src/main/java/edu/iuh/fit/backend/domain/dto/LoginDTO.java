/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package edu.iuh.fit.backend.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDTO {
    @NotBlank(message = "User name cannot be blank")
    private String userName;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
