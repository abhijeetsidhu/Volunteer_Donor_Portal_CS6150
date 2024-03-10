package com.prajnafoundation.volunteerdonorportal.services;

import com.prajnafoundation.volunteerdonorportal.models.BeneficiaryResponseObj;

import java.util.Date;

public interface BeneficiaryService {


    BeneficiaryResponseObj getAllBeneficiaries();

    BeneficiaryResponseObj getBeneficiaryById(Long id);

    BeneficiaryResponseObj createBeneficiary(String name, Date beneficiarySinceDate, Date birthDate, String gender);

    BeneficiaryResponseObj updateBeneficiaryDetails(Long beneficiaryId, String name, Date beneficiarySinceDate, Date birthDate, String gender);

    BeneficiaryResponseObj deleteBeneficiary(Long id);
}
