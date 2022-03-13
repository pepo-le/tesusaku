package com.pepole.tesusaku.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pepole.tesusaku.form.TestcaseForm;
import com.pepole.tesusaku.form.TestsuiteForm;
import com.pepole.tesusaku.model.MUser;
import com.pepole.tesusaku.model.Testcase;
import com.pepole.tesusaku.model.Testsuite;
import com.pepole.tesusaku.service.AssignService;
import com.pepole.tesusaku.service.TestcaseService;
import com.pepole.tesusaku.service.TestsuiteService;
import com.pepole.tesusaku.service.UserService;
import com.pepole.tesusaku.util.TestcaseComponent;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TestsuiteController {
	
	private final TestsuiteService testsuiteService;
	
	private final TestcaseService testcaseService;

	private final UserService userService;
	
	private final AssignService assignService;
	
	private final TestcaseComponent testcaseComponent;
	
	private final ModelMapper modelMapper;
	
	private final HttpServletRequest httpServletRequest;
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/testsuite/create")
	public String getCreateForm(Model model, @ModelAttribute TestsuiteForm testsuiteForm,
			Authentication loginUser) {
		List<MUser> users = userService.getOthers(loginUser.getName());

    	model.addAttribute("requestUri", httpServletRequest.getRequestURI());
		
		model.addAttribute("users", users);
		
		return "/testsuite/create";
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/testsuite")
	public String createSuite(Model model, @Validated @ModelAttribute TestsuiteForm testsuiteForm,
			Authentication loginUser) {

        Testsuite suite = modelMapper.map(testsuiteForm, Testsuite.class);
        suite.setAdminId(loginUser.getName());
        
        Optional<String[]> optionalUsers = Optional.ofNullable(testsuiteForm.getAssign());
        List<String> assignUsers = new ArrayList<>(Arrays.asList(optionalUsers.orElse(new String[0])));
        
        testsuiteService.create(suite, assignUsers);

		return "redirect:/user";
	}
	
	@GetMapping("/testsuite")
	public String getSuiteList(Model model, @ModelAttribute TestsuiteForm testsuiteForm,
			Authentication loginUser) {
    	
    	List<Testsuite> suites = testsuiteService.getSuiteList(loginUser.getName());
    	
        model.addAttribute("testsuites", suites);
		
    	model.addAttribute("requestUri", httpServletRequest.getRequestURI());

		return "/testsuite/list";
	}

	@GetMapping("/testsuite/{suiteId}")
	public String getSuite(@PathVariable String suiteId, Model model, @ModelAttribute TestcaseForm testcaseForm,
			Authentication loginUser) {
        model.addAttribute("suiteId", suiteId);

		Map<Integer, String> resultMap = testcaseComponent.getResultMap();
		model.addAttribute("resultMap", resultMap);
        
        List<Testcase> cases = testcaseService.getBySuiteId(suiteId);
        model.addAttribute("cases", cases);

		model.addAttribute("isAssigned", isAssigned(suiteId, loginUser.getName()));
		
		return "/testsuite/caseform";
	}
	
	@PostMapping("/testsuite/{suiteId}")
	public String createSuite(@PathVariable String suiteId, Model model, @ModelAttribute TestcaseForm testcaseForm,
			Authentication loginUser) {

		if (isAssigned(suiteId, loginUser.getName())) {
			// アサインされているとき
			
			String[] roles = loginUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new);
			
			if (Arrays.asList(roles).contains("ROLE_ADMIN")) {
				testcaseService.updateCases(testcaseForm, suiteId);
			} else {
				testcaseService.updateResult(testcaseForm, suiteId);
			}
		}		

		return "redirect:/user";
	}
	
	// アサインの確認
	private boolean isAssigned(String suiteId, String userId) {
		List<String> assignedUsers = assignService.getAssignedUsers(suiteId);
		return assignedUsers.contains(userId);
	}
}