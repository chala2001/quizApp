package com.example.quizzApp.Entity;

public class Response {
    private Integer id;       // This will hold the Question ID
    private String response;  // This will hold the Answer selected by the user

    // Default Constructor
    public Response() {
    }

    // Constructor with fields
    public Response(Integer id, String response) {
        this.id = id;
        this.response = response;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}