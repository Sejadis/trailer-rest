package com.sejadis.trailerrest.entity;


import javax.persistence.*;

@Entity
public class EventTrailer {

    @EmbeddedId
    EventTrailerKey id;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    Event event;

    @ManyToOne
    @MapsId("trailerId")
    @JoinColumn(name = "trailer_id")
    Trailer trailer;

    @ManyToOne(fetch = FetchType.LAZY)
    private User bringUser;
    @ManyToOne(fetch = FetchType.LAZY)
    private User returnUser;

}