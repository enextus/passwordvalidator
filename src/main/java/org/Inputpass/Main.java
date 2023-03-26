package org.Inputpass;

import java.util.Scanner;
import java.util.concurrent.*;

public class Main {

	public static final String PASSWORD = "123";
	public static final int MAX_ATTEMPTS = 3;
	public static final int TIMEOUT = 30;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ExecutorService executor = Executors.newSingleThreadExecutor();

		int attempts = 0;
		boolean success = false;
		while (attempts < MAX_ATTEMPTS && !success) {
			System.out.println("Enter password (" + (attempts + 1) + "/" + MAX_ATTEMPTS + "):");
			Future<String> future = executor.submit(scanner::nextLine);

			try {
				String inputPassword = future.get(TIMEOUT, TimeUnit.SECONDS);

				if (inputPassword.equals(PASSWORD)) {
					System.out.println("Access granted.");
					success = true;
				} else {
					System.out.println("Incorrect password.");
					attempts++;
				}
			} catch (TimeoutException e) {
				System.out.println("Timeout. Please try again.");
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (!success) {
			System.out.println("Program terminated.");
		}

		executor.shutdown();
	}

}
