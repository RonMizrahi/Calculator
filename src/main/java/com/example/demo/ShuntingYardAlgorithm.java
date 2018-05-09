package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;
import org.springframework.stereotype.Component;

/**
 * Algorithm to Parse and Solve math expression.
 * The algorithm was invented by Edsger Dijkstra.
 * This code works like Reverse Polish notation (PRN). 
 * @author ronm
 *
 */
@Component
public class ShuntingYardAlgorithm {

	final static Map<Character, Integer> OpPrecedence;
	static {
		OpPrecedence = new HashMap<Character, Integer>();
		OpPrecedence.put('+', 0);
		OpPrecedence.put('-', 0);
		OpPrecedence.put('*', 1);
		OpPrecedence.put('/', 1);
	}
	// Operators stack
	Stack<Character> ops;
	// output queue Example: 2-2*3 -> 223*-
	Queue<Object> output;
	// stack which help to sum the output queue
	Stack<Double> sum;

	public ShuntingYardAlgorithm() {
		ops = new Stack<Character>();
		output = new LinkedBlockingQueue<>();
		sum = new Stack<Double>();
	}

	/**
	 * Solve the input math Expression
	 * 
	 * @param mathExpr Simple math expression
	 * @usage solveTheEquation("2-2*3")
	 * @return Answer
	 * @throws Exception expression parsing error
	 */
	public double solveMathExpr(String mathExpr) throws Exception {
		char[] tokens = mathExpr.toCharArray();
		boolean expectOperand = true;
		for (int i = 0; i < tokens.length; i++) {

			// Skip whitespaces
			if (tokens[i] == ' ')
				continue;

			// If number
			if (tokens[i] >= '0' && tokens[i] <= '9') {
				StringBuffer sbuf = new StringBuffer();
				while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9'
						|| i < tokens.length && tokens[i] == '.') {
					sbuf.append(tokens[i++]);
				}
				output.add(Double.parseDouble(sbuf.toString()));
				expectOperand = false;
				i--;
			}

			// If Operator
			else if (OpPrecedence.containsKey(tokens[i]))
			{
				if(expectOperand)
					throw new Exception("Expected an operand but found " + tokens[i]);
				else
				{
					pushOperator(tokens[i]);
					expectOperand = true;
				}
			}
			else
				throw new Exception("Couldn't parse the equation " + tokens[i] + " isn't supported.");

		}

		// One of the Algorithm steps
		while (!ops.isEmpty()) {
			output.add(ops.pop());
		}

		// Calculate the evaluated expression
		while (!output.isEmpty()) {
			Object temp = output.poll();
			if (temp instanceof Double)
				sum.push((Double) temp);
			else {
				double y = sum.pop();
				double x = sum.pop();
				sum.push(applyOp((char) temp, x, y));
			}
		}
		return (double) sum.pop();
	}

	/**
	 * Push operator to ops stack
	 * 
	 * @param op Operator as char
	 */
	private void pushOperator(char op) {
		if (ops.isEmpty()) {
			ops.push(op);
			return;
		} else {
			// While top of 'ops' has same or greater precedence to current token.
			while (!ops.isEmpty() && OpPrecedence.get(ops.peek()) >= OpPrecedence.get(op)) {
				char temp = ops.pop();
				output.add(temp);
			}
		}
		ops.push(op);
	}

	// A utility method to apply an operator 'op' on operands 'a'
	// and 'b'. Return the result.
	private double applyOp(char op, double a, double b) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			if (b == 0)
				throw new UnsupportedOperationException("Cannot divide by zero");
			return a / b;
		}
		return 0;
	}
}
