package org.creativebee.schedule.menu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.creativebee.schedule.menu.bean.OshiraseBean;
import org.creativebee.schedule.menu.bean.TestTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping("menu")
public class MenuController {

	@Autowired
	protected NamedParameterJdbcTemplate jdbcTemplate;

	@RequestMapping
	public String menu(Model model) {

		List<OshiraseBean> oshiraseList = new ArrayList<>();
		oshiraseList.add(new OshiraseBean("タイトル1", "内容1"));
		oshiraseList.add(new OshiraseBean("タイトル2", "内容2"));
		oshiraseList.add(new OshiraseBean("タイトル3", "内容3"));
		oshiraseList.add(new OshiraseBean("タイトル4", "内容4"));
		oshiraseList.add(new OshiraseBean("タイトル5", "内容5"));

		model.addAttribute("oshiraseList", oshiraseList);

		return "menu";
	}

	@RequestMapping(value = "/switch", params = "schedule")
	public String schedule(Model model) {

		return "schedule";
	}

	@RequestMapping(value = "/switch", params = "scheduleList")
	public String scheduleList(Model model) {

		String sql = "select id, params1 from test_table where id = :id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", "aaaaaaaaaaaaaaaaaaaa");

		TestTable ret = jdbcTemplate.queryForObject(sql, param,
				(rs, rownum) -> new TestTable(rs.getString("id"), rs.getString("params1")));

		System.out.println("ret:" + ret.getId());

		List<TestTable> list = new ArrayList<>();

		list.add(ret);

		model.addAttribute("hogeList", list);

		String hoge = new Date().toString();

		String updateSql = "insert into test_table (id, params1) values ('" + hoge + "','hoge3')";

		jdbcTemplate.update(updateSql, param);

		return "scheduleList";
	}

	@RequestMapping(value = "/switch", params = "password")
	public String password(Model model) {

		return "password";
	}

	@RequestMapping(value = "/switch", params = "holiday")
	public String holiday(Model model) {

		return "holiday";
	}

	@RequestMapping(value = "/switch", params = "holidayList")
	public String holidayList(Model model) {

		return "holidayList";
	}
}
