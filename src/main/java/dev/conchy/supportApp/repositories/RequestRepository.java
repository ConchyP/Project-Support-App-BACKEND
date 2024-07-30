package dev.conchy.supportApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.conchy.supportApp.models.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    
}
