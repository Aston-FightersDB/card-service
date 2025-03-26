package org.example.fighterscardservice.repository;

import java.util.UUID;
import org.example.fighterscardservice.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
}
