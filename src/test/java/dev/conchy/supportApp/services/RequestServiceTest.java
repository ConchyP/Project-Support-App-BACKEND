package dev.conchy.supportApp.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.conchy.supportApp.models.Request;
import dev.conchy.supportApp.repositories.RequestRepository;

@ExtendWith(MockitoExtension.class)
public class RequestServiceTest {

    @InjectMocks
    private RequestService service;

    @Mock
    private RequestRepository repository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testGetAllRequests() {
        Request request1 = new Request(1L, "Test1");
        Request request2 = new Request(2L, "Test2");

        List<Request> allRequests = Arrays.asList(request1, request2);
        when(repository.findAll()).thenReturn(allRequests);

        List<Request> result = service.getAllRequests();
        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0).getName(), equalTo(request1.getName()));
        assertThat(result, contains(request1, request2));
    }

    @Test
    void testGetRequestById() {
        Request request = new Request(1L, "Test1");
        when(repository.findById(1L)).thenReturn(Optional.of(request));

        Request result = service.getRequestById(1L);
        assertThat(result).isEqualTo(request);
    }

    @Test
    void testGetRequestByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getRequestById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Request not found");
    }

    @Test
    void testCreateRequest() {
        Request request = new Request(1L, "Test1");

        when(repository.save(Mockito.any(Request.class))).thenReturn(request);
        Request result = service.createRequest(request);

        assertThat(result, is(instanceOf(Request.class)));
        assertThat(result.getName(), is(equalTo(request.getName())));
    }

    

    // @Test
    // void testUpdateRequest() {
    // Request request = new Request(1L, "UpdatedTest");

    // when(repository.existsById(1L)).thenReturn(true);
    // when(repository.save(request)).thenReturn(request);

    // Request result = service.updateRequest(request);
    // assertThat(result).isEqualTo(request);
    // }

    // @Test
    // void testUpdateRequestNotFound() {
    // Request request = new Request(1L, "UpdatedTest");

    // when(repository.existsById(1L)).thenReturn(false);

    // assertThatThrownBy(() -> service.updateRequest(request))
    // .isInstanceOf(RuntimeException.class)
    // .hasMessage("Request not found");
    // }

    @Test
    void testDeleteRequestById() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        service.deleteRequestById(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteRequestByIdNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> service.deleteRequestById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Request not found");
    }
}
