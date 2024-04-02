package com.example.readcomment.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoNameResponse {
    @JsonProperty("ck")
    private String cookies;
    @JsonProperty("fn")
    private String fullName;
    @JsonProperty("acbal")
    private String accountBalance;
    @JsonProperty("exp")
    private String expDate;
}
