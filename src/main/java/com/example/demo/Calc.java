package com.example.demo;

import javax.script.ScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Calc { 

	// This means to get the bean from spring container
	@Autowired
	Calculator calculator;

	// This means URL's start with /calcjs, Post request
	@RequestMapping(path = "/calcjs", method = RequestMethod.POST)
	@ResponseBody // @ResponseBody means the returned String is the response
	public String solveMathExprJS(@RequestBody MathExpression mathExpr) throws ScriptException {
		return calculator.calcWithJs(mathExpr.getMathExpr());
	}

	@RequestMapping(path = "/calc", method = RequestMethod.POST)
	@ResponseBody
	public String solveMAthExpr(@RequestBody MathExpression mathExpr) throws Exception {
		return calculator.calcWithSyaAlgo(mathExpr.getMathExpr());
	}

	@RequestMapping(path = "/calc", method = RequestMethod.GET)
	@ResponseBody
	public String userGuide() {
		return "Please send a json format like { \"mathExpr\":\"1+2+3\" }";
	}

}
