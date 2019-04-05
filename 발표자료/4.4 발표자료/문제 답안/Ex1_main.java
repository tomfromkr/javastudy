package kkkk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex1_main {
	static HashMap<Integer, ex1> list = new HashMap<Integer, ex1>();
	static HashSet<Integer> list_search = new HashSet<Integer>();
	
	public static void main(String[] args) {
		System.out.println("DB TERM PRJECT");
		System.out.println("1: 조회하기");
		System.out.println("2: 추가하기");
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.print("입력: ");

			int number = sc.nextInt();
			
			Runnable runnable = () -> {
				//createUser.getList();
				try {
					Thread.sleep(10000);
					if(list != null && list_search != null) {
						Iterator<Integer> it = list_search.iterator(); // iterator 생성
						
						while(it.hasNext()){
							int check = it.next();
							if(list.containsKey(check)) {
								System.out.println("해당 데이터를 찾았습니다");
								System.out.print("학생 이름: "+ list.get(check).getName()+"  ");
								System.out.print("학년: "+ list.get(check).getGrade()+"  ");
								System.out.print("주소: "+ list.get(check).getAddress()+"  ");
								System.out.println("전화번호: "+ list.get(check).getNumber());	
								list_search.remove(check);
							}
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
			Thread thread = new Thread(runnable);
			thread.start();
			sc.nextLine();
			if(number == 1) {
				// 조회하기
				// 전화번호 입력 받기
				int input = sc.nextInt();
				
				// 키 값 찾기
				if(list.containsKey(input)) {
					System.out.println("해당 데이터를 찾았습니다");
					System.out.print("학생 이름: "+ list.get(input).getName()+"  ");
					System.out.print("학년: "+ list.get(input).getGrade()+"  ");
					System.out.print("주소: "+ list.get(input).getAddress()+"  ");
					System.out.println("전화번호: "+ list.get(input).getNumber());
				}else {
					System.out.println("해당 데이터를 찾을 수 없습니다");
					list_search.add(input);
				}
				
			}
			
			else if (number == 2) {
				// 추가하기 
				// 학생 정보 입력
				System.out.print("추가하기: ");
				String input = sc.nextLine();
				StringTokenizer st = new StringTokenizer(input);
				String student[] = new String[4];
				int i = 0;
				while(st.hasMoreTokens()) {
					student[i] = (String)st.nextToken();
					i++;
				}
				
				// 출력 확인
				//i = 0;
				//for(; i < student.length; i++)
					//System.out.println(student[i]);
				
				// key값 조사 
				if(list.containsKey(Integer.parseInt(student[3]))) {
					System.out.println("데이터가 이미 존재 합니다");
					continue;
				}
				
				// student 객체 생성
				ex1 student_info = new ex1(student);
				// list 추가 
				list.put(Integer.parseInt(student[3]), student_info); // string 객체를 Integer wrapper클래스로 변환
				
				// 추가 확인 하기 
				// System.out.println(list.get(Integer.parseInt(student[3])).getName());
				System.out.println("추가 완료");
			}
			
			else {
				System.out.println("Bye Bye~~");
				break;
			}
		}
	}
}
