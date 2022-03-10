package com.pepole.tesusaku.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserComponent {
	private final MessageSource messageSource;

    /** ロールのMapを生成する */
    public Map<String, String> getRoleMap(Locale locale) {
        Map<String, String> roleMap = new LinkedHashMap<>();
        String admin = messageSource.getMessage("admin", null, locale);
        String tester = messageSource.getMessage("tester", null, locale);
        roleMap.put(admin, "ROLE_ADMIN");
        roleMap.put(tester, "ROLE_TESTER");
        return Collections.unmodifiableMap(roleMap);
    }
}
