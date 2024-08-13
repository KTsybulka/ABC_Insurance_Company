package model;

public class M_93_Users {
	private int userid;
	private String username, password, name, cellphone, email, address, role;
	
	public M_93_Users(int userid, String username, String password, String name, String cellphone, String email,
			String address, String role) {
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.cellphone = cellphone;
		this.email = email;
		this.address = address;
		this.role = role;
	}
	
	public M_93_Users(String username, String password, String name, String cellphone, String email, String address,
			String role) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.cellphone = cellphone;
		this.email = email;
		this.address = address;
		this.role = role;
	}
		
	public M_93_Users() {
		this.userid = 0;
		this.username = "";
		this.password = "";
		this.name = "";
		this.cellphone = "";
		this.email = "";
		this.address = "";
		this.role = "";
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Users [userid=" + userid + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", cellphone=" + cellphone + ", email=" + email + ", address=" + address + ", role=" + role + "]";
	}
	
	
	
	
}
