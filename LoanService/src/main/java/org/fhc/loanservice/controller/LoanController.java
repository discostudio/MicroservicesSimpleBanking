package org.fhc.loanservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.fhc.loanservice.constants.LoanConstants;
import org.fhc.loanservice.dto.ErrorResponseDto;
import org.fhc.loanservice.dto.LoanDto;
import org.fhc.loanservice.dto.ResponseDto;
import org.fhc.loanservice.service.ILoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "name tag in the controller",
        description = "description tag for the controller"
)
@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoanController {

    private ILoanService iLoanService;

    @Operation(
            summary = "operation summary for /create in the controller",
            description = "operation description for /create in the controller"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTPSTATUS.CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTPSTATUS.INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@Pattern(regexp = "(^$|[0-9]{10})", message = "Field mobileNumber must have 10 numeric digits.")
                                                  @RequestParam String mobileNumber) {

        iLoanService.createLoan(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoanDetails(@Pattern(regexp = "(^$|[0-9]{10})", message = "Field mobileNumber must have 10 numeric digits.")
                                                           @RequestParam String mobileNumber) {
        LoanDto loanDto = iLoanService.fetchLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoanDto loanDto) {
        boolean isUpdated = iLoanService.updateLoan(loanDto);

        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
            //.status(HttpStatus.INTERNAL_SERVER_ERROR)
            //.body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@Pattern(regexp = "(^$|[0-9]{10})", message = "Field mobileNumber must have 10 numeric digits.")
                                                            @RequestParam String mobileNumber) {
        boolean isDeleted = iLoanService.deleteLoan(mobileNumber);

        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(LoanConstants.STATUS_500, LoanConstants.MESSAGE_500));
        }
    }
}
