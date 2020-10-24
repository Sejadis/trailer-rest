package com.sejadis.trailerrest.repository;

import com.sejadis.trailerrest.entity.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
