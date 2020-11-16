package com.sejadis.trailerrest.repository;

import com.sejadis.trailerrest.entity.EventTrailer;
import com.sejadis.trailerrest.entity.EventTrailerKey;
import org.springframework.data.repository.CrudRepository;

public interface EventTrailerRepository extends CrudRepository<EventTrailer, EventTrailerKey> {
}
