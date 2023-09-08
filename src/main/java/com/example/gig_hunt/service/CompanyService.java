package com.example.gig_hunt.service;

import com.example.gig_hunt.model.entity.Company;

import java.util.List;

public interface CompanyService extends DefaultService<Company> {

    List<Company> readByName(String name);
    Company readByRegistrationNumber(Long registrationNumber);
    Company findCompanyInfoByUser(String nickname);

}
