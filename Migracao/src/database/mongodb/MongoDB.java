package database.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class MongoDB {
	
	public MongoClient cloudConnection(String uri) {
		return new MongoClient(new MongoClientURI(uri));
	}

	public MongoClient localConnection(ServerAddress server) {
		return new MongoClient(server);
	}

	public MongoDatabase getDatabase(MongoClient client, String database_name) {
		return client.getDatabase(database_name);
	}

	public MongoCollection<Document> getCollection(MongoDatabase db, String collection_name) {
		return db.getCollection(collection_name);
	}

	public MongoIterable<Document> getDocument(MongoCollection<Document> collection) {
		return collection.find();
	}

	public void insertDocument(MongoCollection<Document> collection, Document document) {
		collection.insertOne(document);
	}

}
