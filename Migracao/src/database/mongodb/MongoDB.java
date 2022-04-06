package database.mongodb;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;

//import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

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
	
	public void sendDocument(MongoIterable<Document> document) throws ParseException {
		for (Document doc : document) {
			String zona = doc.getString("Zona");
			String sensor = doc.getString("Sensor");
			String medicao = doc.getString("Medicao");
			String data = doc.getString("Data");
			if (data == null) {
				System.out.println("Zona:" + zona + " Sensor:" + sensor + " Medicao:" + medicao + " Data:" + data);
			} else {
				String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
				SimpleDateFormat formatter = new SimpleDateFormat(pattern);
				java.util.Date date = formatter.parse(data);
				Timestamp timestamp = new Timestamp(date.getTime());
				System.out.println("Zona:" + zona + " Sensor:" + sensor + " Medicao:" + medicao + " Data:" + timestamp);

			}
		}
	}
	
	/*
	 * public MongoIterable<Document> dateFromString(MongoCollection<Document>
	 * collection) { Bson query = Filters.and(Filters.ne("Data",
	 * "2022-02-31T09:40:03Z"), Filters.ne("Data", null)); Bson projetion =
	 * Projections.fields(Projections.include("Zona"),
	 * Projections.include("Sensor"), Projections.include("Medicao"),
	 * Projections.computed("Data", new Document("$dateFromString", new
	 * Document("dateString", "$Data")))); return
	 * collection.find().projection(projetion); }
	 */
}
