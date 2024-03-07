package com.prajnafoundation.volunteerdonorportal.services;

import com.prajnafoundation.volunteerdonorportal.models.EventResponseObj;

import java.util.Date;

public interface EventService {
    EventResponseObj getAllEvents();

    EventResponseObj getEventById(Long id);

    EventResponseObj createEvent(String eventName, Date date, String description, String location, int registrationHeadCount);

    EventResponseObj updateEvent(Long eventId, String eventName, Date date, String description, String location, Integer registrationHeadCount);

    EventResponseObj deleteEvent(Long eventId);



}
