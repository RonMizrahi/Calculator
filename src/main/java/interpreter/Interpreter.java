package interpreter;

 
public class Interpreter {

	public static int eval(String mathExpr) {

        return Eval.inEvalMathExpr(mathExpr);
	}
	
	public static void main(String[] args) {
		System.out.println(eval("1 + 2 + 3"));
	}

}
