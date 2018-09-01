package interpreter;

public class Add implements MathExpression {
	private MathExpression leftMathExpression;
	private MathExpression rightMathExpression;

	public Add(MathExpression leftMathExpression, MathExpression rightMathExpression) {
		this.leftMathExpression = leftMathExpression;
		this.rightMathExpression = rightMathExpression;
	}

	@Override
	public double interpret() {
		return this.leftMathExpression.interpret() + this.rightMathExpression.interpret();
	}

}