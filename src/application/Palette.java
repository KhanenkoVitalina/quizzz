package application;

public class Palette {
private  static String pink = "#43aec5";
private  static String velvet = "#4a93c9";
private  static String blue = "#6b9ed8";
private  static String darkerBlue = "#8aa1df";
private  static String darkest = "#bcc0f0";
private static String white = "#ffffff";
public static String getRandomBackgroundColor(){
	int random = (int)(Math.random()*5);
	String color;
	switch(random) {
	case 1:
		color = pink;
		break;
	case 2:
		color = velvet;
		break;
	case 3:
		color = blue;
		break;
	case 4:
		color = darkerBlue;
		break;
	case 5:
		color = darkest;
		break;
	default:
		color = pink;
		break;
	}
	return "-fx-background-color: " + color;
}
public static String getWhiteBackground() {
	return "-fx-background-color: " + white;
}
}
