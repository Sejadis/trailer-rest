package com.sejadis.trailerrest.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EventTrailerKey implements Serializable {

    @Column(name = "event_id")
    Long eventId;

    @Column(name = "trailer_id")
    Long trailerId;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(Long trailerId) {
        this.trailerId = trailerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventTrailerKey that = (EventTrailerKey) o;
        return eventId.equals(that.eventId) &&
                trailerId.equals(that.trailerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, trailerId);
    }


//    @Column(name = "event_id")
//    Long event;
//
//    @Column(name = "trailer_id")
//    Long trailer;
//
//    public Long getEvent() {
//        return event;
//    }
//
//    public void setEvent(Long event) {
//        this.event = event;
//    }
//
//    public Long getTrailer() {
//        return trailer;
//    }
//
//    public void setTrailer(Long trailer) {
//        this.trailer = trailer;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        EventTrailerKey that = (EventTrailerKey) o;
//        return event.equals(that.event) &&
//                trailer.equals(that.trailer);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(event, trailer);
//    }
}