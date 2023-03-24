package org.Inputpass;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

	// the correct password is specified here
	public static final String PASSWORD = "123";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AtomicReference<String> inputPassword = new AtomicReference<>();

		System.out.println("Enter password: ");

		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			inputPassword.set(scanner.nextLine());
			return inputPassword;
		});

		try {
			String result = executor.submit(scanner::nextLine).get(7, TimeUnit.SECONDS);
			if (result.equals(PASSWORD)) {
				System.out.println("Access granted.");
			} else {
				System.out.println("Incorrect password. Please try again in 2 minutes.");
				Thread.sleep(1000 * 60 * 2); // wait for 15 minutes
			}
		} catch (TimeoutException e) {
			System.out.println("Timeout. Please try again.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		executor.shutdown();
	}

}
