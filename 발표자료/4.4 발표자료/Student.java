package study4;

public class Student {
	private String name;
	private String grade;
	private String address;
	private int phoneNum;
	
	public Student() {
	}
	public Student(String name, String grade, String address, int phoneNum) {
		this.name = name;
		this.grade = grade;
		this.address = address;
		this.phoneNum = phoneNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}
	
}
