/*
 *   1. 아래의 내용으로 콘솔창에 입력합니다.
 *   형식 - 이름:자바:SQL:Jdbc
 *   
 *   ------------------------------
 *   김기수:98:66:78
 *   이지수:88:56:88
 *   박수리:78:86:58
 *   ------------------------------
 * 
 *   아래와 같은 형식으로 출력하는 프로그램을 작성하시오
 *   
 *   수료 기준 
 *   - 자바 : 70점 이상
 *   - SQL  : 80점 이상
 *   - Jdbc : 80점 이상
 *   
 *   실행시의 결과 출력 형식
 *   
 *   ----------------------------------------
 *   학생정보
 *   ----------------------------------------
 *   김기수 : 총점(242점), 자바(98점-수료), SQL(66점-미수료), Jdbc(78점-미수료)
 *   박수리 : 총점(222점), 자바(78점-수료), SQL(86점-수료), Jdbc(58점-미수료)
 *   이기수 : 총점(232점), 자바(88점-수료), SQL(56점-미수료), Jdbc(88점-수료)
 *   ----------------------------------------
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Student implements Comparable<Student>{
	String name;
	int java;
	int sql;
	int jdbc;
	public Student() {}
	public Student(String name,int java,int sql,int jdbc) {
		this.name = name;
		this.java = java;
		this.sql = sql;
		this.jdbc = jdbc;
	}
	public int sum() {
		return java+sql+jdbc;
	}
	public String isJava() {
		return java>=70?"수료":"미수료";
	}
	public String isSql() {
		return sql>=80?"수료":"미수료";
	}
	public String isJdbc() {
		return jdbc>=80?"수료":"미수료";
	}
	public String toString() {
		return String.format("%s : 총점(%d점), 자바(%d점-%s), SQL(%d점-%s), Jdbc(%d점-%s)",
							name,sum(),java,isJava(),sql,isSql(),jdbc,isJdbc());
	}
	public int compareTo(Student p) {
		Student s = p;
		return this.name.compareTo(s.name);
	}
	//이기수 : 총점(232점), 자바(88점-수료), SQL(56점-미수료), Jdbc(88점-수료)
}
public class Exam02 {
	public static void main(String[] args) throws FileNotFoundException {
		List<Student> list = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		for(int i=0;i<3;i++) {
			String[] arr = sc.nextLine().split(":");
			Student st = new Student(arr[0],Integer.parseInt(arr[1]),
					Integer.parseInt(arr[2]),Integer.parseInt(arr[3]));
			list.add(st);
		}
		Collections.sort(list); // 이름순으로 오름차순 정렬
		
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		sc.close();
	}
}






