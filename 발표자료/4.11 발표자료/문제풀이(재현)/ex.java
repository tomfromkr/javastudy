package ex15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ex {
	//임의의 문자열을 입력받아서 알파벳 오름차순 순으로 정렬하고 List에 저장/출력하는 프로그램 작성
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.print("문자를 입력하면 자동 정렬됩니다.>");
		//입력받은 문자열
		String value = sc.next();
		
		//문자열의 값 하나씩 저장할 list
		List<Character> list = new ArrayList<>();
		
		for(int i=0; i<value.length(); i++) {
			list.add(value.charAt(i));
		}
		
		//Arrays가 아닌 Colletions을 이용하는 이유는
		//Arrays는 기본형 매개변수만 오고 Colletions는 List, Set, Map이 올수 있기 때문
		
		//오름차순정리
		//Collections.sort(list);
		
		//내림차순 정리
		Collections.sort(list, new AscendingString());
		
		for(char a : list) {
			System.out.print(a+ " ");
		}
		
		
	}

}

class AscendingString implements Comparator<Character> {
	@Override
	public int compare(Character c1, Character c2) { //앞에서 뒤에꺼 빼면 오름차순, 뒤에서 앞에꺼 빼면 내림차순?
		return c1-c2;
	}
}
