/*
 * @ (#) .java    1.0       
 * Copyright (c)  IUH. All rights reserved.
 */
package edu.iuh.fit.backend.service;/*
 * @description
 * @author: Huu Thai
 * @date:   
 * @version: 1.0
 */

import edu.iuh.fit.backend.domain.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company handleCreateCompany(Company company);

    List<Company> handleGetAllCompanies();

    Company handleGetCompanyById(Long id);

    Company handleUpdateCompany(Company updatedCompany);

    void handleDeletCompany(Long id);
}
