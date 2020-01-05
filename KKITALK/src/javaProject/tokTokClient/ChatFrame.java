package javaProject.tokTokClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatFrame extends JFrame implements ActionListener, Runnable{
	JDialog meinJDialog = new JDialog();
	BorderLayout border = new BorderLayout();
	JPanel mainPanel = new JPanel();
	JPanel chatPanel = new JPanel();
	JPanel setPanel = new JPanel();
	JPanel imagePanel = new JPanel();
	JPanel colorPanel = new JPanel();
	JButton insertBtn = new JButton(new ImageIcon("./src/Img/in.png"));
	JButton logoutBtn = new JButton(new ImageIcon("./src/Img/out.png"));
	JTextField textField;
	JTextArea textArea;
	JTextArea listArea;	
	JLabel listLabel = new JLabel();

	Socket socket;
	Scanner sc;
	PrintWriter pw;
	String msg = "";
	static int count = 1;

	public ChatFrame(Socket socket){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage("./src/Img/frame.png");
        setIconImage(img);
        insertBtn.setSize(70, 60);
        insertBtn.setBorderPainted(false);		// 버튼 테투리 지우기
        insertBtn.setContentAreaFilled(false);	// 버튼 영역 배경 표시삭제
        logoutBtn.setSize(70, 50);
        logoutBtn.setBorderPainted(false);		// 버튼 테투리 지우기
        logoutBtn.setContentAreaFilled(false);	// 버튼 영역 배경 표시삭제
        
        
		this.socket = socket;
		try {
			sc = new Scanner(socket.getInputStream());
			pw = new PrintWriter(socket.getOutputStream());
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		setTitle("채팅창");
		setBounds(700, 150,520, 520);
		setResizable(false);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				if(pw!=null){
					pw.write("bye");
					pw.flush();
				}
				try { if(socket != null) { socket.close(); } } catch (Exception e1) { e1.printStackTrace(); }
				try { if(sc != null) { sc.close(); } } catch (Exception e1) { e1.printStackTrace(); }
				try { if(pw != null) { pw.close(); } } catch (Exception e1) { e1.printStackTrace(); }
				dispose();
			}
			@Override
			public void windowActivated(WindowEvent e) {
				textField.requestFocus();
			}	
		});
		Color color = new Color(255, 209, 0);
		mainPanel.setBackground(color); // 패널색 변경
        meinJDialog.add(mainPanel);
        chatPanel.setBackground(color); // 패널색 변경
        meinJDialog.add(chatPanel);
        setPanel.setBackground(color); // 패널색 변경
        meinJDialog.add(setPanel);
        meinJDialog.setVisible(false);
        
        listLabel.setForeground(Color.BLACK);
		
//		mainPanel.setBackground(Color.YELLOW);
//		chatPanel.setBackground(Color.YELLOW);
//		setPanel.setBackground(Color.YELLOW);
		add(mainPanel);
		chatPanel.setPreferredSize(new Dimension(330, 520));
		setPanel.setPreferredSize(new Dimension(155, 500));
		mainPanel.add(chatPanel,border.CENTER);
		mainPanel.add(setPanel,border.EAST);
		
		textArea = new JTextArea(26, 28);
		textArea.setText("");
		textArea.setBackground(Color.WHITE);
		textArea.setLineWrap(true);
//		가로줄 x 세로줄 O
//		JScrollPane jsp1 = new JScrollPane(textArea,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		JScrollPane jsp1 = new JScrollPane(textArea);
		jsp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textField = new JTextField(17);
		textArea.setEditable(false);
	
		chatPanel.add(jsp1);
		chatPanel.add(textField);
		chatPanel.add(insertBtn);
		insertBtn.addActionListener(this);
		textField.addActionListener(this);
		
		listLabel.setText("채팅 참여자 목록 : "+count);
		setPanel.add(listLabel);
		listArea = new JTextArea(25,13);
		listArea.setEditable(false);
		listArea.setText("");
		JScrollPane jsp2 = new JScrollPane(listArea);
		setPanel.add(jsp2);
		
		logoutBtn.setPreferredSize(new Dimension(145, 26));
		logoutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		setPanel.add(logoutBtn);
		setVisible(true);
	}
	@Override
	public void run() {
		String str = "";
		str = sc.nextLine();
		for(int i=0;i<str.length();i++){
			char tmp = str.charAt(i);
			if(tmp == ',')
				count++;
		}
		listLabel.setText("채팅 참여자 목록 : "+count);
		count = 1;
		str = str.substring(1, str.indexOf("]")).replace(", ", "\n");
		listArea.setText(str);
		while(socket != null) {						// 접속이 유지되고 있는가?
			str = sc.nextLine().trim();
			if(str.length() > 0) {
				if(str.substring(0, 1).equals("[")){
					for(int i=0;i<str.length();i++){
						char tmp = str.charAt(i);
						if(tmp == ',')
							count++;
					}
					listLabel.setText("채팅 참여자 목록 : "+count);
					count = 1;
					str = str.substring(1, str.indexOf("]")).replace(", ", "\n");
					listArea.setText(str);
				}else{
//					if(str.length()>27){
//						for(int i=0;i<str.length()/27;i++){
//							StringBuffer sb = new StringBuffer(str);
//							sb.insert(27*(i+1), "\n");
//							str = sb.toString();
//						}
//						textArea.setText(msg += str+"\n");
//					}else{
					textArea.setText(msg += str+"\n");
//					}
				}
//				서버에서 "bye"가 넘어오면(클라이언트에서 윈도우 창을 닫았으면) 수신을 중지한다.
				if(str.toLowerCase().equals("bye")) {
					break;
				}
//				서버에서 내용이 들어오면 스크롤을 밑으로 내린다.
				textArea.setCaretPosition(textArea.getDocument().getLength());
			}
//		클라이언트 창이 닫혔으므로 메시지를 전송할 수 없도록 textField, sendBtn을 비활성화 시킨다.
		}
		textField.setEnabled(false);
		insertBtn.setEnabled(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = textField.getText().trim();	// textField에 입력된 내용을 받는다.
		if(str.length() > 0) {						// textField에 내용이 입력되었나?
			textField.setText("");					// 다음 메시지를 입력하기 위해 textField를 지운다.
			textField.requestFocus();				// 다음 메시지를 입력하기 위해 포커스를 이동시킨다.
			if(pw != null) {
				pw.write(str + "\n");
				pw.flush();
			}
		}
	}

}