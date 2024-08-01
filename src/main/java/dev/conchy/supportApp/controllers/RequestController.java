package dev.conchy.supportApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import dev.conchy.supportApp.models.Request;
import dev.conchy.supportApp.services.RequestService;

@RestController
@RequestMapping(path = "${api-endpoint}/requests")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")

public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;

    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Request> getrequestById(@PathVariable Long id) {
        Request request = requestService.getRequestById(id);
        if (request != null) {
            return ResponseEntity.ok(request);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        Request createdRequest = requestService.createRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Request> updateRequest(@RequestBody Request request, @PathVariable Long id) {
        request.setid(id);
        Request updatedrequest = requestService.updateRequest(request);
        return ResponseEntity.ok(updatedrequest);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequestById(id);

        return ResponseEntity.noContent().build();
    }
}
