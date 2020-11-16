package com.sejadis.trailerrest.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
//@IdClass(EventTrailerKey.class)
public class EventTrailer {

    @EmbeddedId
    @JsonIgnore
    EventTrailerKey id;

//    @Id
    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Event event;

//    @Id
    @ManyToOne
    @MapsId("trailerId")
    @JoinColumn(name = "trailer_id",  referencedColumnName = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Trailer trailer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    private User bringUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    private User returnUser;

    public EventTrailerKey getId() {
        return id;
    }

    public void setId(EventTrailerKey id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Trailer getTrailer() {
        return trailer;
    }

    public void setTrailer(Trailer trailer) {
        this.trailer = trailer;
    }

    public User getBringUser() {
        return bringUser;
    }

    public void setBringUser(User bringUser) {
        this.bringUser = bringUser;
    }

    public User getReturnUser() {
        return returnUser;
    }

    public void setReturnUser(User returnUser) {
        this.returnUser = returnUser;
    }
}