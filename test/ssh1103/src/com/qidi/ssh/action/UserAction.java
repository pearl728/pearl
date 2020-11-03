package com.qidi.ssh.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qidi.ssh.pojo.User;
import com.qidi.ssh.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	private User user= new User();
	@Override
	public User getModel() {
		return user;
	}

	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String login() {
		User login = userService.login(user);
		if(login == null) {
			ServletActionContext.getRequest().getSession().setAttribute("user", login);
			this.addActionError("用户名或密码错误");
			return LOGIN;
		}
		return SUCCESS;
	}
	public String registUI() {
		return "registUI";
	}
	public String regist() {
		User login = userService.login(user);
		if(login == null) {
			userService.regist(user);
			return LOGIN;
		}
		this.addActionError("用户名已注册");
		return "registUI";
	}
//	public String userList() {
//		return "userListSuccess";
//	}
}
