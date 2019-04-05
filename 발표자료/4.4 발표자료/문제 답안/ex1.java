package kkkk;

public class ex1 {
	
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	private String name;
	private String grade; 
	private String address;
	private int number;
	
	ex1(String name, String grade , String address, int number){
		this.name = name;
		this.grade = grade;
		this.address = address;
		this.number = number;
	}
	
	ex1(String...strings){
		this.name = strings[0];
		this.grade = strings[1];
		this.address = strings[2];
		this.number = Integer.parseInt(strings[3]);
	}
	
}
