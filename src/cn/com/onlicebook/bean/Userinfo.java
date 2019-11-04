package cn.com.onlicebook.bean;
/**
 * Userinfo表的实例
 */
public class Userinfo {
	private int userid;//用户id
	private String username;//用户名
	private String password;//密码
	private String email;//邮箱
	private int role;//角色
	private int state;//状态(默认为1，为0时不显示数据)
	/**
	 * 无参构造
	 */
	public Userinfo() {
		super();
	}

	/**
	 * 有参构造
	 * @param userid
	 * @param username
	 * @param password
	 * @param email
	 * @param role
	 */
	public Userinfo(int userid, String username, String password, String email, int role) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	/**
	 * 新增
	 * @param username
	 * @param password
	 * @param email
	 * @param role
	 */
	public Userinfo(String username, String password, String email, int role,int state) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.state = state;
	}

	/**
	 * 有参构造
	 * @param userid
	 * @param username
	 * @param password
	 * @param email
	 * @param role
	 */
	public Userinfo(String username, String password, String email) {
		super();
		
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
}
