package interpreter;

public class Sub implements MathExpression{
	private MathExpression leftMathExpression;
	private MathExpression rightMathExpression;
	
	
	public Sub(MathExpression leftMathExpression, MathExpression rightMathExpression){
		this.leftMathExpression=leftMathExpression;
		this.rightMathExpression=rightMathExpression;
	}
	
	@Override
	public int interpret() {
		return this.leftMathExpression.interpret() - this.rightMathExpression.interpret();
	}
	
}