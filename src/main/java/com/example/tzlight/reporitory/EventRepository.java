package com.example.tzlight.reporitory;

import com.example.tzlight.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}