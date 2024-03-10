package com.prajnafoundation.volunteerdonorportal.models;

import com.prajnafoundation.volunteerdonorportal.entities.Beneficiary;
import jakarta.persistence.Column;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BeneficiaryResponseObj extends LogMessage {

    private List<BeneficiaryObj> beneficiaries = null;

    public BeneficiaryResponseObj(List<Beneficiary> beneficiaries, String logMessage) {
        super(logMessage);
        this.beneficiaries = beneficiaries.stream().map(BeneficiaryObj::new).toList();
    }

    public BeneficiaryResponseObj(String logMessage) {
        super(logMessage);
    }

    public List<BeneficiaryObj> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(List<BeneficiaryObj> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }


    private static class BeneficiaryObj {
        Long beneficiaryId;
        String name;
        String beneficiarySinceDate;
        String birthDate;
        String gender;


        public BeneficiaryObj(Beneficiary beneficiary) {
            this.beneficiaryId = beneficiary.getBeneficiaryId();
            this.name = beneficiary.getName();
            Date sinceDate = beneficiary.getBeneficiarySinceDate();
            if(sinceDate == null){
                this.beneficiarySinceDate = null;
            }
            else{
                this.beneficiarySinceDate = new SimpleDateFormat("yyyy-MM-dd").format(sinceDate);
            }
            this.birthDate = new SimpleDateFormat("yyyy-MM-dd").format(beneficiary.getBirthDate());
            this.gender = beneficiary.getGender();
        }

        public Long getBeneficiaryId() {
            return beneficiaryId;
        }

        public String getName() {
            return name;
        }

        public String getBeneficiarySinceDate() {
            return beneficiarySinceDate;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public String getGender() {
            return gender;
        }
    }
}
