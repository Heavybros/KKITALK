package javaProject.tokTokVO;

import java.io.Serializable;

public class ChatVO implements Serializable{

//	아이디, 닉네임, 비밀번호
	private String id;		// 아이디
	private String nickName;	// 닉네임
	private String password;	// 비밀번호
	

	public ChatVO() {}
	public ChatVO(String id,String password){
		this.id = id;
		this.nickName = "";
		this.password = password;
	}
	public ChatVO(String id,String nickName,String password){
		this.id = id;
		this.nickName = nickName;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "VO [id=" + id + ", nickName=" + nickName + ", password=" + password + "]";
	}
	
}
