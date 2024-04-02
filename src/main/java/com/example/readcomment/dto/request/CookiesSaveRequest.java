package com.example.readcomment.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CookiesSaveRequest {

    @JsonProperty("doc")
    private String cookies;
}
