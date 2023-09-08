package com.example.gig_hunt.service.impl;

import com.example.gig_hunt.exception.NumberOfSymbolsDifferentFromRequiredException;
import com.example.gig_hunt.model.entity.Company;
import com.example.gig_hunt.model.repository.CompanyRepository;
import com.example.gig_hunt.model.repository.UserRepository;
import com.example.gig_hunt.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company readById(Long id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public Company createOrUpdate(Company company) throws NumberOfSymbolsDifferentFromRequiredException {
        int i = 10;
        if(company.getRegistrationNumber().toString().length() != 10) {
            throw new NumberOfSymbolsDifferentFromRequiredException(i);
        }
        return companyRepository.saveAndFlush(company);
    }

    @Override
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> readByName(String name) {
        return companyRepository.readByName(name);
    }

    @Override
    public Company readByRegistrationNumber(Long registrationNumber) {
        return companyRepository.readByRegistrationNumber(registrationNumber);
    }

    @Override
    public Company findCompanyInfoByUser(String nickname) {
        return companyRepository.findCompanyInfoByUser(nickname);
    }
}
