package com.aishpam.yummyresto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


public record CustomerDeleteRequest (
    @NotNull(message = "Email should be present")
    @NotEmpty(message = "Email should be present")
    @NotBlank(message = "Email should be present")
    @JsonProperty("email")
    String email
){
    public String getEmail() {
        return null;
    }
}