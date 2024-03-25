package com.eazybank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
    name = "Loans",
    description = "Schema to hold loan information"
)
@Data
public class LoansDto {
    
    @NotEmpty(message = "Mobile Number cannot be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    @Schema(
        description = "Mobile number of customer", example = "4321560984"
    )
    private String mobileNumber;

    @NotEmpty(message = "Loan number cannot be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Loan number must be 12 digits")
    @Schema(
        description = "Loan number of customer", example = "542378951285"
    )
    private String loanNumber;

    @NotEmpty(message = "Type of the loan cannot be empty")
    @Schema(
        description = "Type of the loan", example = "Home Loan"
    )
    private String loanType;

    @Positive(message = "Total loan amount should be greater than zero")
    @Schema(
        description = "Total loan amount", example = "100000"
    )
    private int totalLoan;

    @PositiveOrZero(message = "Total amount paid should be equal or greater than zero")
    @Schema(
        description = "Total loan amount paid", example = "1000"
    )
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    @Schema(
        description = "Total outstanding amount against a loan", example = "99000"
    )
    private int outstandingAmount;

}
