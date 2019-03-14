package study1;

import java.util.Scanner;

public class BankApplication {
	private static Account1[] accountArray = new Account1[100];
	private static Scanner scanner = new Scanner(System.in);
	private static int no = 0;

	public static void main(String[] args) {
		boolean run = true;
		while (run) {
			System.out.println("--------------------------------------------------------");
			System.out.println("1. 계좌생성 | 2. 계좌목록 | 3. 예금 | 4. 출금 | 5.종료");
			System.out.println("--------------------------------------------------------");
			System.out.print("선택>");

			int selectNo = scanner.nextInt();

			if (selectNo == 1) {
				createAccount();
			} else if (selectNo == 2) {
				accountList();
			} else if (selectNo == 3) {
				deposit();
			} else if (selectNo == 4) {
				withdraw();
			} else if (selectNo == 5) {
				run = false;
			}
		}
		System.out.println("프로그램 종료");
	}

	private static void withdraw() {
		System.out.println("-------");
		System.out.println("출금");
		System.out.println("-------");
		String ano = input("계좌번호 : ");
		int withdraw = Integer.parseInt(input("출금액 : "));
		Account1 acc = findAccount(ano);
		if(acc==null) {
			return;
		}
		if (acc.getBalance() - withdraw < 0) {
			acc.setBalance(0);
		} else {
			acc.setBalance(acc.getBalance() - withdraw);
		}
	}

	private static void deposit() {
		System.out.println("-------");
		System.out.println("예금");
		System.out.println("-------");
		String ano = input("계좌번호 : ");
		int deposit = Integer.parseInt(input("예금액 : "));
		
		Account1 acc = findAccount(ano);
		if(acc==null) {
			return;
		}
		acc.setBalance(acc.getBalance() + deposit);

	}

	private static void accountList() {
		System.out.println("-------");
		System.out.println("계좌목록");
		System.out.println("-------");
		if (no == 0) {
			System.out.println("계좌가 존재하지 않습니다.");
			return;
		}
		for (int i=0;i<no;i++) {
			Account1 account = accountArray[i];
			System.out.printf("%s\t%s\t%d%n", account.getAno(), account.getOwner(), account.getBalance());
		}
	}

	private static void createAccount() {
		System.out.println("-------");
		System.out.println("계좌생성");
		System.out.println("-------");
		String ano = input("계좌번호 : ");
		String owner = input("계좌주 : ");
		int balance = Integer.parseInt(input("초기 입금액 : "));
		accountArray[no++] = new Account1(ano, owner, balance);

	}

	private static Account1 findAccount(String ano) {
		boolean flag = true;
		int idx = -1;
		for (int i = 0; i < accountArray.length; i++) {
			if (ano.equals(accountArray[0].getAno())) {
				flag = false;
				idx = i;
				break;
			}
		}
		if (flag) {
			System.out.println("계좌번호가 존재하지 않습니다.");
			return null;
		}
		return accountArray[idx];
	}

	public static String input(String msg) {
		System.out.print(msg);
		return scanner.next();
	}
}
