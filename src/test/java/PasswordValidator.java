import org.jetbrains.annotations.NotNull;

public class PasswordValidator {
	private final String password;

	public PasswordValidator(@NotNull String password) {
		this.password = password;
	}

	public boolean validate(@NotNull String inputPassword) {
		return password.equals(inputPassword);
	}
}
