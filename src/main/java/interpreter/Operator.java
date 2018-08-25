package interpreter;

public class Operator {

	public static boolean isOperator(String op){
		if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")){
			return true;
		}
		return false;
	}

	public static MathExpression getOperator(String op, MathExpression left, MathExpression right){
		switch (op){
		case "+":
			return new Add(left, right);
		case "-":
			return new Sub(left, right);
		case "*":
			return new Mul(left, right);
		case "/":
			return new Div(left, right);
		}
		return null;
	}
}
