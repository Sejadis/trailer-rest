package com.sejadis.trailerrest.repository;

import com.sejadis.trailerrest.entity.Club;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends CrudRepository<Club, Long> {
}