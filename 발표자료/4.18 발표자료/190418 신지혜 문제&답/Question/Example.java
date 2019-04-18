package Question;

/*
2019년 4월 18일 _ 16장: 스트림과 병렬 처리 문제
(시간이 남는다면, 각 문제에 사용된 파이프라인의 처리 과정을 작성해주세요.)

1.
문자열의 단어를 정렬하세요! 단, 소문자로 변경한 후 중복을 제거하시오.

String[] str = {
"He who makes no mistakes makes nothing",
"He that promises too much means nothing",
};

2. 
3학년 학생들의 점수 평균을 내고, 1등부터 나열해주세요!

<조건>
1) student 클래스를 생성하고,
public static int Fresh=1;
public static int Sopho=2;
public static int Junior=3;
public static int Senior=4;
를 정의해주세요.
2) student 클래스의 필드는 이름, 학년, 등수 입니다.
3) 3학년 학생들을 묶어 새로운 List를 만든 후 평균을 내고, 정렬해주세요.

new Student("가나", Students.Fresh, 100)
new Student("다라", Students.Junior, 88)
new Student("마바", Students.Junior, 15)
new Student("사아", Students.Junior, 99)
new Student("자차", Students.Sopho, 49)
new Student("카타", Students.Junior, 20)
new Student("파하", Students.Senior, 70)
*/

/*스트림은 컬렉션의 저장 요소를 하나씩 참조해서 람다식으로 처리할 수 있도록 해주는 반복자이다!*/

/*
 * 스트림을 생성하는 기본적인 방법 
 * collection.stream()
 * stream.of()
 * arrays.stream(배열)
 * */
import java.util.*;
import java.util.stream.*;

public class Example {
	public static void main(String[] args) {
		// 1번
		String[] str = { "He who makes no mistakes makes nothing", "He that promises too much means nothing", };

		Stream<String> ls = Arrays.stream(str);
		ls.flatMap(l -> Stream.of(l.split(" ")))//string의 요소를 각 문자, 즉 복수 개의 요소로 구성된 스트림으로 리턴한다.
												//stream.of는 stream을 직접 생성하는 방법이다.
				.map(String::toLowerCase)//요소를 대체하는 요소인 스트림을 리턴
				.distinct()//중복 제거
				.sorted()//정렬은 중간처리 스트림, 오름차순으로 정렬된 스트림을 리턴한다.
				.forEach(System.out::println);//forEach는 요소 전체를 반복하는 최종 처리 메소드
		System.out.println();

		// 2번
		List<Student> sl = Arrays.asList(new Student("가나", Student.Fresh, 100),
										new Student("다라", Student.Junior, 88), 
										new Student("마바", Student.Junior, 15),
										new Student("사아", Student.Junior, 99), 
										new Student("자차", Student.Sopho, 49),
										new Student("카타", Student.Junior, 20), 
										new Student("파하", Student.Senior, 70));

		// 파이프라인(여러 개의 스트림이 연결되어 있는 구조)
		// 오리지널 스트림에서, 중간 처리 스트림들과 최종 처리한 결과물들로 이어져 있는 구조!
		List<Student> jList = sl.stream()//original stream
				.filter(s -> s.getGrade() == Student.Junior)//필터링은 중간처리 스트림, 3학년 객체를 요소로 하는 스트림을 리턴한다.
				.sorted(Comparator.reverseOrder())//정렬은 중간처리 스트림, 내림차순으로 정렬된 스트림을 리턴한다.
				.collect(Collectors.toList());//최종 처리 메소드 collect(). 필요한 요소(3학년)만 컬렉션으로 가져온다.

		double avgScore = jList.stream()//original stream
							.mapToInt(Student::getScore)//매핑은 중간처리 스트림, student객체를 score값으로 매핑해서 새로운 스트림을 리턴한다.
							.average()//평균값은 최종 처리
							.getAsDouble();//average의 리턴 값은 original double이기 때문에 getasdouble메소드를 호출해서 저장된 평균 값을 읽는다.

		System.out.println("3학년 친구들의 평균 점수: " + avgScore);

		System.out.println("3학년 친구들의 등수는 다음과 같습니다.");
		jList.stream()
			.forEach(s -> System.out.println(s));//출력할 때 tostring을 재정의하였다.
	}
}

class Student implements Comparable<Student> {
	public static int Fresh = 1;
	public static int Sopho = 2;
	public static int Junior = 3;
	public static int Senior = 4;

	private String name;
	private int grade;
	private int score;

	Student(String name, int grade, int score) {
		this.name = name;
		this.grade = grade;
		this.score = score;
	}

	String getName() {
		return name;
	}

	int getGrade() {
		return grade;
	}

	int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return name + " (" + score + ")";
	}

	@Override
	public int compareTo(Student s) {
		return Integer.compare(score, s.getScore());
	}
}