package database.mongodb;

public class ConnectionString {
	
	private String URI;
	private String databse;
	private String collection;
	
	public String getURI() {
		return URI;
	}
	
	public void setURI(String uRI) {
		URI = uRI;
	}
	
	public String getDatabse() {
		return databse;
	}
	
	public void setDatabse(String databse) {
		this.databse = databse;
	}
	
	public String getCollection() {
		return collection;
	}
	
	public void setCollection(String collection) {
		this.collection = collection;
	}
	
}
