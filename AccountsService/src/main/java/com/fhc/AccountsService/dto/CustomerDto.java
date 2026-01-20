package com.fhc.AccountsService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
        name = "customer",
        description = "schema for customers"
)
public record CustomerDto(@Schema(name = "cucustomer", description = "name of the customer", example = "teste example")
                          @NotEmpty(message = "Field Name cannot be empty.")
                          @Size(min = 5, max = 30, message = "Field Name must have 5 - 30 length.")
                          String name,
                          @NotEmpty(message = "Field Email cannot be empty.")
                          @Email
                          String email,
                          @Schema(name = "Cel Phone", description = "Mobile name of the customer")
                          @NotEmpty(message = "Field Email cannot be empty.")
                          @Pattern(regexp = "(^$|[0-9]{10})", message = "Field mobileNumber must have 10 numeric digits.")
                          String mobileNumber,
                          @JsonProperty("account")
                          AccountsDto accountsDto) {
}
