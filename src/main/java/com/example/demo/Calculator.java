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
	 * @param mathExpr
	 * @return
	 * @throws Exception
	 */
	public String calcWithSyaAlgo(String mathExpr) throws Exception {
		ShuntingYardAlgorithm sta=new ShuntingYardAlgorithm();
		return Double.toString(sta.solveMathExpr(mathExpr));
	}

	/**
	 * Calculate using java script engine API
	 * 
	 * @param mathExpr
	 * @return
	 * @throws ScriptException
	 */
	public String calcWithJs(String mathExpr) throws ScriptException {
		final ScriptEngineManager engineManager = new ScriptEngineManager();
		final ScriptEngine engine = engineManager.getEngineByName("JavaScript");
		//System.out.println(engine.eval(mathExpr));
		return engine.eval(mathExpr).toString();
	}
}
