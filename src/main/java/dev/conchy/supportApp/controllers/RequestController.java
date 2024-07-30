package dev.conchy.supportApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import dev.conchy.supportApp.models.Request;
import dev.conchy.supportApp.services.RequestService;

@RestController
@RequestMapping("api/v1/request")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")

public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;

    }

    @GetMapping("/all")
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/{idRequest}")
    public ResponseEntity<Request> getrequestById(@PathVariable Long idRequest) {
        Request request = requestService.getRequestById(idRequest);
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

    @PutMapping("/{idRequest}")
    public ResponseEntity<Request> updateRequest(@RequestBody Request request, @PathVariable Long idRequest) {
        request.setIdRequest(idRequest);
        Request updatedrequest = requestService.updateRequest(request);
        return ResponseEntity.ok(updatedrequest);
    }

    @DeleteMapping("/{idRequest}")
    public ResponseEntity<Void> deleteRequestById(@PathVariable Long idRequest) {
        requestService.deleteRequestById(idRequest);

        return ResponseEntity.noContent().build();
    }
}