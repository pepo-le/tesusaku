package com.pepole.tesusaku.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TestsuiteForm {
	
	@NotBlank
	private String suiteName;
}
