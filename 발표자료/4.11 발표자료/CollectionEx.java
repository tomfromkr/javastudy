package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CollectionEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		List<String> strList = new ArrayList<String>();
		String str = sc.nextLine();
		for (int i = 0; i < str.length(); i++) {
			strList.add(str.substring(i, i + 1));
		}
//		오름차순 정렬
		Collections.sort(strList);
//		내림차순 정렬
//		Collections.sort(strList, new AscendingString());
		for (String temp : strList) {
			System.out.print(temp + " ");
		}
	}

}

class AscendingString implements Comparator<String> {
	@Override
	public int compare(String a, String b) {
		return b.compareTo(a);
	}
}
