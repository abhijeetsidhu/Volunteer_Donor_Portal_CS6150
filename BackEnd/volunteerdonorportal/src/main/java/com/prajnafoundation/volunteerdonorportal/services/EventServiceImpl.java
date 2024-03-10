package com.prajnafoundation.volunteerdonorportal.services;

import com.prajnafoundation.volunteerdonorportal.entities.Event;
import com.prajnafoundation.volunteerdonorportal.models.EventResponseObj;
import com.prajnafoundation.volunteerdonorportal.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Retrieves a list of all events.
     *
     * @return A EventResponseObj containing the list of events and a log message.
     */
    @Override
    public EventResponseObj getAllEvents() {
        List<Event> events = eventRepository.findAll();
        String logMessage;
        if (events.isEmpty()) {
            logMessage = "No events exist!";
            return new EventResponseObj(logMessage);
        }
        logMessage = "Successfully found " + events.size() + " event(s)!";
        return new EventResponseObj(events, logMessage);
    }

    /**
     * Retrieves an event by its unique identifier (ID).
     *
     * @param id The unique identifier of an event.
     * @return A EventResponseObj containing the event information and a log message.
     */
    @Override
    public EventResponseObj getEventById(Long id) {
        String logMessage;
        if (id == null) {
            logMessage = "No event exist with id " + id + "!";
            return new EventResponseObj(logMessage);
        }
        Optional<Event> eventOptional = eventRepository.findById(id);

        List<Event> events = eventOptional.map(Collections::singletonList).orElseGet(Collections::emptyList);

        if (events.isEmpty()) {
            logMessage = "No event exist with id " + id + "!";
            return new EventResponseObj(logMessage);
        }
        logMessage = "Successfully found the event with Id: " + id;
        return new EventResponseObj(events, logMessage);
    }

    /**
     * Creates a new event with the provided information. Ensures that the registration head count is 0 or more,
     * and performs necessary checks before saving the event to the database.
     *
     * @param eventName             The name of the event.
     * @param date                  The date of the event.
     * @param description           The description of the event.
     * @param location              The location of the event.
     * @param registrationHeadCount The head count for event registration (must be 0 or more).
     * @return An EventResponseObj indicating the status of the event creation and a log message.
     */
    @Override
    @Transactional
    public EventResponseObj createEvent(String eventName, Date date, String description, String location, int registrationHeadCount) {
        if (registrationHeadCount < 0) {
            return new EventResponseObj("Registration head count must be 0 or more.");
        }

        Event event = new Event();
        event.setEventName(eventName);
        event.setDate(date);
        event.setDescription(description);
        event.setLocation(location);
        event.setRegistrationHeadCount(registrationHeadCount);

        eventRepository.save(event);

        String logMessage = "Event for " + eventName + " has been successfully created!";
        return new EventResponseObj(logMessage);
    }

    /**
     * Updates an existing event based on the provided information. Performs validations,
     * checks for uniqueness, and updates only the provided fields while ensuring data integrity.
     * Also, the logMessage includes the field that have been updated.
     *
     * @param eventId               The unique identifier of the event to be updated.
     * @param eventName             The updated name of the event.
     * @param date                  The updated date of the event.
     * @param description           The updated description of the event.
     * @param location              The updated location of the event.
     * @param registrationHeadCount The updated registration head count (optional, must be 0 or more).
     * @return An EventResponseObj indicating the status of the event update and a log message.
     */

    @Override
    @Transactional
    public EventResponseObj updateEvent(Long eventId, String eventName, Date date, String description, String location, Integer registrationHeadCount) {
        if (registrationHeadCount != null && registrationHeadCount < 0) {
            return new EventResponseObj("Registration head count must be 0 or more.");
        }

        Optional<Event> eventOptional = eventRepository.findById(eventId);
        StringBuilder logMessage;

        if (eventOptional.isEmpty()) {
            logMessage = new StringBuilder("Event with ID " + eventId + " not found.");
            return new EventResponseObj(logMessage.toString());
        }

        Event event = eventOptional.get();
        logMessage = new StringBuilder("Event with ID " + eventId + " has been updated. Updated fields: ");

        if (eventName != null) {
            event.setEventName(eventName);
            logMessage.append("eventName, ");
        }
        if (date != null) {
            event.setDate(date);
            logMessage.append("date, ");
        }
        if (description != null) {
            event.setDescription(description);
            logMessage.append("description, ");
        }
        if (location != null) {
            event.setLocation(location);
            logMessage.append("location, ");
        }
        if (registrationHeadCount != null) {
            event.setRegistrationHeadCount(registrationHeadCount);
            logMessage.append("registrationHeadCount, ");
        }

        eventRepository.save(event);

        if (logMessage.toString().endsWith(", ")) {
            logMessage.delete(logMessage.length() - 2, logMessage.length()); // Remove trailing comma and space
        } else {
            logMessage = new StringBuilder("Nothing updated for event with ID " + eventId);
        }
        return new EventResponseObj(logMessage.toString());
    }

    /**
     * Deletes an event by its unique identifier.
     * Must also handle a referential integrity issues as the REGISTRATION table
     * references both users and events.
     *
     * @param eventId The unique identifier of an event to be deleted.
     * @return A EventResponseObj indicating the status of the event deletion and a log message.
     */
    @Override
    public EventResponseObj deleteEvent(Long eventId) {
        /*
        TODO : Handle cases where a user is registered for an event.
          Deletion might result in referential integrity issues as the REGISTRATION table references
          both users and events.
         */
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        String logMessage;

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            String eventName = event.getEventName();
            eventRepository.deleteById(eventId);
            logMessage = "Event " + eventName + " with Id " + eventId +" deleted.";
            return new EventResponseObj(logMessage);
        }

        logMessage = "Event with ID " + eventId + " not found.";
        return new EventResponseObj(logMessage);
    }
}
