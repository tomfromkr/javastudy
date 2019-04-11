package collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class Exam1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Player> playerList = new ArrayList<Player>();
		SortedMap<Integer, String> playerMap = new TreeMap<Integer, String>();
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 5; i++) {
			String tempStr = sc.nextLine();
			String name = tempStr.substring(0, tempStr.indexOf(":"));
			int score = Integer.parseInt(tempStr.substring(tempStr.indexOf(":") + 1, tempStr.length()));
			Player player = new Player(name, score);
			playerList.add(player);
		}

		for (Player player : playerList) {
			playerMap.put(player.getScore(), player.getName());
		}
		int num = playerMap.size();
		for (int i = 0; i < num; i++) {
			System.out.println(playerMap.get(playerMap.lastKey()) + "\t ¼±¼ö\t" + playerMap.lastKey() + "Á¡");
			playerMap.remove(playerMap.lastKey());
		}
	}
}
