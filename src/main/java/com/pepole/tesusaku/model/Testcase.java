package com.pepole.tesusaku.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Testcase {
	private int suiteId;
	private int caseId;
	private String caseName;
	private String condition;
	private String process;
	private String expect;
	private int result;
	private Date checkDate;
	private String checkVer;
	private String defectNo;
	private String tester;
	private String comment;
}
