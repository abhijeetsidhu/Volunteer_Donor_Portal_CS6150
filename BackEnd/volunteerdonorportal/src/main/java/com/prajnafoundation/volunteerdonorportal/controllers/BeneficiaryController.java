package com.prajnafoundation.volunteerdonorportal.controllers;

import com.prajnafoundation.volunteerdonorportal.models.BeneficiaryResponseObj;
import com.prajnafoundation.volunteerdonorportal.services.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/beneficiary")
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    @Autowired
    public BeneficiaryController(BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

    @GetMapping
    public BeneficiaryResponseObj getBeneficiaries(@RequestParam(name = "id", required = false) Long id) {
        if (id == null) {
            return beneficiaryService.getAllBeneficiaries();
        } else {
            return beneficiaryService.getBeneficiaryById(id);
        }
    }

    @PostMapping
    public BeneficiaryResponseObj createBeneficiary(@RequestParam String name,
                                                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date beneficiarySinceDate,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate,
                                                    @RequestParam String gender) {
        return beneficiaryService.createBeneficiary(name, beneficiarySinceDate, birthDate, gender);
    }

    @PutMapping
    public BeneficiaryResponseObj updateBeneficiaryDetails(@RequestParam Long beneficiaryId,
                                                           @RequestParam(required = false) String name,
                                                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date beneficiarySinceDate,
                                                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate,
                                                           @RequestParam(required = false) String gender) {
        return beneficiaryService.updateBeneficiaryDetails(beneficiaryId, name, beneficiarySinceDate, birthDate, gender);
    }

    @DeleteMapping
    public BeneficiaryResponseObj deleteBeneficiary(@RequestParam Long id) {
        return beneficiaryService.deleteBeneficiary(id);
    }
}
