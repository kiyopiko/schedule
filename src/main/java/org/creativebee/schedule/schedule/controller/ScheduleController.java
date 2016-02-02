package org.creativebee.schedule.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping("schedule")
public class ScheduleController {

	@Autowired
	protected NamedParameterJdbcTemplate jdbcTemplate;

	@RequestMapping
	public String schedule(Model model) {

		return "schedule";
	}

}