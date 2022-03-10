package com.pepole.tesusaku.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TestcaseComponent {
    /** 結果のMapを生成する */
    public Map<Integer, String> getResultMap() {
        Map<Integer, String> resultMap = new LinkedHashMap<>();
		resultMap.put(0, "");
		resultMap.put(1, "OK");
		resultMap.put(2, "NG");
		resultMap.put(3, "skip");
		resultMap.put(4, "pending");
		resultMap.put(5, "N/A");
		return Collections.unmodifiableMap(resultMap);
    }
}
