package interpreter;

public class Number implements MathExpression {

	private double n;

	public Number(double n) {
		this.n = n;
	}

	@Override
	public double interpret() {
		return n;
	}

}
