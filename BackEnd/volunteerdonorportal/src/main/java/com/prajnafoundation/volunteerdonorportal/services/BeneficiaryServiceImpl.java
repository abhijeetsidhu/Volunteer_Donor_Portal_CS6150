package com.prajnafoundation.volunteerdonorportal.services;

import com.prajnafoundation.volunteerdonorportal.entities.Beneficiary;
import com.prajnafoundation.volunteerdonorportal.models.BeneficiaryResponseObj;
import com.prajnafoundation.volunteerdonorportal.repositories.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;

    @Autowired
    public BeneficiaryServiceImpl(BeneficiaryRepository beneficiaryRepository) {
        this.beneficiaryRepository = beneficiaryRepository;
    }

    /**
     * Retrieves a list of all Beneficiaries.
     *
     * @return A BeneficiaryResponseObj containing the list of Beneficiaries and a log message.
     */
    @Override
    public BeneficiaryResponseObj getAllBeneficiaries() {
        List<Beneficiary> beneficiaries = beneficiaryRepository.findAll();
        String logMessage;
        if (beneficiaries.isEmpty()) {
            logMessage = "No beneficiaries exist!";
            return new BeneficiaryResponseObj(logMessage);
        }
        logMessage = "Successfully found " + beneficiaries.size() + " beneficiaries!";
        return new BeneficiaryResponseObj(beneficiaries, logMessage);
    }

    /**
     * Retrieves a Beneficiary by its unique identifier (ID).
     *
     * @param id The unique identifier of a Beneficiary.
     * @return A BeneficiaryResponseObj containing the Beneficiary information and a log message.
     */
    @Override
    public BeneficiaryResponseObj getBeneficiaryById(Long id) {
        String logMessage;
        if (id == null) {
            logMessage = "No beneficiary exists with id " + id + "!";
            return new BeneficiaryResponseObj(logMessage);
        }
        Optional<Beneficiary> beneficiaryOptional = beneficiaryRepository.findById(id);

        List<Beneficiary> beneficiaries = beneficiaryOptional.map(Collections::singletonList).orElseGet(Collections::emptyList);

        if (beneficiaries.isEmpty()) {
            logMessage = "No beneficiary exists with id " + id + "!";
            return new BeneficiaryResponseObj(logMessage);
        }
        logMessage = "Successfully found the beneficiary with Id: " + id;
        return new BeneficiaryResponseObj(beneficiaries, logMessage);
    }

    /**
     * Creates a new beneficiary.
     *
     * @param name                The name of the beneficiary.
     * @param beneficiarySinceDate The date when the beneficiary started receiving assistance.
     * @param birthDate           The birth date of the beneficiary.
     * @param gender              The gender of the beneficiary.
     * @return A BeneficiaryResponseObj containing the created beneficiary and a log message.
     */
    @Override
    @Transactional
    public BeneficiaryResponseObj createBeneficiary(String name, Date beneficiarySinceDate, Date birthDate, String gender) {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setName(name);
        beneficiary.setBeneficiarySinceDate(beneficiarySinceDate);
        beneficiary.setBirthDate(birthDate);
        beneficiary.setGender(gender);

        beneficiaryRepository.save(beneficiary);

        String logMessage = name + " has been successfully added to the list of beneficiaries in the foundation!";
        return new BeneficiaryResponseObj(logMessage);
    }

    /**
     * Updates details of an existing beneficiary.
     *
     * @param beneficiaryId       The unique identifier of the beneficiary to be updated.
     * @param name                The updated name of the beneficiary.
     * @param beneficiarySinceDate The updated date when the beneficiary started receiving assistance.
     * @param birthDate           The updated birth date of the beneficiary.
     * @param gender              The updated gender of the beneficiary.
     * @return A BeneficiaryResponseObj containing the updated beneficiary and a log message.
     */
    @Override
    @Transactional
    public BeneficiaryResponseObj updateBeneficiaryDetails(Long beneficiaryId, String name, Date beneficiarySinceDate, Date birthDate, String gender) {
        Optional<Beneficiary> beneficiaryOptional = beneficiaryRepository.findById(beneficiaryId);
        StringBuilder logMessage;

        if (beneficiaryOptional.isEmpty()) {
            logMessage= new StringBuilder("Beneficiary with ID "+ beneficiaryId + " not found.");
            return new BeneficiaryResponseObj(logMessage.toString());
        }

        Beneficiary beneficiary = beneficiaryOptional.get();
        logMessage=  new StringBuilder("Beneficiary with ID " + beneficiaryId + " has been updated. Updated fields: ");


        if (name != null) {
            beneficiary.setName(name);
            logMessage.append("name, ");
        }
        if (beneficiarySinceDate != null) {
            beneficiary.setBeneficiarySinceDate(beneficiarySinceDate);
            logMessage.append("beneficiarySinceDate, ");
        }
        if (birthDate != null) {
            beneficiary.setBirthDate(birthDate);
            logMessage.append("birthDate, ");
        }
        if (gender != null) {
            beneficiary.setGender(gender);
            logMessage.append("gender, ");
        }

        beneficiaryRepository.save(beneficiary);

        if (logMessage.toString().endsWith(", ")) {

            logMessage.delete(logMessage.length() - 2, logMessage.length());
        } else {
            logMessage = new StringBuilder("Nothing updated for beneficiary with ID " + beneficiaryId);
        }

        return new BeneficiaryResponseObj(logMessage.toString());
    }

    /**
     * Deletes a beneficiary by its unique identifier (ID).
     *
     * @param id The unique identifier of the beneficiary to be deleted.
     * @return A BeneficiaryResponseObj containing a log message.
     */
    public BeneficiaryResponseObj deleteBeneficiary(Long id) {
        String logMessage;
        Optional<Beneficiary> beneficiaryOptional = beneficiaryRepository.findById(id);

        if (beneficiaryOptional.isPresent()) {
            Beneficiary beneficiary = beneficiaryOptional.get();
            String beneficiaryName = beneficiary.getName();
            beneficiaryRepository.deleteById(id);
            logMessage = "Beneficiary " + beneficiaryName + " with Id: " + id + " has been deleted successfully!";
            return new BeneficiaryResponseObj(logMessage);
        }

        logMessage = "No beneficiary exists with id " + id + "!";
        return new BeneficiaryResponseObj(logMessage);
    }

}
