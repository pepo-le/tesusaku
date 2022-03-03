package com.pepole.tesusaku.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Type {
	public static final Map<Integer, String> ROLE;
	public static final Map<Integer, String> RESULT;

	static {
		Map<Integer, String> role = new LinkedHashMap<Integer, String>();
		role.put(0, "admin");
		role.put(1, "tester");
		ROLE = Collections.unmodifiableMap(role);

		Map<Integer, String> result = new LinkedHashMap<Integer, String>();
		result.put(0, "");
		result.put(1, "OK");
		result.put(2, "NG");
		result.put(3, "skip");
		result.put(4, "pending");
		result.put(5, "N/A");
		RESULT = Collections.unmodifiableMap(result);
	}
}
