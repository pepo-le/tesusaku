package com.pepole.tesusaku.controller;

import java.util.Locale;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pepole.tesusaku.application.service.UserApplicationService;
import com.pepole.tesusaku.form.GroupOrder;
import com.pepole.tesusaku.form.SignupForm;
import com.pepole.tesusaku.model.MUser;
import com.pepole.tesusaku.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {

    private final UserApplicationService userApplicationService;

    private final UserService userService;

    private final ModelMapper modelMapper;

    /** ユーザー登録画面を表示 */
    @GetMapping("/signup")
    public String getSignup(Model model, Locale locale, @ModelAttribute SignupForm form) {

        Map<String, Integer> roleMap = userApplicationService.getRoleMap(locale);
        model.addAttribute("roleMap", roleMap);

        // ユーザー登録画面に遷移
        return "user/signup";
    }

    /** ユーザー登録処理 */
    @PostMapping("/signup")
    public String postSignup(Model model, Locale locale,
            @ModelAttribute @Validated(GroupOrder.class) SignupForm form,
            BindingResult bindingResult) {

        // 入力チェック結果
        if (bindingResult.hasErrors()) {
            // NG:ユーザー登録画面に戻ります
            return getSignup(model, locale, form);
        }

        log.info(form.toString());

        // formをMUserクラスに変換
        MUser user = modelMapper.map(form, MUser.class);

        // ユーザー登録
        userService.signup(user);

        // ログイン画面にリダイレクト
        return "redirect:/login";
    }

    /** データベース関連の例外処理 */
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {

        // 空文字をセット
        model.addAttribute("error", "");

        // メッセージをModelに登録
        model.addAttribute("message", "SignupControllerで例外が発生しました");

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }

    /** その他の例外処理 */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {

        // 空文字をセット
        model.addAttribute("error", "");

        // メッセージをModelに登録
        model.addAttribute("message", "SignupControllerで例外が発生しました");

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }
}
