package com.datdev.model;

public class UserModel extends AbstractModel<UserModel> {
	private String userName;
	private String passWord;
	private String fullName;
	private int status;
	private Long roleId;
	private RoleModel roleModel = new RoleModel();
	public UserModel(String userName, String passWord, String fullName, int status, Long roleId) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.fullName = fullName;
		this.status = status;
		this.roleId = roleId;
	}
	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public RoleModel getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}
}
