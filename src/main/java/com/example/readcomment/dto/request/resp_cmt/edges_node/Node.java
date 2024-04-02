package com.example.readcomment.dto.request.resp_cmt.edges_node;

import lombok.Data;

@Data
public class Node {
    private String id;
    private Author author;
    private BodyRenderer body_renderer;
}
