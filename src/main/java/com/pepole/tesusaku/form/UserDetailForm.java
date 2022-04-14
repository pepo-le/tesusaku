package com.pepole.tesusaku.form;

import lombok.Data;

@Data
public class UserDetailForm {
	private String userId;
	private String password;
	private String userName;
	private Integer role;
}