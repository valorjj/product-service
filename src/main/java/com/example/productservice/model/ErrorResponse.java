package com.example.productservice.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.*;

@Builder
@JsonNaming(SnakeCaseStrategy.class)
public record ErrorResponse(
    String errorMessage,
    String errorCode

) {
}
