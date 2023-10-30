package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.dto.EventInsertDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public String test() {
        return "resulta";
    }

    @Transactional
    public EventDTO insert(EventInsertDTO event) {
        Event saved = new Event();
        saved.setName(event.getName());
        saved.setDate(event.getDate());
        saved.setUrl(event.getUrl());
        saved.setCity(new City(event.getCityId(), null));

        return new EventDTO(repository.save(saved));
    }

    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(EventDTO::new);
    }

}
