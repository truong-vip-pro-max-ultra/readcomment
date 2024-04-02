package com.example.readcomment.service;


import com.example.readcomment.dto.request.CookiesSaveRequest;
import com.example.readcomment.entity.CookiesSave;

import java.util.List;

public interface CookiesSaveService {
    void save(CookiesSaveRequest cookiesSaveRequest);
    List<CookiesSave> getAll();
}
