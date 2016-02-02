package org.creativebee.schedule.password.controller;

import java.sql.Timestamp;
import java.util.Iterator;

import org.creativebee.schedule.password.bean.PasswordForm;
import org.creativebee.schedule.password.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@Transactional
@SessionAttributes(value = "passwordForm")
@RequestMapping("password")
public class PasswordController {

	@Autowired
	protected NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	protected CityMapper cityMapper;

	@ModelAttribute
	protected PasswordForm getPasswordForm() {

		return new PasswordForm();
	};

	@RequestMapping(method = RequestMethod.GET)
	public String show(@AuthenticationPrincipal User user, PasswordForm passwordForm, Model model) {

		System.out.println("mybatis:" + cityMapper.selectCityById(10));

		testing(user);

		// 初期表示時にフォームにセットし、セッション上に退避
		passwordForm.setUpdateTimestamp(new Timestamp(100));

		System.out.println(passwordForm.getUpdateTimestamp());

		return "password";
	}

	private void testing(User user) {

		System.out.println(user.getUsername());
		System.out.println(user.getPassword());

		Iterator<GrantedAuthority> iter = user.getAuthorities().iterator();

		while (iter.hasNext()) {

			GrantedAuthority auth = iter.next();

			System.out.println(auth.getAuthority());
		}
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@AuthenticationPrincipal User user, @Validated PasswordForm passwordForm, BindingResult result,
			SessionStatus sessionStatus, Model model) {

		System.out.println(passwordForm.getUpdateTimestamp());

		// 入力チェックエラーがある場合
		if (result.hasErrors()) {

			return show(user, passwordForm, model);
		}

		// 登録前にtimestampでレコードロック

		// レコードの登録処理
		System.out.println(passwordForm.getUserId());
		System.out.println(passwordForm.getPassword());
		System.out.println(passwordForm.getNewPassword());
		System.out.println(passwordForm.getNewPasswordCheck());

		// セッションの破棄
		sessionStatus.setComplete();

		return "redirect:/password";
	}

}
