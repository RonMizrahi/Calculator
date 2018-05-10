package com.example.demo;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.springframework.stereotype.Component;

@Component
public class Calculator {

	public Calculator() {
	}

	/**
	 * Calculate the math expression using ShuntingYard Algorithm
	 * 
	 * @param mathExpr Simple math expression
	 * @return The calculation
	 * @throws Exception
	 */
	public String calcWithSyaAlgo(String mathExpr) throws Exception {
		ShuntingYardAlgorithm sta = new ShuntingYardAlgorithm();
		return Double.toString(sta.solveMathExpr(mathExpr));
	}

	/**
	 * Calculate using java script engine API - A scripting engine is like an
	 * interpreter that turns script into machine code
	 * 
	 * @param mathExpr Simple math expression
	 * @return The calculation
	 * @throws ScriptException
	 */
	public String calcWithJs(String mathExpr) throws ScriptException {
		final ScriptEngineManager engineManager = new ScriptEngineManager();
		final ScriptEngine engine = engineManager.getEngineByName("JavaScript");
		try {
			// Sleep to trigger the log warning
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return engine.eval(mathExpr).toString();
	}
}
