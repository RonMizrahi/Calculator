package Client;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Client extends Thread {

	private ICalculatorGUI calculator;

	public Client() {

	}

	public void run() {

		StartCal();

	}

	private void StartCal() {

		printMenu();
		calculator = getCalculator();
		if (calculator != null)
			Calculate();

	}

	private void Calculate() {
		HttpPostReq postReq = new HttpPostReq();

		String calcExpr = calculator.calc();
		while (!calcExpr.equals("exit")) {

			calculator.print("calcExpr: " + calcExpr);
			String postType = choosePostType();

			postReq.setPostReuest(postType);
			String result = postReq.sendPostRequest(calcExpr);
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			calculator.printResult(result);
			calcExpr = calculator.calc();
		}

	}

	private String choosePostType() {

		calculator.print("choose post type: ");
		calculator.print("Type any number between 1 and 4 to choose UI");
		calculator.print("1)calcjs - js algorithm");
		calculator.print("2)calc - Shunting Yard Algorithm");
		calculator.print("3)calcInterp - Interpreter algorithm");
		calculator.print("4)searchDB - search query in DB");
		Scanner sc = new Scanner(System.in);
		int selection;
		selection = sc.nextInt();
		String postType = "";
		while (selection < 1 || selection > 4) {
			calculator.print("Type a valid value");
			selection = sc.nextInt();
		}
		switch (selection) {
		case 1:
			postType = "calcjs";
			break;
			
		case 2:
			postType = "calc";
			break;

		case 3:
			postType = "calcInterp";
			break;

		case 4:
			postType = "searchDB";
			break;

		default:
			break;
		}
		return postType;
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
				calculator.print("Type a valid value");
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
