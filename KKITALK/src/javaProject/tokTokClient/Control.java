package javaProject.tokTokClient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javaProject.tokTokVO.ChatVO;

public class Control {

	static PortFrame portFrame;		// IP/포트번호 입력
	static JoinFrame joinFrame;		// 회원가입
	static LoginFrame loginFrame;	// 로그인
	static ChatFrame chatFrame;
	
	static Socket socket = null;	// 서버와 연결
	static PrintWriter pw = null; 	// 서버에 메세지를 보냄
	static Scanner sc = null;		// 서버로부터 메세지를 받음
	static ObjectOutputStream oos = null;	// 서버로 객체 보내는데 사용할 변수
	
	static String ip = "";
	static int port = 0;
	
	public static void main(String[] args) {
		
		boolean isportOK = false;
		while(!isportOK) {
			
		
//		포트 프레임을 띄워 ip와 port번호를 입력 받는다.
		portFrame = new PortFrame();
		
//		portFrame에서 ip와 port번호가 입력되고 버튼이눌릴때 까지 대기
		while(portFrame.isActivate()) {
			try {Thread.sleep(500);} catch (InterruptedException e1) {e1.printStackTrace();}
		}
		
		ip = portFrame.getIp();
		port = portFrame.getPort();
		
//		채팅창이 띄워질 때까지 무한반복
		boolean isChatOK = false;
		while(!isChatOK) {
		try {
//			통신에 필요한 객체 생성
//			portFrame에서 입력한 ip와 port번호로 접속
			socket = new Socket(ip, port);
			sc = new Scanner(socket.getInputStream());
			pw = new PrintWriter(socket.getOutputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			
//			연결이 완료되면 portFrame을 닫는다
			portFrame.setActivate(false);
			
			
				if(socket != null) {
//					loginFrame을 실행시킨다.
					loginFrame = new LoginFrame();
//				로그인 창에서 버튼이 눌릴때 까지 기다린다.
					while(loginFrame.isActivate()) {
						try {Thread.sleep(500);} catch (InterruptedException e1) {e1.printStackTrace();}
					}
					String str = "";
//				loginFrame에서 회원가입버튼이 눌리면 실행된다.
					if(loginFrame.signal.equals("join")) {
						str = "join\n";
						pw.write(str);
						pw.flush();
						loginFrame.setActivate(false);
						
						while(!sc.nextLine().equals("join")) {	}
						boolean isJoinOK = false;
						while(!isJoinOK) {
							
							joinFrame = new JoinFrame();
							
							while(joinFrame.isActivate()) {
								try {Thread.sleep(500);} catch (InterruptedException e1) {e1.printStackTrace();}
							}
							
							try {
//							JoinFrame 에서 입력된 값으로 객체를 만든다.
//							서버에서 join메세지를 받았음을 확인하는 메세지를 받을 때 까지 대기한다.
								
								ChatVO vo = new ChatVO(joinFrame.getId(), joinFrame.getNickName(), joinFrame.getPassword());
								if(!(vo.getId().equals("") || vo.getNickName().equals("") || vo.getPassword().equals(""))) {
//									회원가입에서 입력된 정보를 vo 객체에 담아 서버로 전송
									oos.writeObject(vo);
									oos.flush();
									
//									가입 여부 메세지를 기다린다.
									str = sc.nextLine().trim();
									if(str.equals("join_ok")) {
										JOptionPane.showMessageDialog(null, "가입이 허가 되었습니다.");
										joinFrame.setActivate(false);
										isJoinOK = true;
									} else {
										JOptionPane.showMessageDialog(null, "가입불가 \n(중복된 사용정보)");
										joinFrame.setActivate(false);
									}
								}
							} catch (IOException e) {e.printStackTrace();}
						}
						
//				loginFrame에서 로그인 버튼이 눌리면 실행된다.
					} else if(loginFrame.signal.equals("login")){
						try {
							ChatVO vo = new ChatVO(loginFrame.id, loginFrame.password);
//						vo에 값이 입력되어 있으므로 서버에 login메세지를 보내고 vo를 보내준다.
							str = "login\n";
							pw.write(str);
							pw.flush();
							
//						서버에서 login을 전송할때 까지 대기
							while(!sc.nextLine().equals("login")) {	}
//						로그인에서 입력된 정보를 vo 객체에 담아 서버로 전송
							oos = new ObjectOutputStream(socket.getOutputStream());
							oos.writeObject(vo);
							oos.flush();
//						서버로부터 로그인 확인 여부 메세지를 받는다.
							str = sc.nextLine();	// 서버로부터 메세지를 입력 받는다.
//						System.out.println("server > "+str);
							if(str.equals("login_accept")) {
								loginFrame.setActivate(false);
//							로그인이 완료되었으므로 채팅창을 띄워준다.
								isChatOK = true;
								isportOK = true;
								chatFrame = new ChatFrame(socket);
								Thread thread = new Thread(chatFrame);
								thread.setDaemon(true);
								thread.start();
							} else if(str.equals("login_not_accept")) {
//						로그인 불가, 암호가 틀렸으므로 메세지를 띄워주고 loginFrame을 다시 켠다.
								JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다", "주의", 
										JOptionPane.WARNING_MESSAGE);
								loginFrame.setActivate(false);
							} else if(str.equals("no_id")) {
//						로그인 불가, 아이디를 찾을 수 없습니다. 메세지를 띄워주고 loginFrame을 다시 켠다.
								JOptionPane.showMessageDialog(null, "아이디를 찾을 수 없습니다.", "주의", 
										JOptionPane.WARNING_MESSAGE);
								loginFrame.setActivate(false);
							}
						} catch (IOException e) {e.printStackTrace();}
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "서버에 접속할 수 없습니다.");
				portFrame.setActivate(false);
				isChatOK = true;
				
			}
		}
	}
	}
}
