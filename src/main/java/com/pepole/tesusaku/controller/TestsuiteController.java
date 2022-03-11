package com.pepole.tesusaku.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pepole.tesusaku.form.TestcaseForm;
import com.pepole.tesusaku.form.TestsuiteForm;
import com.pepole.tesusaku.model.Testcase;
import com.pepole.tesusaku.model.Testsuite;
import com.pepole.tesusaku.service.TestcaseService;
import com.pepole.tesusaku.service.TestsuiteService;
import com.pepole.tesusaku.util.TestcaseComponent;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TestsuiteController {
	
	private final TestsuiteService testsuiteService;
	
	private final TestcaseService testcaseService;
	
	private final TestcaseComponent testcaseComponent;
	
	private final ModelMapper modelMapper;
	
	@GetMapping("/testsuite/create")
	public String getCreateForm(Model model, @ModelAttribute TestsuiteForm testsuiteForm) {
		return "/testsuite/create";
	}

	@PostMapping("/testsuite")
	public String createSuite(Model model, @Validated @ModelAttribute TestsuiteForm testsuiteForm,
			Authentication loginUser) {

        Testsuite suite = modelMapper.map(testsuiteForm, Testsuite.class);
        suite.setAdminId(loginUser.getName());

        testsuiteService.create(suite);
		
		return "redirect:/user";
	}
	
	@GetMapping("/testsuite")
	public String getSuiteList(Model model, @ModelAttribute TestsuiteForm testsuiteForm,
			Authentication loginUser) {
    	
    	List<Testsuite> suites = testsuiteService.getSuiteList(loginUser.getName());
    	
        // Modelに登録
        model.addAttribute("testsuites", suites);

		return "/testsuite/list";
	}

	@GetMapping("/testsuite/{path}")
	public String getSuite(@PathVariable String path, Model model, @ModelAttribute TestcaseForm testcaseForm,
			Authentication loginUser) {
        model.addAttribute("path", path);

		Map<Integer, String> resultMap = testcaseComponent.getResultMap();
		model.addAttribute("resultMap", resultMap);
        
        List<Testcase> cases = testcaseService.selectBySuiteId(path);
        model.addAttribute("cases", cases);
		
		return "/testsuite/caseform";
	}
	
	@PostMapping("/testsuite/{path}")
	public String createSuite(@PathVariable String path, Model model, @ModelAttribute TestcaseForm testcaseForm,
			Authentication loginUser) {
		
		if (testcaseForm.getCaseId().length == 1) {
			testcaseService.create(testcaseForm, path);
		} else {
			testcaseService.editBulk(testcaseForm, path);
		}

		return "redirect:/user";
	}
}