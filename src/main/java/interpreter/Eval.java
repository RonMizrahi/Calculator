package interpreter;

import java.util.Stack;

public class Eval {

	public static double EvalMathExpr(String strExpr) {

		Stack<MathExpression> stk = new Stack<>();
		String[] Tokens = strExpr.split("\\s+");

		for (String token : Tokens) {

			if (Operator.isOperator(token)) {
				MathExpression rightExpr = stk.pop();
				MathExpression leftExpr = stk.pop();
				MathExpression operator = Operator.getOperator(token, leftExpr, rightExpr);
				double result = operator.interpret();
				stk.push(new Number(result));

			}

			else {
				MathExpression num = new Number(Integer.parseInt(token));
				stk.push(num);
			}

		}
		double ans = stk.pop().interpret();
		return ans;
	}

	public static double inEvalMathExpr(String strExpr) {

		Stack<MathExpression> stk = new Stack<>();
		String[] Tokens = strExpr.split("\\s+");
		boolean rightNum = false;
		String op = "";
		for (String token : Tokens) {

			if (Operator.isOperator(token)) {
				op = token;
				rightNum = true;
			}

			else {
				MathExpression num = new Number(Integer.parseInt(token));
				stk.push(num);
				if (rightNum) {
					rightNum = false;
					MathExpression rightExpr = stk.pop();
					MathExpression leftExpr = stk.pop();
					MathExpression operator = Operator.getOperator(op, leftExpr, rightExpr);
					double result = operator.interpret();
					stk.push(new Number(result));
				}
			}

		}
		double ans = stk.pop().interpret();

		return ans;
	}

	public static double inTreeEvalMathExpr(String strExpr) {

		Stack<MathExpression> stk = new Stack<>();
		String[] Tokens = strExpr.split("\\s+");
		boolean rightNum = false;
		String op = "";

		for (String token : Tokens) {

			if (Operator.isOperator(token)) {
				op = token;
				rightNum = true;
			}

			else {
				MathExpression num = new Number(Integer.parseInt(token));
				stk.push(num);
				if (rightNum) {
					rightNum = false;
					MathExpression rightExpr = stk.pop();
					MathExpression leftExpr = stk.pop();
					MathExpression operator = Operator.getOperator(op, leftExpr, rightExpr);
					double result = operator.interpret();
					stk.push(new Number(result));
				}
			}

		}
		double ans = stk.pop().interpret();

		return ans;
	}
}
