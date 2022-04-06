package migration.main;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.bson.Document;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import database.mongodb.ConnectionString;
import database.mongodb.MongoDB;
import message.mqtt.Publisch;

public class Main {

	public static void main(String[] args) throws ParseException {
		MongoDB mongo = new MongoDB();
		ConnectionString conn = new ConnectionString();
		Publisch publisch = new Publisch();
		publisch.connect();

		/*
		 * conn.setURI("mongodb://localhost:27017"); MongoClient local_client =
		 * mongo.getConnection(conn.getURI()); conn.setDatabse("monitorizacao");
		 * MongoDatabase local_db = mongo.getDatabase(local_client, conn.getDatabse());
		 * conn.setCollection("t1"); MongoCollection<Document> local_collection =
		 * mongo.getCollection(local_db, conn.getCollection()); MongoIterable<Document>
		 * local_document = mongo.getDocument(local_collection); for (Document doc :
		 * local_document) { System.out.println(doc); }
		 */
		
		conn.setURI("mongodb://aluno:aluno@194.210.86.10:27017/admin");
		MongoClient cloud_client = mongo.getConnection(conn.getURI());
		conn.setDatabse("sid2022");
		MongoDatabase cloud_db = mongo.getDatabase(cloud_client, conn.getDatabse());
		conn.setCollection("medicoes");
		MongoCollection<Document> cloud_collection = mongo.getCollection(cloud_db, conn.getCollection());
		MongoIterable<Document> cloud_document = mongo.getDocument(cloud_collection);
		for (Document doc : cloud_document) {
			String zona = doc.getString("Zona");
			String sensor = doc.getString("Sensor");
			String medicao = doc.getString("Medicao");
			String data = doc.getString("Data");
			publisch.sendMessage("Zona:" + zona + " Sensor:" + sensor + " Medicao:" + medicao + " Data:" + data);
		}
		//mongo.sendDocument(cloud_document); 
		 	 		 	
		/*
		 * for (Document doc : cloud_document) { switch (doc.getString("Sensor")) {
		 * 
		 * case "T1": conn.setCollection("t1"); MongoCollection<Document> t1 =
		 * mongo.getCollection(local_db, conn.getCollection()); mongo.insertDocument(t1,
		 * doc); break;
		 * 
		 * case "T2": conn.setCollection("t2"); MongoCollection<Document> t2 =
		 * mongo.getCollection(local_db, conn.getCollection()); mongo.insertDocument(t2,
		 * doc); break;
		 * 
		 * case "H1": conn.setCollection("h1"); MongoCollection<Document> h1 =
		 * mongo.getCollection(local_db, conn.getCollection()); mongo.insertDocument(h1,
		 * doc); break;
		 * 
		 * case "H2": conn.setCollection("h2"); MongoCollection<Document> h2 =
		 * mongo.getCollection(local_db, conn.getCollection()); mongo.insertDocument(h2,
		 * doc); break;
		 * 
		 * case "L1": conn.setCollection("l1"); MongoCollection<Document> l1 =
		 * mongo.getCollection(local_db, conn.getCollection()); mongo.insertDocument(l1,
		 * doc); break;
		 * 
		 * case "L2": conn.setCollection("l2"); MongoCollection<Document> l2 =
		 * mongo.getCollection(local_db, conn.getCollection()); mongo.insertDocument(l2,
		 * doc); break;
		 * 
		 * default: break; } }
		 */
	}

}
