package com.example.readcomment.service;

import com.example.readcomment.dto.response.CommentInfoResponse;
import com.example.readcomment.dto.request.UserInfoRequest;

public interface CommentService {
    CommentInfoResponse getNewComment(UserInfoRequest userInfoRequest);
}
