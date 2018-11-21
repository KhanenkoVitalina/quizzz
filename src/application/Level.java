package application;

public class Level {
	public  int level;
	public static int numElements;
	public static int playingElements;
	public final int LAST_LEVEL = 5;
	public Level(int level) {
		this.level = level;
		playingElements = getPlayingElementsFromLevel(level);
		numElements = playingElements * playingElements;
	}
	public  int getLevel() {
		return level;
	}
	public  int getPlayingElements() {
		return playingElements;
	}
	public static int getNumElements() {
		return numElements;
	}
	public void setLevel(int level) {
		this.level = level;
		playingElements = getPlayingElementsFromLevel(level);
		numElements = playingElements * playingElements;
	}
	private int getPlayingElementsFromLevel(int level) {
		switch(level) {
		case 1:
			playingElements = 3;
			break;
		case 2:
			playingElements = 4;
			break;
		case 3:
			playingElements = 5;
			break;
		case 4:
			playingElements = 6;
			break;
		case 5:
			playingElements = 7;
			break;
		}
		return playingElements;
	}
	public int getLastLevel() {
		return LAST_LEVEL;
	}
	
}
