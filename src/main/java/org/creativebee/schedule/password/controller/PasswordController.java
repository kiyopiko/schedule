package org.creativebee.schedule.password.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.creativebee.schedule.password.bean.PasswordForm;
import org.creativebee.schedule.password.domain.repository.CityMapper;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
@Transactional
@SessionAttributes(value = "passwordForm")
@RequestMapping("password")
public class PasswordController {

	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;

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
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = this.requestMappingHandlerMapping.getHandlerMethods();

		for (Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
			RequestMappingInfo mapping = item.getKey();
			HandlerMethod method = item.getValue();

			for (String urlPattern : mapping.getPatternsCondition().getPatterns()) {
				System.out.println(
						method.getBeanType().getName() + "#" + method.getMethod().getName() +
								" <-- " + urlPattern);



				if (urlPattern.equals("some specific url")) {
					//add to list of matching METHODS
				}
			}
		}

		return "forward:password/passwordx";
	}

	@RequestMapping(method = RequestMethod.GET, value = "passwordx")
	public String passwordx(@AuthenticationPrincipal User user, Model model) {

		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		for (StackTraceElement element : elements) {
			System.out.println(element);
		}

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

	@RequestMapping(value = "showDateTime/{hoge}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> showDateTime(@AuthenticationPrincipal User user, Model model) {

		Map<String, String> map = new HashMap<>();
		map.put("datetime", new Date().toString());
		map.put("test", "testaaa");

		return map;
	}

}
