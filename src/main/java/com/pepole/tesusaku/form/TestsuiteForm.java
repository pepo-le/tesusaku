package com.pepole.tesusaku.form;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TestsuiteForm {
	@Size(max = 200)
	@NotBlank
	private String suiteName;
	
	private List<String> assignUsers;
}
