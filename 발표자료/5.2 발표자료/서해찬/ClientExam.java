package exm18;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientExam {
	Socket socket;

	void startClient() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					socket = new Socket();
					socket.connect(new InetSocketAddress("172.30.1.31", 5001));

					System.out.println("[연결 완료: " + socket.getRemoteSocketAddress() + "]");
				} catch (Exception e) {

					System.out.println("[서버 통신 안됨]");
					if (!socket.isClosed()) {
						stopClient();
					}
					return;
				}
//				receive();
			}
		};
		thread.start();
	}

	void stopClient() {
		try {
			System.out.println("연결 끊음");
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (IOException e) {
		}
	}

	void receive() {
		while (true) {
			try {
				byte[] byteArr = new byte[100];
				InputStream inputStream = socket.getInputStream();

				// 서버가 비정상적으로 종료했을 경우 IOException 발생
				int readByteCount = inputStream.read(byteArr);

				// 서버가 정상적으로 Socket의 close()를 호출했을 경우
				if (readByteCount == -1) {
					throw new IOException();
				}

				String data = new String(byteArr, 0, readByteCount, "UTF-8");
				System.out.println("[받기 완료] " + data);
			} catch (Exception e) {
				System.out.println("[서버 통신 안됨]");
				stopClient();
				break;
			}
		}
	}

	void send(String data) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					byte[] byteArr = data.getBytes("UTF-8");
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write(byteArr);
					outputStream.flush();
					System.out.println("[보내기 완료]");
				} catch (Exception e) {
					System.out.println("[서버 통신 안됨]");
					stopClient();
				}
			}
		};
		thread.start();
	}

	//////////////////////////////////////////////////////

	public static void main(String[] args) {
		ClientExam CE = new ClientExam();
		CE.startClient();
		Scanner sc = new Scanner(System.in);
		while (true) {
			String msg = sc.next();
			CE.send(msg);
			CE.receive();
		}
	}
}
