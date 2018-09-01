package com.example.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import Database.IUser.UserName;
import Database.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Primary
@Component
public class LocalDb implements DbController {

	private final String folderPath = "C:\\LocalDb";

	public void setAttribute(String mathExpr, String result) {
		// File file = new File(folderPath + File.pathSeparator + mathExpr);

		mathExpr = mathExpr.replaceAll("\\*", "X");
		mathExpr = mathExpr.replaceAll("/", "_");

		try (PrintStream out = new PrintStream(new FileOutputStream(folderPath + File.separator + mathExpr + ".txt"))) {
			out.print(result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String search(String mathExpr) {

		mathExpr = mathExpr.replaceAll("\\*", "X");
		mathExpr = mathExpr.replaceAll("/", "_");
		File file = new File(folderPath + File.separator + mathExpr + ".txt");
		if (file.exists() && !file.isDirectory()) {

			StringBuilder fileContents = new StringBuilder((int) file.length());
			Scanner scanner = null;
			try {
				scanner = new Scanner(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String lineSeparator = System.getProperty("line.separator");

			try {
				while (scanner.hasNextLine()) {
					fileContents.append(scanner.nextLine() + lineSeparator);
				}
				String answer = new String(fileContents.toString());
				answer = answer.replaceAll("X", "\\*");
				answer = answer.replaceAll("_", "/");
				return answer;
			} finally {
				scanner.close();
			}
		}
		return "mathExpr not found in DB";
	}

}
