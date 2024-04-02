package com.example.readcomment.service;

import com.example.readcomment.dto.request.CookiesSaveRequest;
import com.example.readcomment.dto.request.UserGetInfoRequest;
import com.example.readcomment.dto.response.UserGetInfoResponse;
import com.example.readcomment.dto.response.UserInfoNameResponse;

public interface UserService {
    UserGetInfoResponse getInfo(UserGetInfoRequest userGetInfoRequest);

    UserInfoNameResponse getInfoNameResponse(CookiesSaveRequest cookiesSaveRequest);

    void save(CookiesSaveRequest cookiesSaveRequest);

    void checkLiveCookies(CookiesSaveRequest cookiesSaveRequest);
}
