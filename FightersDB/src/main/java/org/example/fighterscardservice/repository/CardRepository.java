package org.example.fighterscardservice.repository;

import java.util.UUID;
import org.example.fighterscardservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {

}
