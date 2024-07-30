package dev.conchy.supportApp.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.conchy.supportApp.models.Request;
import dev.conchy.supportApp.services.RequestService;

@WebMvcTest(RequestController.class)
public class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestService requestService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAllRequests() throws Exception {
        Request request1 = new Request(1L, LocalDate.of(2023, 1, 1), "Request 1", "Connection issues", "Description 1");
        Request request2 = new Request(2L, LocalDate.of(2023, 1, 2), "Request 2", "Password error", "Description 2");
        List<Request> requests = Arrays.asList(request1, request2);

        when(requestService.getAllRequests()).thenReturn(requests);

        mockMvc.perform(get("/api/v1/request/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idRequest").value(1L))
                .andExpect(jsonPath("$[0].name").value("Request 1"))
                .andExpect(jsonPath("$[1].idRequest").value(2L))
                .andExpect(jsonPath("$[1].name").value("Request 2"));
    }

    @Test
    public void testGetRequestById() throws Exception {
        Request request1 = new Request(1L, LocalDate.of(2023, 1, 1), "Request 1", "Connection issues", "Description 1");
        Request request2 = new Request(2L, LocalDate.of(2023, 1, 2), "Request 2", "Password error", "Description 2");

        when(requestService.getRequestById(1L)).thenReturn(request1);
        when(requestService.getRequestById(2L)).thenReturn(request2);

        mockMvc.perform(get("/api/v1/request/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idRequest").value(1L))
                .andExpect(jsonPath("$.name").value("Request 1"));
    }

    @Test
    public void testGetRequestByIdNotFound() throws Exception {
        when(requestService.getRequestById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/request/1"))
                .andExpect(status().isNotFound());
    }

    // @Test
    // public void testCreateRequest() throws Exception {
    //     Request request = new Request(1L, LocalDate.of(2023, 1, 1), "Request 1", "Connection issues", "Description 1");
    //     Request createdRequest = new Request(1L, LocalDate.of(2023, 1, 1), "Request 1", "Connection issues", "Description 1");

    //     when(requestService.createRequest(any(Request.class))).thenReturn(createdRequest);

    //     mockMvc.perform(post("/api/v1/request")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content(objectMapper.writeValueAsString(request)))
    //             .andExpect(status().isCreated())
    //             .andExpect(jsonPath("$.idRequest").value(1L))
    //             .andExpect(jsonPath("$.name").value("Request 1"));
    // }

    @Test
    public void testUpdateRequest() throws Exception {
        Request request = new Request(1L, LocalDate.of(2023, 01, 01), "Request 1", "Connection issues", "Description 1");
        Request updatedRequest = new Request(1L, LocalDate.of(2023, 01, 01), "Request 1", "Connection issues", "Description 1");

        when(requestService.updateRequest(any(Request.class))).thenReturn(updatedRequest);

        mockMvc.perform(put("/api/v1/request/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idRequest").value(1L))
                .andExpect(jsonPath("$.name").value("Request 1 Updated"));
    }

    @Test
    public void testDeleteRequestById() throws Exception {
        mockMvc.perform(delete("/api/v1/request/1"))
                .andExpect(status().isNoContent());
    }
}

