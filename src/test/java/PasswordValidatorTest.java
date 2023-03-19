import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {
	@Test
	void testValidPassword() {
		PasswordValidator passwordValidator = new PasswordValidator("123");
		assertTrue(passwordValidator.validate("123"));
	}

	@Test
	void testInvalidPassword() {
		PasswordValidator passwordValidator = new PasswordValidator("123");
		assertFalse(passwordValidator.validate("124"));
	}

	@Test
	void testEmptyPassword() {
		PasswordValidator passwordValidator = new PasswordValidator("123");
		assertFalse(passwordValidator.validate(""));
	}

	@Test
	void testNullPassword() {
		PasswordValidator passwordValidator = new PasswordValidator("123");
		assertFalse(passwordValidator.validate("null"));
	}
}
