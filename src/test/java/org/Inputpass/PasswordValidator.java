package org.Inputpass;

import org.jetbrains.annotations.NotNull;

public class PasswordValidator {
	private final String password;
	public static final String PASSWORD = "123";

	public PasswordValidator(@NotNull String password) {
		this.password = password;
	}

	public boolean validate(@NotNull String inputPassword) {
		return password.equals(inputPassword);
	}

	public boolean validatePassword(@NotNull  String inputPassword) {
		return inputPassword.equals(PASSWORD);
	}

}
