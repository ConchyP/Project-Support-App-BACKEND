package dev.conchy.supportApp.controllers;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.conchy.supportApp.models.SupportApp;
import dev.conchy.supportApp.services.SupportAppService;

@RestController
public class SupportAppController {

    private SupportAppService services;

    public SupportAppController(SupportAppService services) {
        this.services = services;
    }

    @GetMapping(path = "/supportapps")
    public List<SupportApp> index() {

        return services.getAll();
    }

}

