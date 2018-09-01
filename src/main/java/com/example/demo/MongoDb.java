package com.example.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Database.User;

//@Primary
@Component
public class MongoDb implements DbController {

	MongoDatabase db;
	MongoCollection<Document> collection;
	User user;

	public MongoDb() {
		configUser();
		ConnectDatabase();

	}

	private void configUser() {

		user = User.builder().IpAddress("mongodb://").Port("31932").UserName("shtukelman").Password("S123456").build();

	}

	private void ConnectDatabase() {

		MongoClientURI uri = new MongoClientURI(user.getIpaddress() + user.getUsername() + ":" + user.getPassword()
				+ "@ds1" + user.getPort() + ".mlab.com:" + user.getPort() + "/mathdatabase");
		MongoClient client = new MongoClient(uri);
		db = client.getDatabase("mathdatabase");
		collection = db.getCollection("expr");

	}

	public void PrintAllCollections() {
		for (String str : db.listCollectionNames()) {
			System.out.println(str);
		}
	}

	public void setAttribute(String mathExpr, String result) {
		Document document = new Document();
		document.put(mathExpr, result);
		collection.insertOne(document);
	}

	public String search(String mathExpr) {

		FindIterable<Document> all = collection.find();
		for (Document document : all) {
			if (document.containsKey(mathExpr)) {
				return document.get(mathExpr).toString();
			}
		}
		return "mathExpr not found in DB";
	}
}
