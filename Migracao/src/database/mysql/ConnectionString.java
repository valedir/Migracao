package database.mysql;

public class ConnectionString {
	
	private String user;// = "aluno";
	private String password;// = "aluno";
	private String url;// = "jdbc:mysql://194.210.86.10:3306/sid2022?serverTimezone=UTC";
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
