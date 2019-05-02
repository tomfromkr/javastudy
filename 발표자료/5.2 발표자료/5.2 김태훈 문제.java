package GetterAndSetter;

public class Example {
/*
문제 1번 
1. C드라이브에 텍스트 파일 하나를 생성하시오 

2. Scanner 클래스를 이용해 문자를 입력하시오.

결과 -> 이클립스 콘솔창에 입력한 텍스트가 텍스트파일에 저장되면 끝 

 

문제 2번 

1. 자신의 자바 파일이 있는 파일의 경로를 복사하세요
ex> C:\Users\Hoons_PC\eclipse-workspace\Chapter18\src\exam05_System_in_out\SystemInExample.java

2. 해당 소스파일을 읽고 라인 번호를 추가하는 코드를 작성하세요 
단 , FileReader 클래스를 사용할 것 
 
결과는 자신이 원하는 자바 소스에 라인 번호와 코드를 출력하면 됩니다.





//소스 1
package Exam04_Writer_write;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class WriteExample2 {
public static void main(String [] args) throws Exception { 
	Scanner scan= new Scanner(System.in); // Scanner 클래스 생성
	
	Writer writer =new FileWriter("C:/Temp/test.txt"); // test.txt 파일 생성 후 writer클래스의 매개변수로 사용 
	String data =scan.nextLine(); // String 타입의 data변수 생성 후 Scanner 클래스 이용 
	
	writer.write(data); 
	
	writer.flush();
	writer.close();
}
}
//소스 2

package example;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileReaderExample {

	public static void main(String[] args) throws Exception {
		String filePath="C:\\Users\\Hoons_PC\\eclipse-workspace\\Chapter18\\src\\exam05_System_in_out\\SystemInExample1.java"; // 파일 경로 변수 선언 
	
		FileReader fr = new FileReader(filePath); // filePath를 매개값으로 준 FileReader 객체 생성
		BufferedReader br = new BufferedReader(fr);  //FileReader fr을 매개로 한 BufferedReader 객체 생성 
		
		int number =0;  // 라인 번호 변수 
		String data; // 파일 텍스트 복사 변수
		
		while(true) {// while문을 이용해 문자를 읽어들였을 때 null이 아니라면 출력 null이면 while문을 빠져나간다.  
			data=br.readLine();
			if(data==null) break;
			System.out.println(++number+ ":"+ data); // 라인 번호와 파일 텍스트 출력
			}
		br.close();  // BufferedReader를 닫아준다.
		fr.close();  //FileReader를 닫아준다.
	}

}

BufferedReader는 InputStreamReader에 버퍼링 기능을 추가한 것으로 데이터를 사용자가 요청할 때마다 매번 읽어오는 보다 일정량을 한번에 읽어온 후 버퍼에 보관한다.
그리고 사용자가 요구할 때 버퍼에서 읽어온다.*/
}