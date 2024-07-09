package dev.conchy.supportApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.conchy.supportApp.models.SupportApp;
import dev.conchy.supportApp.repositories.SupportAppRepository;

@Service
public class SupportAppService {
    
    SupportAppRepository repository;

    public SupportAppService(SupportAppRepository repository) {
        this.repository = repository;
    }

    public  List<SupportApp> getAll() {
        List<SupportApp> requests = repository.findAll();
        return requests;
    }

}
