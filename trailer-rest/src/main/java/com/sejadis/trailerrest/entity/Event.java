package com.sejadis.trailerrest.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sejadis.trailerrest.model.View;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @JsonView(View.Internal.class)
    private long id;

    @Column(nullable = false)
//    @JsonView(View.Public.class)
    private String name;
//    @JsonView(View.Public.class)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIdentityReference(alwaysAsId = true)
//    @JsonView(View.Public.class)
    private Club club;

    @ManyToMany
    @JoinTable(name = "event_participant",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users;

    public Event() {
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
