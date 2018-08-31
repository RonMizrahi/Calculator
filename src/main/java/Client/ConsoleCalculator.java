package Client;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class ConsoleCalculator implements ICalculatorGUI {

	public ConsoleCalculator() {
		printMenu();
	}

	@Override
	public String calc() {

		return calculate();

	}

	private String calculate() {

		Scanner user_input = new Scanner(System.in);

		return user_input.nextLine();
	}

	private void printMenu() {

		System.out.println("Please write math Expr like this: 1+2+3");
		System.out.println("write \"exit\" to Exit");

	}

	@Override
	public void printResult(String result) {
		System.out.println("Client result: " + result);

	}

	@Override
	public void print(String str) {

		System.out.println(str);

	}
}
