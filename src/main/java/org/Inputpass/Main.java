package org.Inputpass;

import java.io.Console;
import java.util.concurrent.*;

public class Main {

	public static final String PASSWORD = "123";
	public static final int MAX_ATTEMPTS = 3;
	public static final int TIMEOUT = 5;

	public static void main(String[] args) {
		Console console = System.console();
		if (console == null) {
			System.err.println("No console available. Terminating.");
			System.exit(1);
		}
		ExecutorService executor = Executors.newSingleThreadExecutor();

		for (int attempts = 1; attempts <= MAX_ATTEMPTS; attempts++) {
			System.out.printf("Enter your password and press enter | attempts: %d/%d %n", attempts, MAX_ATTEMPTS);
			Future<String> future = executor.submit(() -> {
				char[] passwordChars = console.readPassword();
				return new String(passwordChars);
			});

			try {
				String inputPassword = future.get(TIMEOUT, TimeUnit.SECONDS);

				if (PASSWORD.equals(inputPassword)) {
					System.out.println("Access granted.");
					executor.shutdown();
					return;
				} else {
					System.out.println("Incorrect password.");
				}
			} catch (TimeoutException e) {
				System.out.println("Timeout. Program terminated.");
				executor.shutdownNow();
				System.exit(2); // emergency program termination with exit code 2.
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("Program terminated.");
		executor.shutdown();
	}

}
