package javaProject.tokTokClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
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

public class LoginFrame  implements ActionListener{
	Image img = new ImageIcon().getImage();
	ImageIcon image1;
	
	JDialog meinJDialog = new JDialog();
	JFrame frame = new JFrame("끼톡 ");
	
	JPanel controlPanel = new JPanel(new BorderLayout());		//전체패널

	JPanel headPanel = new JPanel(new GridLayout(1, 2));		//틱톡패널
	JLabel headerLabel = new JLabel();							//틱톡라벨
	JPanel headPanel2 = new JPanel(new GridLayout(1, 2)); 		//틱톡패널
	JLabel headerLabel2 = new JLabel();
	
	JPanel emptyPanel1 = new JPanel();							// 윗공백을위한 패널
	JLabel emptyLabel1 = new JLabel();							// 윗공백을위한 라벨
   
	JPanel emptyPanel2 = new JPanel();							// 우측공백을위한 패널
	JLabel emptyLabel2 = new JLabel();							// 우측공백을위한 라벨
   
	JPanel idPanel = new JPanel(new BorderLayout());			//아이디패널
	JPanel pwPanel = new JPanel(new BorderLayout());			//패스워드패널
	JPanel dataPanel = new JPanel();							//아이디와 패스워드를 배치할 패널
   
	JPanel fieldPanel = new JPanel(new GridLayout(2, 1));		// 텍스트필드를 배치할 패널
	JPanel buttonPanel = new JPanel();							// 버튼을 패치할 패널
	JPanel labelPanel = new JPanel(new GridLayout(2, 1));		// 라벨을 배치할 패널
   
	JTextField idField = new JTextField(12);					// 아이디필드의 크기
	JPasswordField pwField = new JPasswordField(12);			// 패스워드필드의 크기
   
	JLabel idLabel = new JLabel("ID:");							// 아이디 라벨
	JLabel pwLabel = new JLabel("PW:");							// 패스워드 라벨
   
	JButton loginBtn   = new JButton(new ImageIcon("./src/Img/login.png"));					// 로그인버튼
	JButton joinBtn   = new JButton(new ImageIcon("./src/Img/join.png"));				// 회원가입 버튼
   
//	JFrame frame = new JFrame("Login");							// 프레임 이름
	Dimension fullSize = new Dimension();
	Dimension frameSize = new Dimension();
   
	String id = "";
	String password = "";
	String signal = "";
	private boolean activate;

