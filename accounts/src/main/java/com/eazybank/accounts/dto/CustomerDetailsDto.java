package com.eazybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    name = "CustomerDetails",
    description = "Schema to hold Customer, Account, Cards and Loans Information"
)
public class CustomerDetailsDto {
    
    @NotEmpty(message = "Name cannot be null or empty.")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30.")
    @Schema(
        description = "Name of the customer", example = "Shivam Verma"
    )
    private String name;

    @NotEmpty(message = "Email cannot be empty.")
    @Email(message = "Email address should be a valid value.")
    @Schema(
        description = "Email address of the customer", example = "shivamvermasv380@gmail.com"
    )
    private String email;

    @NotEmpty(message = "Mobile Number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
        description = "Mobile number of the customer", example = "1234846263"
    )
    private String mobileNumber;

    @Schema(
        description = "Account details of the customer"
    )
    private AccountsDto accountsDto;

    @Schema(
        description = "Cards details of the customer"
    )
    private CardsDto cardsDto;

    @Schema(
        description = "Loans details of the customer"
    )
    private LoansDto loansDto;
}
