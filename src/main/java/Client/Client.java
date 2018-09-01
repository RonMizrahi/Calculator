package Client;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Database.User;

public class Client extends Thread {

	private ICalculatorGUI UiCalculator;
	private User clientUser;
	
	public Client(User clientUser) {
		this.clientUser = clientUser;
	}

	public void run() {
		printMenu();
		initUICalculator();
		if (UiCalculator != null)
			startCalculate();
	}

	private void startCalculate() {

		HttpPostReq postReq = new HttpPostReq(clientUser);
		String calcExpr = UiCalculator.getUserInputToCalculate();
		while (!calcExpr.equals("exit")) {

			UiCalculator.print("calcExpr: " + calcExpr);
			String postType = choosePostType();
			postReq.setPostReuest(postType);
			String result = postReq.sendPostRequest(calcExpr);
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			UiCalculator.printResult(result);
			calcExpr = UiCalculator.getUserInputToCalculate();
		}
		UiCalculator.print("client exit");
	}

	private String choosePostType() {

		UiCalculator.print("choose post type: ");
		UiCalculator.print("Type any number between 1 and 4 to choose Post request type");
		UiCalculator.print("1)calcjs - js algorithm");
		UiCalculator.print("2)calc - Shunting Yard Algorithm");
		UiCalculator.print("3)calcInterp - Interpreter algorithm");
		UiCalculator.print("4)searchDB - search query in DB");
		Scanner sc = new Scanner(System.in);
		int selection;
		selection = sc.nextInt();
		String postType = "";
		while (selection < 1 || selection > 4) {
			UiCalculator.print("Type a valid value");
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

	private void initUICalculator() {
		Scanner sc = new Scanner(System.in);

		int selection;
		selection = sc.nextInt();
		while (selection != 0) {
			if (selection >= 0 && selection <= 3) {
				UiCalculator = CalculatorFactory.getCalculator(selection);

				break;
			} else {
				System.out.println("Type a valid value");
			}
		}

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
