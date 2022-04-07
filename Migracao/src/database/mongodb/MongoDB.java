package database.mongodb;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class MongoDB {
	
	public MongoClient getConnection(String uri) {
		return MongoClients.create(uri);
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
	
	public ArrayList<ObjectId> getList(MongoIterable<Document> document) {
		ArrayList<ObjectId> list= new ArrayList<ObjectId>();
		for (Document doc : document) {
			ObjectId id = doc.getObjectId("_id");
			list.add(id);
		}
		return list;
	}
	
	/*
	 * public void sendDocument(MongoIterable<Document> document) throws
	 * ParseException { for (Document doc : document) { String zona =
	 * doc.getString("Zona"); String sensor = doc.getString("Sensor"); String
	 * medicao = doc.getString("Medicao"); String data = doc.getString("Data"); if
	 * (data == null) { System.out.println("Zona:" + zona + " Sensor:" + sensor +
	 * " Medicao:" + medicao + " Data:" + data); } else { String pattern =
	 * "yyyy-MM-dd'T'HH:mm:ss'Z'"; SimpleDateFormat formatter = new
	 * SimpleDateFormat(pattern); java.util.Date date = formatter.parse(data);
	 * Timestamp timestamp = new Timestamp(date.getTime());
	 * System.out.println("Zona:" + zona + " Sensor:" + sensor + " Medicao:" +
	 * medicao + " Data:" + timestamp); } } }
	 */
}