	public LoginFrame(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./src/Img/frame.png");
		frame.setIconImage(img);
	      
		loginBtn.setBorderPainted(false);		// 버튼 테투리 지우기
		loginBtn.setContentAreaFilled(false);	// 버튼 영역 배경 표시삭제
	      
		joinBtn.setBorderPainted(false);		// 버튼 테투리 지우기
		joinBtn.setContentAreaFilled(false);	// 버튼 영역 배경 표시삭제
		
		idLabel.setForeground(Color.BLACK);
		pwLabel.setForeground(Color.BLACK);
		headerLabel.setForeground(Color.BLACK);
	      
//		frame.setSize(380, 200);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    fullSize = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setBounds(380,170,250,220);
		frameSize.setSize(frame.getSize());
		frame.setLocation((fullSize.width-frameSize.width)/2, (fullSize.height-frameSize.height)/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
		Color color = new Color(255, 209, 0);
		  headPanel.setBackground(color); // 패널색 변경
	      meinJDialog.add(headPanel);
	      headPanel2.setBackground(color); // 패널색 변경
	      meinJDialog.add(headPanel2);
	      emptyPanel1.setBackground(color); // 패널색 변경
	      meinJDialog.add(emptyPanel1);
	      emptyPanel2.setBackground(color); // 패널색 변경
	      meinJDialog.add(emptyPanel2);
	      idPanel.setBackground(color); // 패널색 변경
	      meinJDialog.add(idPanel);
	      pwPanel.setBackground(color); // 패널색 변경
	      meinJDialog.add(pwPanel);
	      dataPanel.setBackground(color); // 패널색 변경
	      meinJDialog.add(dataPanel);
	      fieldPanel.setBackground(color); // 패널색 변경
	      meinJDialog.add(fieldPanel);
	      buttonPanel.setBackground(color); // 패널색 변경
	      meinJDialog.add(buttonPanel);
	      labelPanel.setBackground(color); // 패널색 변경
	      meinJDialog.add(labelPanel);
	      meinJDialog.setVisible(false);
		
	      headerLabel.setPreferredSize(new Dimension(15,15));
//		headerLabel.setText("    TokTok");
//		headerLabel.setFont(new Font("Dialog", Font.BOLD, 40));
      
		image1 = new ImageIcon("./src/Img/H.png");  //이미지 경로
	      
	      headerLabel = new JLabel("",image1,JLabel.CENTER);
	      headerLabel.setVerticalTextPosition(JLabel.CENTER);
	      headerLabel.setHorizontalTextPosition(JLabel.RIGHT);
	      
		
	      headerLabel2.setPreferredSize(new Dimension(15,15));
	      //   headerLabel.setText("    TokTok");
//	          headerLabel.setFont(new Font("Dialog", Font.BOLD, 40));
	          
	          image1 = new ImageIcon("./src/Img/H3.png");  //이미지 경로
	          
	          headerLabel2 = new JLabel("",image1,JLabel.CENTER);
	          headerLabel2.setVerticalTextPosition(JLabel.CENTER);
	          headerLabel2.setHorizontalTextPosition(JLabel.RIGHT);
	           
	           
	           headPanel.add(headerLabel);
	           controlPanel.add(headPanel, BorderLayout.NORTH);
	           
	           headPanel2.add(headerLabel2);
	           controlPanel.add(headPanel2, BorderLayout.NORTH);
      
		emptyLabel1.setText("");
		emptyPanel1.add(emptyLabel1);
		headPanel.add(emptyPanel1, BorderLayout.SOUTH);
      
		labelPanel.setPreferredSize(new Dimension(30, 40));
		idLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		pwLabel.setFont(new Font("Dialog", Font.BOLD, 15));
      
		labelPanel.add(idLabel);
		labelPanel.add(pwLabel);
      
		fieldPanel.add(idField);
		fieldPanel.add(pwField);
      
//		emptyLabel2.setText("     ");
      
//		emptyPanel2.add(emptyLabel2);
//		dataPanel.add(emptyPanel2);
      
		dataPanel.setPreferredSize(new Dimension(30, 30));
		dataPanel.add(labelPanel, BorderLayout.WEST);
		dataPanel.add(fieldPanel);
		dataPanel.add(emptyPanel2);
		controlPanel.add(dataPanel);
  
		buttonPanel.add(loginBtn);
		buttonPanel.add(joinBtn);
		controlPanel.add(buttonPanel, BorderLayout.SOUTH);
   
		frame.add(controlPanel, BorderLayout.CENTER);

		loginBtn.setName("로그인");
		joinBtn.setName("회원가입");
		pwField.setName("로그인");
		
		pwField.addActionListener(this);
		loginBtn.addActionListener(this);
		joinBtn.addActionListener(this);
      
		frame.setResizable(false); // 프레임 크기 고정
		frame.setVisible(true);
		activate = true;
	}
	
   	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public boolean isActivate() {return activate;}
	public void setActivate(boolean activate) {
		this.activate = activate;
		frame.dispose();
	}
//	public static void main(String[] args) {
//		LoginFrame frame = new LoginFrame();
//	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getModifiers() == 0) {
			login();
		} else {
			switch(((JButton)e.getSource()).getName()) {
			case "로그인" :
				login();
				break;
			case "회원가입":
				signal = "join";
				setId("");
				setPassword("");
				activate = false;
				break;
			}
		}
	}

	private void login() {
		signal = "login";
		id = idField.getText().trim();
		password = pwField.getText().trim();
//		for(int i = 0; i< id.length(); i++ ){
//			if((id.charAt(i) >= 'a' && id.charAt(i)<= 'z')) {
//			} else if((id.charAt(i) >= 'A' && id.charAt(i)<= 'Z')) {
//			} else if((id.charAt(i) >= '0' && id.charAt(i)<= '9')) {
//			} else {
//				JOptionPane.showMessageDialog(null, "id는 영문이나 숫자만 가능합니다.", "주의", JOptionPane.WARNING_MESSAGE);
//				break;
//			}
//		}
		if(id.length()<=0){
			JOptionPane.showMessageDialog(null, "ID가 입력되지 않았습니다.");
		} else if (password.length() <= 0) {
			JOptionPane.showMessageDialog(null, "비밀번호가 입력되지 않았습니다.");
		} else {
			setId(idField.getText().trim());
			setPassword(pwField.getText().trim());
			activate = false;
		}
	}
}