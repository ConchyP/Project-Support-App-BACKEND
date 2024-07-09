package dev.conchy.supportApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.conchy.supportApp.models.SupportApp;

public interface SupportAppRepository extends JpaRepository<SupportApp, Long> {
    
}
