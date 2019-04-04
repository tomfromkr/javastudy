package study4;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	
	private static ArrayList<Student> dataList = new ArrayList<>();
	private static ArrayList<Integer> searchList = new ArrayList<>();
	private static Scanner sc = new Scanner(System.in);
	
	/** dataList 에 있는 거 조회 없을 경우 searchList 에 저장 */
	private static void select() {
		System.out.print("전화번호를 입력하세요 : ");
		int phoneNum = Integer.parseInt(sc.nextLine());
		// 전화번호와 일치하는 학생이 있는지 확인
		boolean flag = true;
		for(Student ele : dataList) {
			if(ele.getPhoneNum() == phoneNum) {
				flag = false;
				System.out.println("해당 데이터를 찾았습니다.");
				System.out.printf("학생이름 : %s 학년 : %s 주소 : %s 전화번호 : %d%n",
									ele.getName(), ele.getGrade(), ele.getAddress(), ele.getPhoneNum());
				return;
			}
		}
		if(flag) {
			System.out.println("해당 데이터를 찾을 수 없습니다.");
			searchList.add(phoneNum);
		}
	}
	/** dataList에 추가!*/
	private static void insert() {
		System.out.println("추가하기(이름, 학년, 주소, 전화번호) : ");
		String sen = sc.nextLine();
		String[] arr = sen.split(" ");
		
		String name = arr[0];
		String grade = arr[1];
		String address = arr[2];
		int phoneNum = Integer.parseInt(arr[3]);
		
		Student student = new Student(name,grade,address,phoneNum);
		
		boolean flag = true;
		for(Student ele : dataList) {
			if(ele.getPhoneNum() == phoneNum) {
				flag = false;
				student = ele; 
				break;
			}
		}
		if(flag) {
			dataList.add(student);
			System.out.println("추가완료");
			return;
		}
		System.out.println("데이터가 이미 존재합니다.");
		
	}
	
	private static String check() {
		System.out.println("-----------------------");
		System.out.println("1. 조회하기");
		System.out.println("2. 추가하기");
		System.out.println("0. 종료하기");
		System.out.println("-----------------------");
		System.out.print("입력 : ");
		return sc.nextLine();
	}
	
	public static void main(String[] args) {
		Thread thread = new Thread(
				()-> {
					while(true) {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						boolean flag = true;
						if(searchList != null) {
							for(int i=0;i<searchList.size();i++) {
								for(Student ele : dataList) {
									if(ele.getPhoneNum() == searchList.get(i)) {
										System.out.println();
										System.out.println("해당 데이터를 찾았습니다.");
										System.out.printf("학생이름 : %s 학년 : %s 주소 : %s 전화번호 : %d%n",
												ele.getName(), ele.getGrade(), ele.getAddress(), ele.getPhoneNum());
										searchList.remove(i);
										break;
									}
								}
							}
						}
					}
				}
		);
		thread.start();
		System.out.println("DB TEAM PROJECT");
		while(true) {
			switch(check()) {
			case "1":
				select();
				break;
			case "2":
				insert();
				break;
			case "0":
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default :
				System.out.println("다시입력해주세요.");
			}
		}
	}
}
