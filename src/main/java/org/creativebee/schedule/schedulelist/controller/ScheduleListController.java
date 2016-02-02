package org.creativebee.schedule.schedulelist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping("scheduleList")
public class ScheduleListController {

	@Autowired
	protected NamedParameterJdbcTemplate jdbcTemplate;

	@RequestMapping
	public String scheduleList(Model model) {

		return "scheduleList";
	}
}
