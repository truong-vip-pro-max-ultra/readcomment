package com.example.readcomment.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentGetNewRequest {
    private String doc_id;
    private String fb_dtsg;
    private String variables;
}
