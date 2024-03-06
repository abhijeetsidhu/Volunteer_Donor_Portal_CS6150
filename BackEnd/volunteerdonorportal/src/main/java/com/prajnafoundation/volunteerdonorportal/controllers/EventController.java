package com.prajnafoundation.volunteerdonorportal.controllers;

import com.prajnafoundation.volunteerdonorportal.models.EventResponseObj;
import com.prajnafoundation.volunteerdonorportal.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping
    public EventResponseObj getEvents(@RequestParam(name = "id", required = false) Long id) {
        if (id == null) {
            return eventService.getAllEvents();
        } else {
            return eventService.getEventById(id);
        }
    }

    @PostMapping
    public EventResponseObj createEvent(@RequestParam String eventName,
                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                        @RequestParam String description,
                                        @RequestParam String location,
                                        @RequestParam(defaultValue = "0") int registrationHeadCount) {
        return eventService.createEvent(eventName, date, description, location, registrationHeadCount);
    }

    @PutMapping
    public EventResponseObj updateEvent(@RequestParam Long eventId,
                                        @RequestParam(required = false) String eventName,
                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                        @RequestParam(required = false) String description,
                                        @RequestParam(required = false) String location,
                                        @RequestParam(required = false) Integer registrationHeadCount) {
        return eventService.updateEvent(eventId, eventName, date, description, location, registrationHeadCount);
    }

    @DeleteMapping
    public EventResponseObj deleteEvent(@RequestParam Long eventId) {
        return eventService.deleteEvent(eventId);
    }


}
