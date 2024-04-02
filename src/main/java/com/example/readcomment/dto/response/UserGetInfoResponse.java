package com.example.readcomment.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserGetInfoResponse {
    @JsonProperty("ck")
    private String cookies;
    @JsonProperty("fbdtsg")
    private String fb_dtsg;
    @JsonProperty("pId")
    private String postId;
}
