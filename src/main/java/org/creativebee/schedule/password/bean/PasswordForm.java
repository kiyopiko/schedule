package org.creativebee.schedule.password.bean;

import java.sql.Timestamp;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordForm {

	private Timestamp updateTimestamp;

	@NotBlank
	@Pattern(regexp = "[a-zA-Z0-9]{10,20}")
	@Size(min = 10, max = 20)
	private String userId;

	@NotBlank
	@Pattern(regexp = "[a-zA-Z0-9]{10,20}")
	@Size(min = 10, max = 20)
	private String password;

	@NotBlank
	@Pattern(regexp = "[a-zA-Z0-9]{10,20}")
	@Size(min = 10, max = 20)
	private String newPassword;

	@NotBlank
	@Pattern(regexp = "[a-zA-Z0-9]{10,20}")
	@Size(min = 10, max = 20)
	private String newPasswordCheck;

	@AssertTrue(message = "新しいパスワードと新しいパスワード(確認用)には同一の文字列を入力してください。")
	public boolean isValidPasswords() {

		if (StringUtils.isNotEmpty(this.newPassword) && !this.newPassword.equals(newPasswordCheck)) {
			return false;
		}

		return true;
	}
}
