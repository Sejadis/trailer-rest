package com.sejadis.trailerrest.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String email;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;
    @ManyToMany(mappedBy = "owners")
    private Set<Club> administratingClubs;
    @ManyToMany(mappedBy = "members")
    private Set<Club> memberships;
    @ManyToMany(mappedBy = "users")
    private Set<Event> events;
    @OneToMany(mappedBy = "bringUser")
    private Set<EventTrailer> bringTrailers;
    @OneToMany(mappedBy = "returnUser")
    private Set<EventTrailer> returnTrailers;

    public User() {
    }
    public User(String username, String password) {
        this(username, password, "");
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.create_time = new Date();
    }

    public Set<Club> getAdministratingClubs() {
        return administratingClubs;
    }

    public void setAdministratingClubs(Set<Club> administratingClubs) {
        this.administratingClubs = administratingClubs;
    }

    public Set<Club> getMemberships() {
        return memberships;
    }

    public void setMemberships(Set<Club> memberships) {
        this.memberships = memberships;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
