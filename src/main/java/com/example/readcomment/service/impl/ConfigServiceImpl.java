package com.example.readcomment.service.impl;

import com.example.readcomment.entity.Config;
import com.example.readcomment.repository.ConfigRepository;
import com.example.readcomment.service.ConfigService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfigServiceImpl implements ConfigService {
    private ConfigRepository configRepository;
    @Override
    public Config getConfig() {
        return configRepository.findOneById(1L);
    }
}
