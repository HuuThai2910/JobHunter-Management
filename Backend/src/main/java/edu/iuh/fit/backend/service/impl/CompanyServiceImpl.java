/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package edu.iuh.fit.backend.service.impl;

import edu.iuh.fit.backend.domain.Company;
import edu.iuh.fit.backend.repository.CompanyRepository;
import edu.iuh.fit.backend.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public Company handleCreateCompany(Company company){
        return this.companyRepository.save(company);
    }
}
