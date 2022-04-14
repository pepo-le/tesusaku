package com.pepole.tesusaku.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pepole.tesusaku.form.UserDetailForm;
import com.pepole.tesusaku.model.MUser;
import com.pepole.tesusaku.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
@Slf4j
public class UserDetailController {

	private final UserService userService;

	private final ModelMapper modelMapper;

	/** ユーザー詳細画面を表示 */
	@GetMapping("/detail/{userId:.+}")
	public String getUser(UserDetailForm form, Model model, @PathVariable("userId") String userId) {
		MUser user = userService.getUserOne(userId);
		user.setPassword(null);

		form = modelMapper.map(user, UserDetailForm.class);

		model.addAttribute("userDetailForm", form);

		// ユーザー詳細画面を表示
		return "user/detail";
	}

	/** ユーザー更新処理 */
	@PostMapping(value = "/detail", params = "update")
	public String updateUser(UserDetailForm form, Model model) {
		try {
			userService.updateUserOne(form.getUserId(),
					form.getPassword(),
					form.getUserName());
		} catch (Exception e) {
			log.error("ユーザー更新でエラー", e);
		}
		return "redirect:/user/list";
	}

	/** ユーザー削除処理 */
	@PostMapping(value = "/detail", params = "delete")
	public String deleteUser(UserDetailForm form, Model model) {
		userService.deleteUserOne(form.getUserId());

		return "redirect:/user/list";
	}
}
