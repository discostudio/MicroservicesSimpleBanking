package com.fhc.AccountsService.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record AccountsDto(@NotEmpty(message = "Field accountName cannot be empty.")
                          @Pattern(regexp = "(^$|[0-9]{10})", message = "Field accountNumber must have 10 numeric digits.")
                          Long accountNumber,
                          @NotEmpty(message = "Field accountType cannot be empty.")
                          String accountType,
                          @NotEmpty(message = "Field branchAddress cannot be empty.")
                          String branchAddress) {
}
