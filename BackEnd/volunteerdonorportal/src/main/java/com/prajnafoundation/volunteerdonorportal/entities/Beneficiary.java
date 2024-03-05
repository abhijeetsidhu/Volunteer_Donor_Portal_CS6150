package com.prajnafoundation.volunteerdonorportal.entities;

import jakarta.persistence.*;
import java.util.Date;
@Entity
@Table(name = "beneficiary_table")
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beneficiary_id")
    private Long beneficiaryId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "beneficiary_since_date")
    private Date beneficiarySinceDate;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "gender")
    private String gender;

    // Getters and setters
    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeneficiarySinceDate() {
        return beneficiarySinceDate;
    }

    public void setBeneficiarySinceDate(Date beneficiarySinceDate) {
        this.beneficiarySinceDate = beneficiarySinceDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}