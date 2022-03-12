package com.pepole.tesusaku.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pepole.tesusaku.form.UserListForm;
import com.pepole.tesusaku.model.MUser;
import com.pepole.tesusaku.model.Testsuite;
import com.pepole.tesusaku.service.TestsuiteService;
import com.pepole.tesusaku.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    
    private final TestsuiteService testsuiteService;

    private final ModelMapper modelMapper;
    
    private final HttpServletRequest httpServletRequest;

    /** ユーザー詳細画面を表示 */
    @GetMapping("/user")
    public String index(Model model, Authentication loginUser) {
    	
    	List<Testsuite> suites = testsuiteService.getSuiteList(loginUser.getName());
    	
        model.addAttribute("testsuites", suites);

    	model.addAttribute("requestUri", httpServletRequest.getRequestURI());

        return "user/index";
    }

    /** ユーザー一覧画面を表示 */
    @GetMapping("/user/list")
    public String getUserList(@ModelAttribute UserListForm form, Model model) {

        // formをMUserクラスに変換
        MUser user = modelMapper.map(form, MUser.class);

        // ユーザー一覧取得
        List<MUser> userList = userService.getUsers(user);

        // Modelに登録
        model.addAttribute("userList", userList);

        // ユーザー一覧画面を表示
        return "user/list";
    }

    /** ユーザー検索処理 */
    @PostMapping("/user/list")
    public String postUserList(@ModelAttribute UserListForm form, Model model) {

        // formをMUserクラスに変換
        MUser user = modelMapper.map(form, MUser.class);

        // ユーザー検索
        List<MUser> userList = userService.getUsers(user);

        // Modelに登録
        model.addAttribute("userList", userList);

        // ユーザー一覧画面を表示
        return "user/list";
    }

    
}