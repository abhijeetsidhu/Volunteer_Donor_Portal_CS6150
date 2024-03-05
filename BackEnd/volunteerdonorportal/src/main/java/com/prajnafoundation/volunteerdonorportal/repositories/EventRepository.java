package com.prajnafoundation.volunteerdonorportal.repositories;

import com.prajnafoundation.volunteerdonorportal.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
