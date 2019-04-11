import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String sen = sc.nextLine();
		String[] array = sen.split("");
		Arrays.sort(array,new Comparator<String>() {

			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		System.out.println(Arrays.toString(array));
		Arrays.sort(array);
		System.out.println(Arrays.toString(array));
		sc.close();
	}
}
