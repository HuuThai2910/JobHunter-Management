/*
 * @ (#) .java    1.0       
 * Copyright (c)  IUH. All rights reserved.
 */
package edu.iuh.fit.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @description
 * @author: Huu Thai
 * @date:   
 * @version: 1.0
 */
@RestController
public class HelloController {
    @GetMapping("/")
    public String getHello(){
        return "Hello spring boot with alo with alo";
//        alo
    }
}
