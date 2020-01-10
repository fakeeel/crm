package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.CstService;
import com.briup.crm.bean.SysUser;
import com.briup.crm.service.UserService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/login")
	public String login(String username,String password,HttpSession session) {
		String url="";
		try {
			SysUser user = userService.login(username, password);
			session.setAttribute("user", user);
			url="index";
		} catch (Exception e) {
			session.setAttribute("msg", e.getMessage());
			url="login";
			e.printStackTrace();
		}
		return url;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
	
	@RequestMapping("/findAllUser/{curPage}")
	public String findAllUser(@PathVariable int curPage,HttpSession session) {
		PageInfo<SysUser> userinfo = userService.findAllUser(curPage, 5);
		session.setAttribute("userinfo", userinfo);
		return "system/user";
	}
	
	@RequestMapping("/findUserEqualTo/{curPage}")
	public String findUserEqualTo(@PathVariable int curPage,String type,HttpSession session) {
		PageInfo<SysUser> userinfo = userService.findServiceEqualTo(curPage, 5, type);
		session.setAttribute("userinfo", userinfo);
		return "system/user";
	}
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(SysUser user) {
		userService.saveOrUpdate(user);
		return "保存成功";
	}
	
	@RequestMapping("/findUserById/{usrId}")
	@ResponseBody
	public SysUser findUserById(@PathVariable long usrId) {
		SysUser user = userService.findUserById(usrId);
		return user;
	}
	
	@RequestMapping("/deleteUserById/{usrId}")
	@ResponseBody
	public String deleteUserById(@PathVariable long usrId,HttpSession session) {
		userService.deleteUserById(usrId);
		return "删除成功";
	}
}
