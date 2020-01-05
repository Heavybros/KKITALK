package javaProject.tokTokServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//   멀티 채팅 서버
public class MultiChatServer {

	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(1004);
//         서버가 실행된 상태에서 접속되는 모든 접속을 받아줘야 하므로 무한 루프를 돌린다.
			while(true) {
				Socket socket = serverSocket.accept();
            
//				클라이언트가 접속하면 접속한 클라이언트 한 개마다 각각의 login 스레드를 실행한다.
//				실제 한 클라이언트로부터 login정보나 회원가입 정보를 받아 처리하는 일은 스레드가 처리한다.
//				스레드를 생성할 때 클라이언트가 접속될 때마다 생성되는 소켓을 생성자로 넘겨서 생성한다.
//				그리고 로그인이 완료되면 ChatThread()를 배정한다.
				Thread loginThread = new LoginThread(socket);
				loginThread.start();
//				loginThread.interrupt();    // 스레드 사망
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
      
	}
   
}




