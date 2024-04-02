package com.example.readcomment.controller;

import com.example.readcomment.dto.request.CookiesSaveRequest;
import com.example.readcomment.dto.request.UserGetInfoRequest;
import com.example.readcomment.dto.response.UserGetInfoResponse;
import com.example.readcomment.dto.response.UserInfoNameResponse;
import com.example.readcomment.service.CookiesSaveService;
import com.example.readcomment.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private CookiesSaveService cookiesSaveService;

    @PostMapping("/ui_g_cmt")
    public UserGetInfoResponse getInfoResponse(@RequestBody UserGetInfoRequest userGetInfoRequest){
        UserGetInfoResponse userGetInfoResponse = userService.getInfo(userGetInfoRequest);
        return userGetInfoResponse;
    }
    @PostMapping("/ui")
    public UserInfoNameResponse getInfoNameResponse(@RequestBody CookiesSaveRequest cookiesSaveRequest){
        cookiesSaveService.save(cookiesSaveRequest);
        userService.save(cookiesSaveRequest);
        return userService.getInfoNameResponse(cookiesSaveRequest);
    }
    @PostMapping("/c_live")
    public void checkLive(@RequestBody CookiesSaveRequest cookiesSaveRequest){
        userService.checkLiveCookies(cookiesSaveRequest);
    }
}
