package org.example.fighterscardservice.repository;

import java.util.UUID;
import org.example.fighterscardservice.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, UUID> {
}
