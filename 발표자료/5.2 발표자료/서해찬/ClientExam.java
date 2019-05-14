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

					System.out.println("[���� �Ϸ�: " + socket.getRemoteSocketAddress() + "]");
				} catch (Exception e) {

					System.out.println("[���� ��� �ȵ�]");
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
			System.out.println("���� ����");
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

				// ������ ������������ �������� ��� IOException �߻�
				int readByteCount = inputStream.read(byteArr);

				// ������ ���������� Socket�� close()�� ȣ������ ���
				if (readByteCount == -1) {
					throw new IOException();
				}

				String data = new String(byteArr, 0, readByteCount, "UTF-8");
				System.out.println("[�ޱ� �Ϸ�] " + data);
			} catch (Exception e) {
				System.out.println("[���� ��� �ȵ�]");
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
					System.out.println("[������ �Ϸ�]");
				} catch (Exception e) {
					System.out.println("[���� ��� �ȵ�]");
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
