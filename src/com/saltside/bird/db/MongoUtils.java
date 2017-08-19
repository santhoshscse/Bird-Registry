package com.saltside.bird.db;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

class MongoUtils {
	/**
	 * mongo client
	 */
	private static MongoClient mongoclient;

	/**
	 * not exposed constructor
	 */
	private MongoUtils() {

	}

	/**
	 * singleton mongodb instance
	 * 
	 * @return
	 */
	public static MongoClient getInstance() {

		if (mongoclient == null) {
			String mongoUser = "admin";
			String mongoPass = "admin";

			List<MongoCredential> credentials = new ArrayList<MongoCredential>(1);
			credentials.add(MongoCredential.createCredential(mongoUser, "admin", mongoPass.toCharArray()));
			mongoclient = new MongoClient(new ServerAddress("127.0.0.1", 27017));
		}
		return mongoclient;
	}

	/**
	 * get db collection
	 * 
	 * @param collectionName
	 * @return
	 */
	public static MongoCollection<Document> getCollectionByName(String collectionName) {

		MongoClient client = MongoUtils.getInstance();
		MongoDatabase db = client.getDatabase("bird-registry");
		MongoCollection<Document> col = db.getCollection(collectionName);

		return col;
	}

}