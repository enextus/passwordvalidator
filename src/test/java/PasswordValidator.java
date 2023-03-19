import org.jetbrains.annotations.NotNull;

public class PasswordValidator {
	private final String password;

	public PasswordValidator(String password) {
		this.password = password;
	}

	public boolean validate(@NotNull String inputPassword) {
		return inputPassword.equals(password);
	}

}
