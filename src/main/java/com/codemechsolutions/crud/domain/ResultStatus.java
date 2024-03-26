package com.codemechsolutions.crud.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "errorMessage",
        "errorCode"
})
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResultStatus {
    @JsonProperty("status")
    private String status;
    @JsonProperty("errorMessage")
    private String errorMessage;
    @JsonProperty("errorCode")
    private String errorCode;
}
