package com.example.readcomment.dto.request.resp_cmt;

import lombok.Data;

import java.util.List;

@Data
public class DisplayComments {
    private List<Edges> edges;
}
