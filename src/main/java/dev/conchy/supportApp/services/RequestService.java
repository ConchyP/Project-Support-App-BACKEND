package dev.conchy.supportApp.services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.conchy.supportApp.models.Request;
import dev.conchy.supportApp.repositories.RequestRepository;


@Service
public class RequestService {

    @Autowired
    private  RequestRepository requestRepository;

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Request getRequestById(Long idRequest) {
        return requestRepository.findById(idRequest).orElseThrow(() -> new RuntimeException("Request not found"));
    }

    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }

    public Request updateRequest(Request request) {
        if (!requestRepository.existsById(request.getIdRequest())) {
            throw new RuntimeException("Request not found");
        }
        return requestRepository.save(request);
    }

    public void deleteRequestById(Long idRequest) {
        if (!requestRepository.existsById(idRequest)) {
            throw new RuntimeException("Request not found");
        }
        requestRepository.deleteById(idRequest);
    }
}
