package javaProject.tokTokClient;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.*;

public class PortFrame implements ActionListener,KeyListener{
	JDialog meinJDialog = new JDialog();
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton loginBtn = new JButton(new ImageIcon("./src/Img/port.png"));				// 버튼
//	URL imgPort = getClass().getClassLoader().getResource("port.png");
//	JButton loginBtn = new JButton(new ImageIcon(imgPort));
	JLabel ipLabel = new JLabel(" I  P ");
	JLabel commaLabel1 = new JLabel(".");						// .
	JLabel commaLabel2 = new JLabel(".");						// .
	JLabel commaLabel3 = new JLabel(".");						// .
	JTextField ipField1 = new JTextField(3);				// 1번째 ip필드
	JTextField ipField2 = new JTextField(3);				// 2번째 ip필드
	JTextField ipField3 = new JTextField(3);				// 3번째 ip필드
	JTextField ipField4 = new JTextField(3);				// 4번째 ip필드
	JLabel portLabel = new JLabel("Port");
	JLabel gapLabel = new JLabel("            ");	// 포트와 버튼사이 공백
	JTextField portField = new JTextField(4);			// 포트번호가 입력될 텍스트 필드
	
	private boolean activate;
	String ip;
	int port;
	
	Dimension fullScreen = new Dimension();
	Dimension winScreen = new Dimension();
	
	public PortFrame(){
		frame.setTitle("끼톡");
		
		loginBtn.setBorderPainted(false);		// 버튼 테투리 지우기
		loginBtn.setContentAreaFilled(false);	// 버튼 영역 배경 표시삭제
		
		Color color = new Color(255, 209, 0);
		panel.setBackground(color); // 패널색 변경
        meinJDialog.add(panel);
        meinJDialog.setVisible(false);
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage("./src/Img/frame.png");
        frame.setIconImage(img);
		
		ipField1.setHorizontalAlignment(JTextField.CENTER); 	// 텍스트필드 중앙으로
		ipField2.setHorizontalAlignment(JTextField.CENTER); 	// 텍스트필드 중앙으로
		ipField3.setHorizontalAlignment(JTextField.CENTER); 	// 텍스트필드 중앙으로
		ipField4.setHorizontalAlignment(JTextField.CENTER); 	// 텍스트필드 중앙으로
		portField.setHorizontalAlignment(JTextField.CENTER); 	// 텍스트필드 중앙으로
		
		frame.setSize(230, 95); // 프레임 크기
		fullScreen = Toolkit.getDefaultToolkit().getScreenSize();
		winScreen = frame.getSize();
		frame.setLocation((fullScreen.width-winScreen.width)/2, (fullScreen.height-winScreen.height)/2); // 처음에 뜨는 좌표
		
		ipField1.addKeyListener(this);
		ipField2.addKeyListener(this);
		ipField3.addKeyListener(this);
		ipField4.addKeyListener(this);
		portField.addKeyListener(this);
		
		activate = true;
		portField.addActionListener(this);
		loginBtn.addActionListener(this);
		
		panel.add(ipLabel); // 아이피
		panel.add(ipField1); // 아이피 텍스트필드1
		panel.add(commaLabel1); // .
		panel.add(ipField2); // 아이피 텍스트필드2
		panel.add(commaLabel2); // .
		panel.add(ipField3); // 아이피 텍스트필드3
		panel.add(commaLabel3); // .
		panel.add(ipField4); // 아이피 텍스트필드4
		panel.add(portLabel); // 포트
		panel.add(portField); // 포트 텍스트필드
		panel.add(gapLabel); // 포트와 버튼사이 라벨
		panel.add(loginBtn); // 접속 버튼
		frame.add(panel); 
		
		ipLabel.setForeground(Color.BLACK);
		commaLabel1.setForeground(Color.BLACK);
		commaLabel2.setForeground(Color.BLACK);
		commaLabel3.setForeground(Color.BLACK);
		portLabel.setForeground(Color.BLACK);
		
		frame.setResizable(false); // 프레임 크기 고정
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setVisible(true); 
	}

	public boolean isActivate() {return activate;}
	public void setActivate(boolean activate) {
		this.activate = activate;
		frame.dispose();
	}
	public String getIp() {	return ip;}
	public void setIp(String ip) {this.ip = ip;}
	public int getPort() {return port;}
	public void setPort(int port) {this.port = port;}
	
//	public static void main(String[] args) {
//		PortFrame frame = new PortFrame();
//	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String ip1 = ipField1.getText().trim();
		String ip2 = ipField2.getText().trim();
		String ip3 = ipField3.getText().trim();
		String ip4 = ipField4.getText().trim();
		String portNum = portField.getText().trim();
		if(ip1.length() <= 0 || ip2.length() <= 0 || ip3.length() <= 0 || ip4.length() <= 0) {
			warning("IP");
		} else if(portNum.length() <= 0) {
			warning("PORT");
		} else {
			ip = ip1+"."+ip2+"."+ip3+"."+ip4;
			port = Integer.parseInt(portNum);
//			System.out.println("ip : "+ip+", port : "+port);
			activate = false;
		} 
		
	}

	private void warning(String str) {
		JOptionPane.showMessageDialog(null, str+"가 입력되지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char input = e.getKeyChar();
		JTextField field = (JTextField)e.getSource();
//		ipField에서 숫자만
		if(field.getColumns() == 3) {
			if((input < '0' || input > '9') && input != '\b'){
				e.consume();
			} else if(field.getText().length()>=3) e.consume();
//		portField에서 숫자만
		} else if(field.getColumns() == 4) {
			if((input < '0' || input > '9') && input != '\b'){
				e.consume();
			} else if(field.getText().length()>=4) e.consume();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {	}
	@Override
	public void keyReleased(KeyEvent e) {	}
}








