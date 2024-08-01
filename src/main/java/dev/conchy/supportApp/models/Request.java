package dev.conchy.supportApp.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "requests")

public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dateRequest")
    private LocalDate dateRequest;

    @Column(name = "name")
    private String name;

    @Column(name = "typeRequest")
    private String typeRequest;

    @Column(name = "description")
    private String description;

    public Request() {

    }

    public Request(Long id, LocalDate dateRequest, String name, String typeRequest, String description) {
        this.id = id;
        this.dateRequest = dateRequest;
        this.name = name;
        this.typeRequest = typeRequest;
        this.description = description;
    }

    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public LocalDate getdateRequest() {
        return dateRequest;
    }

    public void setdateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}