package org.creativebee.schedule.holidayList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping("holiday")
public class HolidayController {

	@RequestMapping
	public String holiday() {

		return "holiday";

	}
}
