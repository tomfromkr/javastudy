package example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
	
	//list는 현재 데이터가 존재하지않는 일시적 학생의 정보를 모아두는 곳 
	//ArrayList를 쓰지않고 Hashmap을 쓰는 이유는 ArrayList는 중복하여 저장해서 list에 중복값이 저장되어 쓰레드가 조회한만큼 출력하게된다.
	//반면에 Set은 이미 저장되어 있는 값을 중복하여 저장하지 않으므로 여러번 조회해도 한번만 출력하게된다.
	private static HashSet<Integer> list = new HashSet();
	//data는 정보가 일치하는지 확인하는 데이터셋
	private static HashMap<Integer, Student> data = new HashMap();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("1번 조회, 2번 추가");
		//메뉴 번호 변수
		int num;
		
		while(true) {
			System.out.print("입력: ");
			
			num = sc.nextInt();
			
			//쓰레드 생성(람다식이용)
			Thread thread = new Thread( () -> {
				try {
					Thread.sleep(10000);
					
					if(list != null && data != null) {
						
						//Iterator를 이용하여 여러번 출력 방지
						Iterator<Integer> it = list.iterator();
						
						while(it.hasNext()) {
							int check = it.next();
							if(data.containsKey(check)) {
								System.out.println("해당 데이터를 찾았습니다.");
								System.out.println("학생이름: "+data.get(check).getName());
								System.out.println("학년: "+data.get(check).getGrade());
								System.out.println("주소: "+data.get(check).getAddress());
								System.out.println("전화번호: "+data.get(check).getTelno());
								list.remove(check);
							}
						}
						
					} //if end
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
					
			);
			
			//쓰레드 시작
			thread.start();
			
			
			// 1. 조회하기	
			if(num == 1) {
				
				//전화번호 입력
				System.out.print("전화번호를 입력하세요: ");
				
				
				int telno = sc.nextInt();
				
				
				//입력한 전화번호 값이 존재하면 그 값에 대한 정보 출력
				if(data.containsKey(telno)) {
					System.out.println("해당 데이터를 찾았습니다.");
					System.out.println("학생이름: "+data.get(telno).getName());
					System.out.println("학년: "+data.get(telno).getGrade());
					System.out.println("주소: "+data.get(telno).getAddress());
					System.out.println("전화번호: "+data.get(telno).getTelno());
				}
				
				else {
					System.out.println("해당 데이터를 찾을 수 없습니다.");
					list.add(telno);
					continue;
				}
				
				
				
			}
			
			
			
			//2. 추가하기
			else if (num == 2) {
				System.out.print("이름, 학년, 주소, 전화번호: ");
				String name = sc.next();
				String grade = sc.next();
				String address = sc.next();
				int telno = sc.nextInt();
				
				Student student = new Student();
				student.setName(name);
				student.setGrade(grade);
				student.setAddress(address);
				student.setTelno(telno);
				
				
				//data변수 HashMap
				//hashmap에 같은 전화번호가 있다면 다시 커멘드창 
				if(data.containsKey(telno)) {
					System.out.println("데이터가 이미 존재 합니다.");
					continue;
				}
				
				//HashMap에 데이터 넣기
				data.put((Integer)telno, student);
				
				
				System.out.println("추가완료");
			}
			
			//1이아니거나 2가 아니면 시스템종료
			else {
				System.out.println("Bye Bye~~");
				break;
			}
			
		}
	}

}
