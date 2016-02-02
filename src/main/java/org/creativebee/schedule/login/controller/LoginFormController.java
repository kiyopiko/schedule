package org.creativebee.schedule.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping("loginForm")
public class LoginFormController {

	@RequestMapping
	public String loginForm() {

		return "loginForm";

	}
}
