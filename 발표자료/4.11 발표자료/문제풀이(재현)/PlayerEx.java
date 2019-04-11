package ex15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class PlayerEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		List list = new ArrayList();
		
		//player 배열에 이름, 점수 한개씩 넣기
		System.out.println("------------");
		
		
		//5번 입력하여 분리하는 로직
		for(int i=0; i<5; i++) {
			String input = sc.next();
			String name = input.split(":")[0];
			int score = Integer.parseInt(input.split(":")[1]);
			list.add(new Player(name,score));
		}
		
		System.out.println("------------");
		
		Collections.sort(list);
		/* 이름 정렬하는 방법(Comparator compare, compareTo사용)
		 * Collections.sort(list, new Comparator<Player>() {
		 * 
		 * @Override public int compare(Player p1, Player p2) { return
		 * p2.getName().compareTo(p1.getName()); } });
		 */
		
		//출력형식
		System.out.println("선수 성적순");
		System.out.println("선수명     점수");
		System.out.println("------------");
		for(int i=0; i<5; i++) {
			System.out.println(list.get(i));
		}

		System.out.println("------------");
		
	}

}
