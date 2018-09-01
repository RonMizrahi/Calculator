package interpreter;

public class Number implements MathExpression {

	private int n;

	public Number(int n) {
		this.n = n;
	}

	@Override
	public int interpret() {
		return n;
	}

}
