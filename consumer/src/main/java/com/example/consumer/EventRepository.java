package com.example.consumer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, String> {
    Optional<Event> findById(String id);
}
