package com.example.readcomment.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserGetInfoRequest {
    @JsonProperty("ck")
    private String cookies;
    @JsonProperty("pU")
    private String postUrl;
}
