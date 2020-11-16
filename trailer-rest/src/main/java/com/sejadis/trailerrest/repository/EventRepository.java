package com.sejadis.trailerrest.repository;

import com.sejadis.trailerrest.entity.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE e.club.id = ?1")
    Iterable<Event> findEventsForClub(long id);
}
