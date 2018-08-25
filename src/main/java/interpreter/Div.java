package interpreter;

public class Div implements MathExpression{
	private MathExpression leftMathExpression;
	private MathExpression rightMathExpression;
	
	
	public Div(MathExpression leftMathExpression, MathExpression rightMathExpression){
		this.leftMathExpression=leftMathExpression;
		this.rightMathExpression=rightMathExpression;
	}
	
	@Override
	public int interpret() {
		return this.leftMathExpression.interpret() / this.rightMathExpression.interpret();
	}
	
}