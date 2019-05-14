package exm18;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerExam {
	ExecutorService executorService;
	ServerSocket serverSocket;
	List<Client> connections = new Vector<Client>();

	void startServer() {
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("172.30.1.31", 5001));
		} catch (Exception e) {
			if (!serverSocket.isClosed()) {
				stopServer();
			}
			return;
		}

		Runnable runnable = new Runnable() {
			@Override
			public void run() {

				System.out.println("[���� ����]");
				while (true) {
					try {
						Socket socket = serverSocket.accept();
						String message = "[���� ����: " + socket.getRemoteSocketAddress() + ": "
								+ Thread.currentThread().getName() + "]";
						System.out.println(message);
						Client client = new Client(socket);
						connections.add(client);
						System.out.println("[���� ����: " + connections.size() + "]");
						
					} catch (Exception e) {
						if (!serverSocket.isClosed()) {
							stopServer();
						}
						break;
					}
				}
			}
		};
		executorService.submit(runnable);
	}

	void stopServer() {
		try {
			Iterator<Client> iterator = connections.iterator();
			while (iterator.hasNext()) {
				Client client = iterator.next();
				client.socket.close();
				iterator.remove();
			}
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			if (executorService != null && !executorService.isShutdown()) {
				executorService.shutdown();
			}
			System.out.println("[���� ����]");
		} catch (Exception e) {
		}
	}

	class Client {
		Socket socket;

		Client(Socket socket) {
			this.socket = socket;
			receive();
		}

		void receive() {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						while (true) {
							byte[] byteArr = new byte[100];
							InputStream inputStream = socket.getInputStream();

							// Ŭ���̾�Ʈ�� ������ ���Ḧ ���� ��� IOException �߻�
							int readByteCount = inputStream.read(byteArr);

							// Ŭ���̾�Ʈ�� ���������� Socket�� close()�� ȣ������ ���
							if (readByteCount == -1) {
								throw new IOException();
							}

							String message = "[��û ó��: " + socket.getRemoteSocketAddress() + ": "
									+ Thread.currentThread().getName() + "]";
							System.out.println(message);
							String data = new String(byteArr, 0, readByteCount, "UTF-8");
							System.out.println(socket.getRemoteSocketAddress() + ": "+data);
							for (Client client : connections) {
								client.send(data);
							}
						}
					} catch (Exception e) {
						try {
							connections.remove(Client.this);
							String message = "[Ŭ���̾�Ʈ ��� �ȵ�: " + socket.getRemoteSocketAddress() + ": "
									+ Thread.currentThread().getName() + "]";
							System.out.println(message);
							socket.close();
						} catch (IOException e2) {
						}
					}
				}
			};
			executorService.submit(runnable);
		}

		void send(String data) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						byte[] byteArr = data.getBytes("UTF-8");
						OutputStream outputStream = socket.getOutputStream();
						String temp="Deposit";
						outputStream.write(temp.getBytes("UTF-8"));
						outputStream.flush();
					} catch (Exception e) {
						try {
							String message = "[Ŭ���̾�Ʈ ��� �ȵ�: " + socket.getRemoteSocketAddress() + ": "
									+ Thread.currentThread().getName() + "]";
							System.out.println(message);
							connections.remove(Client.this);
							socket.close();
						} catch (IOException e2) {
						}
					}
				}
			};
			executorService.submit(runnable);
		}
	}


	public static void main(String[] args) {
		ServerExam serverExam=new ServerExam();
		serverExam.startServer();
	}
}
