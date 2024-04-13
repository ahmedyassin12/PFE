package com.example.demo.service;

import com.example.demo.dao.EventDAO;
import com.example.demo.entity.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class EventService {

    @Autowired
    private EventDAO eventDAO;

    public Iterable<Event> getAllEvents() {
        return eventDAO.findAll();
    }

    public Event getEventById(Long id) {
        Optional<Event> optional = eventDAO.findById(id);
        Event event;
        if (optional.isPresent()) {
            event = optional.get();
        } else {
            throw new RuntimeException("Event not found for id :: " + id);
        }
        return event;
    }

    public Event getEventByName(String name) {
        Optional<Event> optional = eventDAO.findBynom(name);
        Event event;
        if (optional.isPresent()) {
            event = optional.get();
        } else {
            throw new RuntimeException("Event not found for name :: " + name);
        }
        return event;
    }

    public Event createNewEvent(Event event) {
        eventDAO.save(event);
        log.info("Event {} is saved", event.getEvent_id());
        return event;
    }


    public void rem_event(Long id) {
        eventDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        eventDAO.deleteById(id);
    }

    public Event update_event(Event event) {
        if (event == null || event.getEvent_id() == null) {
            throw new IllegalArgumentException("Invalid event ID for update");
        }
        return eventDAO.save(event);
    }
}