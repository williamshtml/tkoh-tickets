package com.tkoh.repository;

import com.tkoh.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    // Esto permite que el GET /api/events no traiga 10,000 eventos de golpe
    Page<Event> findAll(Pageable pageable);
}