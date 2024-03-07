package com.prajnafoundation.volunteerdonorportal.models;

import com.prajnafoundation.volunteerdonorportal.entities.Event;

import java.util.Date;
import java.util.List;

public class EventResponseObj extends LogMessage {

    private List<EventObj> events = null;

    public EventResponseObj(List<Event> events, String logMessage) {
        super(logMessage);
        this.events = events.stream().map(EventObj::new).toList();
    }

    public EventResponseObj(String logMessage) {
        super(logMessage);
    }

    public List<EventObj> getEvents() {
        return events;
    }

    public void setEvents(List<EventObj> events) {
        this.events = events;
    }

    private static class EventObj {
        Long eventId;
        String eventName;
        Date date;
        String description;
        String location;
        int registrationHeadCount;

        public EventObj(Event event) {
            this.eventId = event.getEventId();
            this.eventName = event.getEventName();
            this.date = event.getDate();
            this.description = event.getDescription();
            this.location = event.getLocation();
            this.registrationHeadCount = event.getRegistrationHeadCount();
        }

        public Long getEventId() {
            return eventId;
        }

        public String getEventName() {
            return eventName;
        }

        public Date getDate() {
            return date;
        }

        public String getDescription() {
            return description;
        }

        public String getLocation() {
            return location;
        }

        public int getRegistrationHeadCount() {
            return registrationHeadCount;
        }
    }
}
