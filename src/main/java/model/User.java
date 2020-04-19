package model;

public class User {
	
private int teamId;
private String teamName;
private String league;
private String email;
private String password;

public User() {}


public User(String email, String password){
	this.email=email;
	this.password=password;	
}



public String getEmail() {
	return this.email;
}
public String getPassword() {
	return this.password;
}
public int getTeamId() {
	return this.teamId;
}

public void setEmail(String email) {
	this.email=email;
}

public void setPassword(String password) {
	this.password=password;
}

public void setTeamId(int teamId) {
	this.teamId=teamId;
}




}
