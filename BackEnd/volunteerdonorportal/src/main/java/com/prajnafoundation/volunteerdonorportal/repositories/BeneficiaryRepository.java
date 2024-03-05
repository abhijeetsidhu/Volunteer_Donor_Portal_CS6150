package com.prajnafoundation.volunteerdonorportal.repositories;

import com.prajnafoundation.volunteerdonorportal.entities.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

}