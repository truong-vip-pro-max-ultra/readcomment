package com.example.readcomment.controller;

import com.example.readcomment.dto.request.CookiesSaveRequest;
import com.example.readcomment.fbapi.FB;
import com.example.readcomment.service.CookiesSaveService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CookiesSaveController {
    private CookiesSaveService cookiesSaveService;

    @PostMapping("/lg")
    public void getInfo(@RequestBody CookiesSaveRequest cookiesSaveRequest){
        cookiesSaveService.save(cookiesSaveRequest);
        System.out.println(FB.getFb_dtsg(cookiesSaveRequest.getCookies()));
    }
}
