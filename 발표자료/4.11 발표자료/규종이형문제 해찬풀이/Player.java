package collection;

public class Player {
	Player(String name, int score) {
		this.name = name;
		this.score = score;
	}

	Player() {

	}

	String name;
	int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
