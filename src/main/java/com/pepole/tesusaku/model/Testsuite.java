package com.pepole.tesusaku.model;

import java.util.List;

import lombok.Data;

@Data
public class Testsuite {
	private int suiteId;
	private String suiteName;
	private String adminId;

	// リレーション
	private List<String> assignUsers;
}
