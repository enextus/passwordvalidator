package org.Inputpass;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {

	public static final String PASSWORD = "123";
	public static final int MAX_ATTEMPTS = 3;
	public static final int TIMEOUT = 15;
	public static final int SLEEP = 50;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		ExecutorService executor = Executors.newSingleThreadExecutor();

		for (int attempts = 1; attempts <= MAX_ATTEMPTS; attempts++) {

			System.out.printf("Enter password (%d/%d):%n", attempts, MAX_ATTEMPTS);

			Future<String> future = executor.submit(() -> {
				try {
					while (System.in.available() == 0) {
						Thread.sleep(SLEEP);
					}
					return scanner.nextLine();
				} catch (IOException | InterruptedException e) {
					return null;
				}
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
				System.out.println("Timeout. Please try again.");
				future.cancel(true);
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("Program terminated.");
		executor.shutdown();
	}

}
