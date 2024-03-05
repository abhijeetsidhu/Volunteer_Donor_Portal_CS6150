package com.prajnafoundation.volunteerdonorportal.repositories;

import com.prajnafoundation.volunteerdonorportal.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository  extends JpaRepository<Registration, Long> {
}
