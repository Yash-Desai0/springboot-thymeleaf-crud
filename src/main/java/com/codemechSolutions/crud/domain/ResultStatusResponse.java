package com.codemechSolutions.crud.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "resultStatus"
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResultStatusResponse {
    @JsonProperty("resultStatus")
    private ResultStatus resultStatus;

}
