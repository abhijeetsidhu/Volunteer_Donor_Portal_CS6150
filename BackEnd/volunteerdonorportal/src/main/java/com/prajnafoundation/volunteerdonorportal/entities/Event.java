package com.prajnafoundation.volunteerdonorportal.entities;

import jakarta.persistence.*;
import java.util.Date;
@Entity
@Table(name = "event_table")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "date")
    private Date date;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "registration_head_count")
    private Integer registrationHeadCount;

    // Getters and setters

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getRegistrationHeadCount() {
        return registrationHeadCount;
    }

    public void setRegistrationHeadCount(Integer registrationHeadCount) {
        this.registrationHeadCount = registrationHeadCount;
    }

}