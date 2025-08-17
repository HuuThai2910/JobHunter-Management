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

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Override
    public List<Company> handleGetAllCompanies(){
        return this.companyRepository.findAll();
    }

    @Override
    public Company handleGetCompanyById(Long id){
        return this.companyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
    }

    @Override
    public Company handleUpdateCompany(Company updatedCompany){
        return this.companyRepository.findById(updatedCompany.getId())
                .map(company -> {
                    company.setName(updatedCompany.getName());
                    company.setDescription(updatedCompany.getDescription());
                    company.setAddress(updatedCompany.getAddress());
                    company.setLogo(updatedCompany.getLogo());
                    return this.companyRepository.save(company);
                }).orElseThrow(() -> new NoSuchElementException("Company not found"));
    }

    @Override
    public void handleDeletCompany(Long id){
        if(!companyRepository.existsById(id)){
            throw new NoSuchElementException("Company not found");
        }
        this.companyRepository.deleteById(id);
    }

}
