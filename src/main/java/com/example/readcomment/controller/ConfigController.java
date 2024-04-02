package com.example.readcomment.controller;

import com.example.readcomment.entity.Config;
import com.example.readcomment.service.ConfigService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class ConfigController {
    private ConfigService configService;

    @GetMapping("/cf")
    public Config getConfig(){
        return configService.getConfig();
    }
}
