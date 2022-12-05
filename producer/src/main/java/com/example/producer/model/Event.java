package com.example.producer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
public class Event {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Version
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    private Instant version;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content="";

    public Event(String id) {
        this.id = id;
    }
}
