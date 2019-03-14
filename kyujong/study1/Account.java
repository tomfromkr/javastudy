package study1;

public class Account {
	private int balance = 0;

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		if(balance < 0 || balance > 1000000) {
			System.out.println("잔고는 음수값이 될 수 없습니다.");
			return;
		}
		this.balance = balance;
	}
}
