package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.CstService;
import com.briup.crm.bean.SysUser;
import com.briup.crm.service.ServiceService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/service")
public class ServiceController {

	@Autowired
	private ServiceService serviceService;
	
	@RequestMapping("/findService/{curPage}")
	public String findService(@PathVariable int curPage,HttpSession session) {
		PageInfo<CstService> serviceinfo = serviceService.findService(curPage, 5);
		session.setAttribute("serviceinfo", serviceinfo);
		return "service/feedback";
	}
	
	@RequestMapping("/findServiceByUserName/{curPage}")
	public String findServiceByUserName(@PathVariable int curPage,HttpSession session) {
		SysUser user = (SysUser)session.getAttribute("user");
		PageInfo<CstService> serviceinfo = serviceService.findServiceByUserName(curPage, 5, user.getUsrName());
		session.setAttribute("serviceinfo", serviceinfo);
		return "service/service";
	}
	
	@RequestMapping("/findServiceLike/{curPage}")
	public String findServiceLike(@PathVariable int curPage,String custName,String type,HttpSession session) {
		SysUser user = (SysUser)session.getAttribute("user");
		PageInfo<CstService> serviceinfo = serviceService.findServiceLike(curPage, 5, custName, type,user.getUsrName());
		session.setAttribute("serviceinfo", serviceinfo);
		return "service/serviceLike";
	}
	
	@RequestMapping("/findServiceEqualTo/{curPage}")
	public String findServiceEqualTo(@PathVariable int curPage,String type,String status,HttpSession session) {
		PageInfo<CstService> serviceinfo = serviceService.findServiceEqualTo(curPage, 5, type, status);
		session.setAttribute("serviceinfo", serviceinfo);
		return "service/feedback";
	}
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(CstService service) {
		serviceService.saveOrUpdate(service);
		return "保存成功";
	}
	
	@RequestMapping("/findServiceById/{serviceId}")
	@ResponseBody
	public CstService findServiceById(@PathVariable long serviceId) {
		CstService service = serviceService.findServiceById(serviceId);
		return service;	
	}
	
	@RequestMapping("/toServiceDetail2/{serviceId}")
	public String toServiceDetail2(@PathVariable long serviceId,HttpSession session) {
		CstService service = serviceService.findServiceById(serviceId);
		session.setAttribute("service", service);
		return "service/serviceDetail2";
	}

	@RequestMapping("/toServiceDetail/{serviceId}")
	public String toServiceDetail(@PathVariable long serviceId,HttpSession session) {
		CstService service = serviceService.findServiceById(serviceId);
		session.setAttribute("service", service);
		return "service/serviceDetail";
	}
}
