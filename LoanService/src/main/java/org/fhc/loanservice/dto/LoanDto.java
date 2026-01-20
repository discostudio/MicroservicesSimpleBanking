package org.fhc.loanservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record LoanDto(@Schema(name = "Cel Phone", description = "Mobile name of the customer")
                      @NotEmpty(message = "Field Email cannot be empty.")
                      @Pattern(regexp = "(^$|[0-9]{10})", message = "Field mobileNumber must have 10 numeric digits.")
                      String mobileNumber,
                      @Schema(name = "Loan Number", description = "Loan number field")
                      @NotEmpty(message = "Field Loan Number cannot be empty.")
                      @Pattern(regexp = "(^$|[0-9]{12})", message = "Field mobileNumber must have 12 numeric digits.")
                      String loanNumber,
                      @Schema(name = "Loan Type", description = "Loan type field")
                      @NotEmpty(message = "Field Loan Type cannot be empty.")
                      String loanType,
                      @Positive(message = "Field Total Loan must be greater than zero.")
                      int totalLoan,
                      @PositiveOrZero(message = "Field Amount Paid must be zero or more.")
                      int amountPaid,
                      @PositiveOrZero(message = "Field Amount Paid must be zero or more.")
                      int outstandingAmount) {
}
