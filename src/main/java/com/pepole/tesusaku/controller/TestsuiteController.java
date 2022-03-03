package com.pepole.tesusaku.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pepole.tesusaku.domain.user.model.Testcase;
import com.pepole.tesusaku.domain.user.model.Testsuite;
import com.pepole.tesusaku.domain.user.service.TestcaseService;
import com.pepole.tesusaku.domain.user.service.TestsuiteService;
import com.pepole.tesusaku.form.TestcaseForm;
import com.pepole.tesusaku.form.TestsuiteForm;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TestsuiteController {
	
	private final TestsuiteService testsuiteService;
	
	private final TestcaseService testcaseService;
	
	private final ModelMapper modelMapper;
	
	@GetMapping("/testsuite/create")
	public String getForm(Model model, @ModelAttribute TestsuiteForm testsuiteForm) {
		return "/testsuite/create";
	}

	@PostMapping("/testsuite/create")
	public String createSuite(Model model, @Validated @ModelAttribute TestsuiteForm testsuiteForm) {
        Testsuite suite = modelMapper.map(testsuiteForm, Testsuite.class);

        testsuiteService.create(suite);
		
		return "redirect:/user";
	}

	@GetMapping("/testsuite/list")
	public String getSuiteList(Model model, @ModelAttribute TestsuiteForm testsuiteForm) {
    	
    	List<Testsuite> suites = testsuiteService.getSuites();
    	
        // Modelに登録
        model.addAttribute("testsuites", suites);

		return "/testsuite/list";
	}

	@GetMapping("/testsuite/{path}")
	public String getSuite(@PathVariable String path, Model model, @ModelAttribute TestcaseForm testcaseForm) {

        // Modelに登録
        model.addAttribute("path", path);
        
        List<Testcase> cases = testcaseService.selectBySuiteId(path);
        model.addAttribute("cases", cases);
		
		return "/testsuite/index";
	}
	
	@PostMapping("/testsuite/{path}")
	public String createCase(@PathVariable String path, Model model, @ModelAttribute TestcaseForm testcaseForm) {
		
		if (testcaseForm.getCaseId().length == 1) {
			testcaseService.create(testcaseForm, path);
		} else {
			testcaseService.createBulk(testcaseForm, path);
		}

		return "redirect:/user";
	}
}