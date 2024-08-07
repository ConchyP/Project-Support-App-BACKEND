package dev.conchy.supportApp.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class RequestTest {

    @Test
    void testNoArgsConstructor() {
        Request request = new Request(1L, "Test1");
        assertThat(request.getid()).isNull();
        assertThat(request.getdateRequest()).isNull();
        assertThat(request.getName()).isNull();
        assertThat(request.getTypeRequest()).isNull();
        assertThat(request.getDescription()).isNull();
    }

    @Test
    void testAllArgsConstructor() {
        LocalDate date = LocalDate.of(2023, 8, 3);
        Request request = new Request(1L, date, "Test Name", "Test Type", "Test Description");

        assertThat(request.getid()).isEqualTo(1L);
        assertThat(request.getdateRequest()).isEqualTo(date);
        assertThat(request.getName()).isEqualTo("Test Name");
        assertThat(request.getTypeRequest()).isEqualTo("Test Type");
        assertThat(request.getDescription()).isEqualTo("Test Description");
    }

    @Test
    void testSettersAndGetters() {
        Request request = new Request(1L, "Test1");

        request.setid(1L);
        request.setdateRequest(LocalDate.of(2023, 8, 3));
        request.setName("Test Name");
        request.setTypeRequest("Test Type");
        request.setDescription("Test Description");

        assertThat(request.getid()).isEqualTo(1L);
        assertThat(request.getdateRequest()).isEqualTo(LocalDate.of(2023, 8, 3));
        assertThat(request.getName()).isEqualTo("Test Name");
        assertThat(request.getTypeRequest()).isEqualTo("Test Type");
        assertThat(request.getDescription()).isEqualTo("Test Description");
    }
}

