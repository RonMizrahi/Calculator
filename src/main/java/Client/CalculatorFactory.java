package Client;

public class CalculatorFactory {

	public static ICalculatorGUI getCalculator(int choise) {
		
		switch (choise) {
		case 1:			
			return  new ConsoleCalculator();
			
		case 2:			
			return new GUICalculator();
			
		case 3:			
			return new CmdCalculator();
			
		default:
			break;
		}
		
		
		return null;
		
	}
	
}
