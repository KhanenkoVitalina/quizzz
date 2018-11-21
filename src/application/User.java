package application;

public class User {
private String name;
private String password;
private int maxLevel;
public User(String name, String password, int maxLevel) {
	this.name = name;
	this.password = password;
	this.maxLevel = maxLevel;
}
public String getName() {
	return name;
}
public String getPassword() {
	return password;
}
public int getMaxLevel() {
	return maxLevel;
}
public void setMaxLevel(int maxLevel) {
	this.maxLevel = maxLevel;
}
}
