package org.creativebee.schedule.holiday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping("holidayList")
public class HolidayListController {

	@RequestMapping
	public String holidayList() {

		return "holidayList";

	}
}
