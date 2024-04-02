package com.example.readcomment.controller;

import com.example.readcomment.dto.response.CommentInfoResponse;
import com.example.readcomment.dto.request.UserInfoRequest;
import com.example.readcomment.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @PostMapping("/n_cmt")
    public CommentInfoResponse getNewComment(@RequestBody UserInfoRequest userInfoRequest){
        CommentInfoResponse commentInfoResponse = commentService.getNewComment(userInfoRequest);
        return commentInfoResponse;
    }
}
