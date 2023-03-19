package org.Inputpass;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

	// здесь указывается правильный пароль
	public static final String PASSWORD = "123";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AtomicReference<String> inputPassword = new AtomicReference<>();

		System.out.println("Введите пароль: ");

		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			inputPassword.set(scanner.nextLine());
			return inputPassword;
		});

		try {
			String result = executor.submit(scanner::nextLine).get(7, TimeUnit.SECONDS);
			if (result.equals(PASSWORD)) {
				System.out.println("Доступ разрешен.");
			} else {
				System.out.println("Неправильный пароль. Попробуйте снова через 15 минут.");
				Thread.sleep(1000 * 60 * 15); // ждем 15 минут
			}
		} catch (TimeoutException e) {
			System.out.println("Время истекло. Попробуйте снова.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		executor.shutdown();
	}

}
