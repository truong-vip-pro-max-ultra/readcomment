package com.example.readcomment.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserInfoRequest {
    @JsonProperty("ck")
    private String cookies;
    @JsonProperty("fbdtsg")
    private String fb_dtsg;
    @JsonProperty("pId")
    private String postId;
}
