package main;

import java.text.ParseException;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import database.mongodb.ConnectionString;
import database.mongodb.MongoDB;

public class MongoToMongo {
	
	public static void main(String[] args) throws ParseException {
		MongoDB mongo = new MongoDB();
		ConnectionString conn = new ConnectionString();

		conn.setURI("mongodb://aluno:aluno@194.210.86.10:27017/admin");
		MongoClient cloud_client = mongo.getConnection(conn.getURI());
		conn.setDatabse("sid2022");
		MongoDatabase cloud_db = mongo.getDatabase(cloud_client, conn.getDatabse());
		conn.setCollection("medicoes");
		MongoCollection<Document> cloud_collection = mongo.getCollection(cloud_db, conn.getCollection());
		MongoIterable<Document> cloud_document = mongo.getDocument(cloud_collection);

		conn.setURI("mongodb://localhost:27017");
		MongoClient local_client = mongo.getConnection(conn.getURI());
		conn.setDatabse("monitorizacao");
		MongoDatabase local_db = mongo.getDatabase(local_client, conn.getDatabse());
		conn.setCollection("h1");
		MongoCollection<Document> local_collection = mongo.getCollection(local_db, conn.getCollection());
		MongoIterable<Document> local_document = mongo.getDocument(local_collection);
		
		if (local_document.cursor().hasNext()) {
			ArrayList<ObjectId> list = mongo.getList(local_document);
			for (Document doc : cloud_document) {
				ObjectId id = doc.getObjectId("_id");
				if (!list.contains(id)) {
					mongo.insertDocument(local_collection, doc);
					System.out.println(doc);
				}
			}
		} else {
			for (Document document : cloud_document) {
				mongo.insertDocument(local_collection, document);
				System.out.println(document);
			}
		}	 
	}

}
