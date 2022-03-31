package database.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Main {

	public static void main(String[] args) {
		MongoDB mongo = new MongoDB();
		ConnectionString conn = new ConnectionString();
		
		MongoClient local_client = mongo.localConnection(new ServerAddress("localhost", 27017));
		conn.setDatabse("monitorizacao");
		MongoDatabase local_db = mongo.getDatabase(local_client, conn.getDatabse());
		
		conn.setURI("mongodb://aluno:aluno@194.210.86.10:27017/admin");
		MongoClient cloud_client = mongo.cloudConnection(conn.getURI());
		conn.setDatabse("sid2022");
		MongoDatabase cloud_db = mongo.getDatabase(cloud_client, conn.getDatabse());
		conn.setCollection("medicoes");
		MongoCollection<Document> cloud_collection = mongo.getCollection(cloud_db, conn.getCollection());
		MongoIterable<Document> cloud_document = mongo.getDocument(cloud_collection);
		for (Document doc : cloud_document) {
			switch (doc.getString("Sensor")) {
			case "T1":
				conn.setCollection("t1");
				MongoCollection<Document> t1 = mongo.getCollection(local_db, conn.getCollection());
				mongo.insertDocument(t1, doc);
				break;
			case "T2":
				conn.setCollection("t2");
				MongoCollection<Document> t2 = mongo.getCollection(local_db, conn.getCollection());
				mongo.insertDocument(t2, doc);
				break;
			case "H1":
				conn.setCollection("h1");
				MongoCollection<Document> h1 = mongo.getCollection(local_db, conn.getCollection());
				mongo.insertDocument(h1, doc);
				break;
			case "H2":
				conn.setCollection("h2");
				MongoCollection<Document> h2 = mongo.getCollection(local_db, conn.getCollection());
				mongo.insertDocument(h2, doc);
				break;
			case "L1":
				conn.setCollection("l1");
				MongoCollection<Document> l1 = mongo.getCollection(local_db, conn.getCollection());
				mongo.insertDocument(l1, doc);
				break;
			case "L2":
				conn.setCollection("l2");
				MongoCollection<Document> l2 = mongo.getCollection(local_db, conn.getCollection());
				mongo.insertDocument(l2, doc);
				break;

			default:
				break;
			}
		}
	}

}
