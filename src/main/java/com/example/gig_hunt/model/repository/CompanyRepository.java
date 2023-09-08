package com.example.gig_hunt.model.repository;

import com.example.gig_hunt.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> readByName(String name);
    Company readByRegistrationNumber(Long registrationNumber);

    @Query(value = "SELECT c.company_id, c.name, c.registration_number, c.user_id, u.nickname "
            + "FROM company c INNER JOIN user u ON c.user_id = u.user_id WHERE u.nickname = :nickname", nativeQuery = true)
    Company findCompanyInfoByUser(@Param("nickname") String nickname);

}
