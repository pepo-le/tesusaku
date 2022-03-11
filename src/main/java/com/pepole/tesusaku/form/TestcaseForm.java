package com.pepole.tesusaku.form;

import lombok.Data;

@Data
public class TestcaseForm {
	private String[] suiteId;

	private String[] caseId;

	private String[] caseName;

	private String[] condition;

	private String[] process;

	private String[] expect;

	private String[] result;

	private String[] checkDate;

	private String[] checkVer;

	private String[] defectNo;

	private String[] tester;

	private String[] comment;
}
