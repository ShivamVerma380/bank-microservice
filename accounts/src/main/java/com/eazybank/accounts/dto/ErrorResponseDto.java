package com.eazybank.accounts.dto;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
    name = "ErrorResponse",
    description = "Schema to hold error response information"
)
@Data @AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
        description = "API path invoked by client"
    )
    private String apiPath;

    @Schema(
        description = "Error response representing the error happened"
    )
    private HttpStatus errorCode;

    @Schema(
        description = "Error message representing the error happened"
    )
    private String errorMsg;

    @Schema(
        description = "Time representing when the error happened"
    )
    private String errorTime;

}
