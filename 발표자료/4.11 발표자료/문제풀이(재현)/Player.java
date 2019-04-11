package ex15;

public class Player implements Comparable<Player>{
	
	private String name;
	private int score;
	
	public Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public String toString() {
		return name+" 선수  "+score+"점";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public int compareTo(Player p) {
		return p.score-score; // 내림차순
	}
}
