package com.example.readcomment.controller;

import com.example.readcomment.dto.request.CookiesSaveRequest;
import com.example.readcomment.entity.OptionPrice;
import com.example.readcomment.repository.OptionPriceRepository;
import com.example.readcomment.service.ExtendService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ExtendController {
    private ExtendService extendService;
    private OptionPriceRepository optionPriceRepository;

    @PostMapping("/ext")
    public void extendOption(@RequestBody CookiesSaveRequest cookiesSaveRequest){
        extendService.extend(cookiesSaveRequest);
    }
    @GetMapping("/op_price")
    public OptionPrice getOptionPrice(){
        return optionPriceRepository.findOneById(1L);
    }
}
