package javaProject.tokTokClient;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JoinFrame  implements ActionListener {
	
	JDialog meinJDialog = new JDialog();
	JFrame frame = new JFrame("끼톡");
	
    // 회원가입 글자
	JPanel headPanel = new JPanel(new BorderLayout()); 
	JLabel headerLabel = new JLabel();     
   
	// 패널 겉판때기
	JPanel controlPanel = new JPanel();   
	JPanel dataPanel = new JPanel();
   
	// 가운데 좌,우 패널
	JPanel fieldPanel = new JPanel(new GridLayout(4,1));
	JPanel labelPanel = new JPanel(new GridLayout(4, 1));
	JPanel buttonPanel = new JPanel();
   
	// 왼쪽 라벨
	JLabel idLabel = new JLabel("아이디:");	
	JLabel nickNameLabel = new JLabel("닉네임:");
	JLabel passwordLabel = new JLabel("비밀번호:");
	JLabel passwordcheckLabel = new JLabel("비밀번호 확인:");
   
	// 오른쪽 입력받는공간
	JTextField idField = new JTextField(10);
	JTextField nickNameField = new JTextField(10);   
	JPasswordField passwordField = new JPasswordField(10);   
	JPasswordField passwordcheckField = new JPasswordField(10);
   
	//버튼
	JButton yesBtn = new JButton(new ImageIcon("./src/Img/join.png"));
	JButton eraseBtn = new JButton(new ImageIcon("./src/Img/out.png"));
   
	private String id ="";
	private String nickName ="";
	private String password ="";
	private boolean activate;
	
	Dimension fullSize = new Dimension();
	Dimension frameSize = new Dimension();
   
	public JoinFrame() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Image img = toolkit.getImage("./src/Img/frame.png");
	    frame.setIconImage(img);
	    
	    yesBtn.setBorderPainted(false);		// 버튼 테투리 지우기
	    yesBtn.setContentAreaFilled(false);	// 버튼 영역 배경 표시삭제
	    eraseBtn.setBorderPainted(false);		// 버튼 테투리 지우기
	    eraseBtn.setContentAreaFilled(false);	// 버튼 영역 배경 표시삭제
		
	    
		frame.setSize(350, 250);
		fullSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameSize = frame.getSize();
		frame.setLocation((fullSize.width-frameSize.width)/2, (fullSize.height-frameSize.width)/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		Color color = new Color(255, 209, 0);
		controlPanel.setBackground(color); // 패널색 변경
	    meinJDialog.add(controlPanel);
	    headPanel.setBackground(color); // 패널색 변경
	    meinJDialog.add(headPanel);
	    labelPanel.setBackground(color); // 패널색 변경
	    meinJDialog.add(labelPanel);
	    fieldPanel.setBackground(color); // 패널색 변경
	    meinJDialog.add(fieldPanel);
	    dataPanel.setBackground(color); // 패널색 변경
	    meinJDialog.add(dataPanel);
	    buttonPanel.setBackground(color); // 패널색 변경
	    meinJDialog.add(buttonPanel);
	    meinJDialog.setVisible(false);
	    
	    idLabel.setForeground(Color.BLACK);
	    nickNameLabel.setForeground(Color.BLACK);
	    passwordLabel.setForeground(Color.BLACK);
	    passwordcheckLabel.setForeground(Color.BLACK);
		
		idLabel = new JLabel("아이디");	   
		nickNameLabel = new JLabel("닉네임");
		passwordLabel = new JLabel("비밀번호");
		passwordcheckLabel = new JLabel("비밀번호 확인");
	      
		idLabel.setBounds(5, 50, 5, 5);
		nickNameLabel.setBounds(5, 80, 5, 5);
		passwordLabel.setBounds(5, 120, 5, 5);
		passwordcheckLabel.setBounds(5, 150, 5, 5);
	      
		idField.setBounds(5, 5, 5, 50);
		nickNameField.setBounds(5,5,5,80);
		passwordField.setBounds(5,5,5,120);
		passwordcheckField.setBounds(5,5,5,150);
	      
		//헤드라인
		headerLabel.setText("회원가입");
		headerLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		headPanel.add(headerLabel);      
		controlPanel.add(headPanel, BorderLayout.NORTH);
	      
		// 왼쪽 추가
		labelPanel.add(idLabel);
		labelPanel.add(nickNameLabel);
		labelPanel.add(passwordLabel);
		labelPanel.add(passwordcheckLabel);
	      
		// 오른쪽 추가
		fieldPanel.add(idField);
		fieldPanel.add(nickNameField);
		fieldPanel.add(passwordField);
		fieldPanel.add(passwordcheckField);
		passwordcheckField.addActionListener(this);
	      
		// 왼쪽 네이밍 배치
		dataPanel.add(labelPanel, BorderLayout.WEST);
		dataPanel.add(fieldPanel);
		controlPanel.add(dataPanel);
	      
		// 버튼 배치
		buttonPanel.add(yesBtn);
		buttonPanel.add(eraseBtn);
		controlPanel.add(buttonPanel, BorderLayout.SOUTH);
		yesBtn.setName("확인");
		eraseBtn.setName("지우기");
		yesBtn.addActionListener(this);
		eraseBtn.addActionListener(this);
	      
		frame.add(controlPanel, BorderLayout.CENTER);
		frame.setResizable(false); // 프레임 크기 고정
		frame.setVisible(true);
		activate = true;
	}
   
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getNickName() {return nickName;}
	public void setNickName(String nickName) {this.nickName = nickName;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public boolean isActivate() {return activate;}
	public void setActivate(boolean activate) {
		this.activate = activate;
		frame.dispose();
	}
	
	public static void main(String[] args) {
		JoinFrame frame = new JoinFrame();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
//		빈칸검사준비
		String id = idField.getText().trim();
		String nickName = nickNameField.getText().trim();	   
		String password = passwordField.getText().trim();
		String passwordcheck = passwordcheckField.getText().trim();
		
//		System.out.println("id : "+this.id+", nick : "+this.nickName+
//				", pass : "+this.password+", passcheck : "+passwordcheck);
		if(e.getModifiers() == 0) {
			submit(id, nickName, password, passwordcheck);
		} else {
			
			switch(((JButton)e.getSource()).getName()) {
			case "확인":
				submit(id, nickName, password, passwordcheck);
				break;
				
			case "지우기": 
				idField.setText("");
				nickNameField.setText("");
				passwordField.setText("");
				passwordcheckField.setText("");
				idField.requestFocus();
				break;
			}
		}
   }

	private void submit(String id, String nickName, String password, String passwordcheck) {
		boolean flag = true;
		for(int i = 0; i< id.length(); i++ ) {
			if(i == 0) {
				boolean atoz = (id.charAt(i) >= 'a' && id.charAt(i)<= 'z');
				if(!(atoz)){
					JOptionPane.showMessageDialog(null,"아이디는 알파벳으로 시작합니다.");
					flag = false;
					idField.setText("");
					break;
				}
			} else {
				boolean atoz = (id.charAt(i) >= 'a' && id.charAt(i)<= 'z');
				boolean oneto9 = (id.charAt(i) >= '0' && id.charAt(i)<= '9');
				if(!(atoz || oneto9)){
					JOptionPane.showMessageDialog(null,"아이디는 알파벳소문자와 숫자로만 가능합니다.");
					flag = false;
					break;
				}
			}
		}
		if(id.length() <= 0) {
			JOptionPane.showMessageDialog(null, "아이디가 입력되지 않았습니다.");
			idField.requestFocus(); flag = false;
		} else if(nickName.length() <= 0) {
			JOptionPane.showMessageDialog(null, "닉네임이 입력되지 않았습니다.");
			nickNameField.requestFocus(); flag = false;
		} else if(password.length() <= 0) {
			JOptionPane.showMessageDialog(null, "비밀번호가 입력되지 않았습니다.");	  
			passwordField.requestFocus(); flag = false;
		} else if(passwordcheck.length() <= 0) {
			JOptionPane.showMessageDialog(null,"비밀번호를 다시 확인해주시길 바랍니다.");
			passwordField.requestFocus(); flag = false;
		} else if(!password.equals(passwordcheck)){
			JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다.");
			passwordField.requestFocus(); flag = false;
		}
		if(flag) {
			this.id = id;
			this.nickName = nickName;
			this.password = password;
			activate = false;
		}
		
	}
   
}