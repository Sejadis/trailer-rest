package com.sejadis.trailerrest.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
class EventTrailerKey implements Serializable {

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
}