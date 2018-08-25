package interpreter;

import java.util.Stack;
import java.util.StringTokenizer;

public class Eval {

	public static int EvalMathExpr(String strExpr){
		
		Stack<MathExpression> stk = new Stack<>();
		String[] Tokens = strExpr.split(" ");
		
		for(String token : Tokens){
			
			if (Operator.isOperator(token)){
				MathExpression rightExpr = stk.pop();
				MathExpression leftExpr = stk.pop();
				MathExpression operator = Operator.getOperator(token, leftExpr, rightExpr);
				int result = operator.interpret();
				stk.push(new Number(result));
				
			}
			
			else{
				MathExpression num = new Number(Integer.parseInt(token));
				stk.push(num);
			}
			
		}
		int ans = stk.pop().interpret();
		System.out.println("("+strExpr+") = " +ans);

		return ans;
	}
	
	public static int inEvalMathExpr(String strExpr){
		
		Stack<MathExpression> stk = new Stack<>();
		String[] Tokens = strExpr.split("\\s+");
		boolean rightNum = false;;
		String op = "";
		for(String token : Tokens){
			
			if (Operator.isOperator(token)){
				op = token;	
				rightNum = true;
			}
			
			else{
				MathExpression num = new Number(Integer.parseInt(token));
				stk.push(num);
				if (rightNum){
					rightNum = false;
					MathExpression rightExpr = stk.pop();
					MathExpression leftExpr = stk.pop();
					MathExpression operator =Operator.getOperator(op, leftExpr, rightExpr);			
					int result = operator.interpret();
					stk.push(new Number(result));					
				}
			}
			
		}
		int ans = stk.pop().interpret();
		System.out.println("("+strExpr+") = " +ans);
 
		return ans;
	}
	
	public static int inTreeEvalMathExpr(String strExpr){
		
		Stack<MathExpression> stk = new Stack<>();
		String[] Tokens = strExpr.split("\\s+");
		boolean rightNum = false;;
		String op = "";
		
		
		for(String token : Tokens){
			
			if (Operator.isOperator(token)){
				op = token;	
				rightNum = true;
			}
			
			else{
				MathExpression num = new Number(Integer.parseInt(token));
				stk.push(num);
				if (rightNum){
					rightNum = false;
					MathExpression rightExpr = stk.pop();
					MathExpression leftExpr = stk.pop();
					MathExpression operator =Operator.getOperator(op, leftExpr, rightExpr);			
					int result = operator.interpret();
					stk.push(new Number(result));					
				}
			}
			
		}
		int ans = stk.pop().interpret();
		System.out.println("("+strExpr+") = " +ans);
 
		return ans;
	}
}
