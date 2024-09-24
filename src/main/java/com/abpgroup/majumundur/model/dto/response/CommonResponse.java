package com.abpgroup.majumundur.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponse <T>{
    @JsonProperty("data")
    private T data;
    @JsonProperty("message")
    private String message;
    @JsonProperty("status")
    private int status;
}
