package Client;

import java.util.Scanner;

public class Client extends Thread {

	public Client() {

	}

	public void run() {

		StartCal();

	}

	private void StartCal() {

		printMenu();
		ICalculatorGUI calculator = getCalculator();
		if (calculator != null)
			Calculate(calculator);

	}

	private void Calculate(ICalculatorGUI calculator) {
		HttpPostReq postReq = new HttpPostReq();

		String calcExpr = calculator.calc();
		while (!calcExpr.equals("exit")) {			

			System.out.println("calcExpr: " + calcExpr);
			String result = postReq.sendPostRequest(calcExpr);

			calculator.printResult(result);
			calcExpr = calculator.calc();
		}

	}

	private ICalculatorGUI getCalculator() {
		Scanner sc = new Scanner(System.in);
		ICalculatorGUI calculator = null;
		int selection;
		selection = sc.nextInt();
		while (selection != 0) {
			if (selection >= 0 && selection <= 3) {
				calculator = CalculatorFactory.getCalculator(selection);

				break;
			} else {
				System.out.println("Type a valid value");
			}
		}
		return calculator;
	}

	private void printMenu() {
		System.out.println("Menu : ");
		System.out.println("Type any number between 1 and 3 to choose UI");
		System.out.println("1)Create a new console Calculator GUI");
		System.out.println("2)Create a new Calculator GUI");
		System.out.println("3)Create a new CMD Calculator GUI");
		System.out.println("0)Exit");

	}

}
