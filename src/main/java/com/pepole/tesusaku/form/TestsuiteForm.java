package com.pepole.tesusaku.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TestsuiteForm {
	@Size(max = 200)
	@NotBlank
	private String suiteName;
	
	private String[] assign;
}
