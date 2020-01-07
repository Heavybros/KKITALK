package javaProject.tokTokServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javaProject.tokTokVO.ChatVO;

public class LoginThread extends Thread {

	Socket socket = null;
	Scanner sc = null;               // 클라이언트로부터 받는 vo 객체가 join인지 login인지 구분
	PrintWriter pw = null;            // 로그인 승인 메세지나 로그인 불가 메세지를 보낼 때 사용
	ObjectInputStream ois = null;      // 클라이언트로부터 vo객체를 받는다
   
	public LoginThread() {   }
	public LoginThread(Socket socket) {
		this.socket = socket;
		try{
			sc = new Scanner(socket.getInputStream());
			pw = new PrintWriter(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch(IOException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void run() {
		String str = "";            // 클라이언트로부터 받은 메세지를 구분
		if(socket != null) {
			str = sc.nextLine().trim();
//			클라이언트에서 로그인 버튼을누르면 login메세지가 회원가입 버튼을 누르면 join메세지가 전송된다.
			if(str.length() > 0) {
				if(str.equals("join")) {
//					회원가입 진행
					 try {
		                  pw.write("join\n");
		                  pw.flush();
		                  
		                  boolean flag = true;
		                  while(flag) {
		                	  
		                	  Object obj = ois.readObject();
		                	  ChatVO vo = (ChatVO)obj;
//		                  System.out.println(vo.toString());
//		                  vo객체에서 id,nickName,password를 가져와 중복되는 id가 있는지 검사한다.
//		                  중복되는 id가 없을 경우 가입을 승인한다.
		                	  boolean result = ChatDAO.idCompare(vo.getId(), vo.getNickName());
		                	  if(result) {
		                		  ChatDAO.join(vo.getId(), vo.getNickName(), vo.getPassword());
		                		  pw.write("join_ok\n");
		                		  pw.flush();
		                	  } else {
		                		  pw.write("join_not_accept\n");
		                		  pw.flush();
		                	  } 
		                  }
		                  
		                  
		               } catch (ClassNotFoundException e) {
		                  e.printStackTrace();
		               } catch (IOException e) {
		                  e.printStackTrace();
		               }
					
				} else if (str.equals("login")) {
//					로그인을 위하여 클라이언트로부터 id와 비밀번호가 저장된 vo 객체를 받는다.
					try {
						pw.write("login\n");
						pw.flush();
						ois = new ObjectInputStream(socket.getInputStream());
						Object obj = ois.readObject();
//						ChatVO vo = (ChatVO)obj;			// 패키지가 다르면 형변환이 안됌
						ChatVO vo = (ChatVO)obj;
//        				vo객체에서 id와 비밀번호를 가져와 id와 비밀번호로 비밀번호가 일치하는지 확인하는 DAO파일의
//      	   			SQL문 실행(일치하면 1, 일치하지 않으면 2를, 아이디가 없으면 3을 리턴)
						int result = ChatDAO.login(vo.getId(),vo.getPassword());
						if(result == 1) {
//           				로그인이 성공하였음으로 로그인 성공 메세지를 보내주고 ChatThread 실행
//           				ChatThread에서 로그온 스레드를 종료시킨다.
							pw.write("login_accept\n");
							pw.flush();
							Thread chatThread = new ChatThread(socket,vo.getId());
							chatThread.setDaemon(true);
							chatThread.start();
							this.interrupt();
						} else if(result == 2) {
//            				불일치, 로그인에 실패하였으므로 실패했다는 메세지를 보내준다
							pw.write("login_not_accept\n");
							pw.flush();
						} else if(result == 3) {
//							아이디가 없으므로 3이 리턴됨
							pw.write("no_id\n");
							pw.flush();
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
		} else {
//			System.out.println("socket == null");
		}
	}
   
}