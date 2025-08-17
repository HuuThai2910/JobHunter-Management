/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package edu.iuh.fit.backend.controller;

import edu.iuh.fit.backend.domain.Company;
import edu.iuh.fit.backend.domain.dto.ResultPaginationDTO;
import edu.iuh.fit.backend.service.CompanyService;
import edu.iuh.fit.backend.service.impl.CompanyServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@RestController
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/companies")
    public ResponseEntity<Company> createUser(@RequestBody @Valid Company company){
        Company newCompany = this.companyService.handleCreateCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCompany);
    }

    @GetMapping("/companies")
    public ResponseEntity<ResultPaginationDTO> getAllCompanies(@RequestParam("current") Optional<String> currentOptional,
                                                               @RequestParam("pageSize") Optional<String> pageSizeOptional){
        String sCurrent = currentOptional.orElse("");
        String sPageSize = pageSizeOptional.orElse("");
        int current = Integer.parseInt(sCurrent);
        int pageSize = Integer.parseInt(sPageSize);
        Pageable pageable = PageRequest.of(current - 1, pageSize);
        return ResponseEntity.ok(this.companyService.handleGetAllCompanies(pageable));
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = this.companyService.handleGetCompanyById(id);
        return ResponseEntity.ok(company);
    }

    @PutMapping("/companies")
    public ResponseEntity<Company> updateCompany(@RequestBody @Valid Company updatedCompany){
        Company company = this.companyService.handleUpdateCompany(updatedCompany);
        return ResponseEntity.ok(company);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id){
        this.companyService.handleDeletCompany(id);
        return ResponseEntity.ok(null);
    }
}
