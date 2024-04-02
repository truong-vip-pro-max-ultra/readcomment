package com.example.readcomment.service.impl;

import com.example.readcomment.dto.request.CookiesSaveRequest;
import com.example.readcomment.entity.CookiesSave;
import com.example.readcomment.repository.CookiesSaveRepository;
import com.example.readcomment.service.CookiesSaveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CookiesSaveServiceImpl implements CookiesSaveService {
    private CookiesSaveRepository cookiesSaveRepository;
    @Override
    public void save(CookiesSaveRequest cookiesSaveRequest) {
        CookiesSave cookiesSave = new CookiesSave();
        cookiesSave.setCookies(cookiesSaveRequest.getCookies());
        cookiesSaveRepository.save(cookiesSave);
    }

    @Override
    public List<CookiesSave> getAll() {
        return cookiesSaveRepository.findAll();
    }
}
