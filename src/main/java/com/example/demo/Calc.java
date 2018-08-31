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
	private Calculator calculator;
	
	@Autowired
	private DbController database;
	
	// This means URL's start with /calcjs, Post request js Algo
	@RequestMapping(path = "/calcjs", method = RequestMethod.POST)
	@ResponseBody // @ResponseBody means the returned String is the response
	public String solveMathExprJS(@RequestBody MathExpression mathExpr) throws ScriptException {
		String strMathExpr = mathExpr.getMathExpr();
		String evalMathExpr = calculator.calcWithJs(strMathExpr);
		database.setAttribute(strMathExpr, evalMathExpr);
		return evalMathExpr;
	}

	// This means URL's start with /calc, Post request Sya Algo
	@RequestMapping(path = "/calc", method = RequestMethod.POST)
	@ResponseBody
	public String solveSyaAlgoMAthExpr(@RequestBody MathExpression mathExpr) throws Exception {
		String strMathExpr = mathExpr.getMathExpr();
		String evalMathExpr = calculator.calcWithSyaAlgo(strMathExpr);
		database.setAttribute(strMathExpr, evalMathExpr);
		return evalMathExpr;
	}
	
	// This means URL's start with /calcInterp, Post request Interpreter Algo
	@RequestMapping(path = "/calcInterp", method = RequestMethod.POST)
	@ResponseBody
	public String solveMAthExprcalcInterpreter(@RequestBody MathExpression mathExpr) throws Exception {

		String strMathExpr = mathExpr.getMathExpr();
		String evalMathExpr = calculator.calcWithInterpreter(strMathExpr);
		database.setAttribute(strMathExpr, evalMathExpr);
		
		return evalMathExpr;
	}
	
	// This means URL's start with /searchDB, Post request /search answer in DB
	@RequestMapping(path = "/searchDB", method = RequestMethod.POST)
	@ResponseBody
	public String searchInDB(@RequestBody MathExpression mathExpr) throws Exception {
 		return database.search(mathExpr.getMathExpr());
	
	}

	// This means URL's start with /calc, get request user guide
	@RequestMapping(path = "/calc", method = RequestMethod.GET)
	@ResponseBody
	public String userGuide() {
		return "Please send a json format like { \"mathExpr\":\"1+2+3\" }";
	}

 
	

}
