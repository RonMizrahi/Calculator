package interpreter;

public class Mul implements MathExpression{
	private MathExpression leftMathExpression;
	private MathExpression rightMathExpression;
	
	
	public Mul(MathExpression leftMathExpression, MathExpression rightMathExpression){
		this.leftMathExpression=leftMathExpression;
		this.rightMathExpression=rightMathExpression;
	}
	
	@Override
	public int interpret() {
		return this.leftMathExpression.interpret() * this.rightMathExpression.interpret();
	}
	
}