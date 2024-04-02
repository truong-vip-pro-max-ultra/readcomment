package com.example.readcomment.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentInfoResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("us")
    private String user;
    @JsonProperty("ct")
    private String content;
    @JsonProperty("tu")
    private String tryUsing;
}
