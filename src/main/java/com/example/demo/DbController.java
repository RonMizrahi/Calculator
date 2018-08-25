package com.example.demo;


public interface DbController {

	public void PrintAllCollections();
	
	public void setAttribute(String mathExpr, String result);
	
	public String search(String mathExpr);
	
	
}
