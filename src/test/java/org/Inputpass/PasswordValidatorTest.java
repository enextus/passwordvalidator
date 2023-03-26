package org.Inputpass;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    @Test
    void validatePassword_correctPassword_returnsTrue() {
        PasswordValidator passwordValidator = new PasswordValidator("123");
        assertTrue(passwordValidator.validatePassword("123"));
    }

    @Test
    void validatePassword_incorrectPassword_returnsFalse() {
        PasswordValidator passwordValidator = new PasswordValidator("123");
        assertFalse(passwordValidator.validatePassword("incorrect_password"));
    }

    private static class PasswordValidator {
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
}
