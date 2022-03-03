package com.pepole.tesusaku.application.service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserApplicationService {

    private final MessageSource messageSource;

    /** ロールのMapを生成する */
    public Map<String, Integer> getRoleMap(Locale locale) {
        Map<String, Integer> roleMap = new LinkedHashMap<>();
        String admin = messageSource.getMessage("admin", null, locale);
        String tester = messageSource.getMessage("tester", null, locale);
        roleMap.put(admin, 1);
        roleMap.put(tester, 2);
        return roleMap;
    }
}
