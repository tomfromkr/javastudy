/*
1. 아래의 내용을 콘솔창에 입력받습니다!
------------------------------
윤성빈:89
이상화:69
이승훈:77
김은정:90
최민정:84
------------------------------

2. 선수 한명의 정보를 표현하는 Player 클래스를 작성합니다.(이름, 스코어)

3. 아래와 같이 출력하는 프로그램을 작성합니다.

출력형식 > 

선수 성적순
선수명	점수
-------------
김은정 선수  90점
윤성빈 선수  89점
최민정 선수  84점
이승훈 선수  77점
이상화 선수  69점
-------------

 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Player {
	private String name;
	private int score;
	public Player(String name,int score) {
		this.name = name;
		this.score = score;
	}
	public String toString() {
		return name+" 선수 "+score+"점";
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
	
}
/** Comparable 이용 참고!*/
//class Player implements Comparable<Player>{
//	private String name;
//	private int score;
//	public Player(String name,int score) {
//		this.name = name;
//		this.score = score;
//	}
//	public String toString() {
//		return name+" 선수 "+score+"점";
//	}
////	@Override
//	// 내림차순으로 정렬
//	public int compareTo(Player p) {
//		Player l = p;
////		return this.score<l.score?1:this.score==l.score?0:-1;
//		return (this.score-l.score) * (-1);
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public int getScore() {
//		return score;
//	}
//	public void setScore(int score) {
//		this.score = score;
//	}
//}
public class Exam01 {
	public static void main(String[] args) throws FileNotFoundException {
		List<Player> list = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		for(int i=0;i<5;i++){
			String player = sc.nextLine();
			String name = player.split(":")[0];
			int score = Integer.parseInt(player.split(":")[1]);
			Player p = new Player(name,score);
			list.add(p);
		}
		
		Collections.sort(list,new Comparator<Player>() {
			public int compare(Player o1, Player o2) {
				return o1.getScore()<o2.getScore()? 1:o1.getScore()==o2.getScore()? 0:-1;
			}
		});//내림차순 정렬
//		Comparable 이용
//		Collections.sort(list);
		System.out.println("선수 성적순");
		System.out.println("선수명\t성적");
		for(int j=0;j<list.size();j++) {
			System.out.println(list.get(j));
		}
		sc.close();
	}
}
