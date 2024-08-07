package dev.conchy.supportApp.controllers;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.conchy.supportApp.models.Request;
import dev.conchy.supportApp.services.RequestService;

@AutoConfigureMockMvc(addFilters = false) 

@WebMvcTest(controllers = RequestController.class)
public class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestService requestService;

    @Autowired
    private ObjectMapper objectMapper;
    

@AutoConfigureMockMvc(addFilters = false)

    @Test
    @DisplayName("Should return a list of requests")
    void testGetAllRequests() throws Exception {
        List<Request> requests = new ArrayList<>();
        Request request1 = new Request();
        request1.setid(1L);
        request1.setTypeRequest("Request 1");
        Request request2 = new Request();
        request2.setid(2L);
        request2.setTypeRequest("Request 2");
        requests.add(request1);
        requests.add(request2);

        when(requestService.getAllRequests()).thenReturn(requests);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/requests/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(200));
        assertThat(response.getContentAsString(), containsString(request1.getTypeRequest()));
        assertThat(response.getContentAsString(), containsString(request2.getTypeRequest()));
        assertThat(response.getContentAsString(), equalTo(objectMapper.writeValueAsString(requests)));
    }

    @Test
    void testGetRequestById() throws Exception {
        Request request = new Request();
        request.setid(1L);
        request.setTypeRequest("Test Request");
        when(requestService.getRequestById(1L)).thenReturn(request);

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/requests/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(200));
        assertThat(response.getContentAsString(), containsString(request.getTypeRequest()));
        assertThat(response.getContentAsString(), equalTo(objectMapper.writeValueAsString(request)));
    }

    @Test
    void testCreateRequest() throws Exception {
        Request newRequest = new Request();
        newRequest.setid(1L);
        newRequest.setTypeRequest("New Request");
        when(requestService.createRequest(any(Request.class))).thenReturn(newRequest);

        String jsonRequest = objectMapper.writeValueAsString(newRequest);

        MockHttpServletResponse response = mockMvc.perform(post("/api/v1/requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(201));
        assertThat(response.getContentAsString(), equalTo(jsonRequest));
    }

    @Test
    void testUpdateRequest() throws Exception {
        Request updatedRequest = new Request();
        updatedRequest.setid(1L);
        updatedRequest.setTypeRequest("Updated Request");
        when(requestService.updateRequest(any(Request.class))).thenReturn(updatedRequest);

        String jsonRequest = objectMapper.writeValueAsString(updatedRequest);

        MockHttpServletResponse response = mockMvc.perform(put("/api/v1/requests/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(200));
        assertThat(response.getContentAsString(), equalTo(jsonRequest));
    }

    @Test
    void testDeleteRequest() throws Exception {
        doNothing().when(requestService).deleteRequestById(1L);

        MockHttpServletResponse response = mockMvc.perform(delete("/api/v1/requests/1"))
                .andExpect(status().isNoContent())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(204));
    }
}
