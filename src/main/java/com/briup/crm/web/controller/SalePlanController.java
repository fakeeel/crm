package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.SalPlan;
import com.briup.crm.bean.SysUser;
import com.briup.crm.bean.extend.SalChanceExtend;
import com.briup.crm.service.PlanService;
import com.briup.crm.service.SalChanceService;
import com.github.pagehelper.PageInfo;


@Controller
@RequestMapping("/plan")
public class SalePlanController {
	
	@Autowired
	private SalChanceService chanceService;
	@Autowired
	private PlanService planService;
	

	@RequestMapping("/findPlansByUserName/{curPage}")
	public String findPlansByUserName(@PathVariable int curPage,HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		PageInfo<SalChance> chanceinfo = chanceService.findChanceByUserName(curPage, 5, user.getUsrName());
		session.setAttribute("chanceinfo",chanceinfo);
		return "sales/plans";
	}
	
	@RequestMapping("/findChanceByUserNameAndRegion/{curPage}")
	public String findChanceByUserNameAndRegion(@PathVariable int curPage,String region,HttpSession session) {
		SysUser user = (SysUser)session.getAttribute("user");
		PageInfo<SalChance> chanceinfo = chanceService.findChanceByUserNameAndRegion(curPage, 5, user.getUsrName(), region);
		session.setAttribute("chanceinfo", chanceinfo);
		return "sales/plans";
	}
	
	@RequestMapping("/toPlanAdd/{chanceId}")
	public String toPlanAdd(@PathVariable long chanceId,HttpSession session) {
		SalChance chance = chanceService.findChanceById(chanceId);
		session.setAttribute("chance", chance);
		session.setAttribute("chanceId", chanceId);
		return "sales/plan_add";
	}
	
	@RequestMapping("/addPlan")
	public String addPlan(SalPlan plan,HttpSession session) {
		Long chanceId = (Long)session.getAttribute("chanceId");
		planService.savePlan(plan, chanceId);
		return "forward:/plan/findPlansByUserName/1";
	}
	
	@RequestMapping("/toPlanEdit/{chanceId}")
	public String toPlanEdit(@PathVariable long chanceId,HttpSession session) {
		SalChanceExtend chanceExtend = chanceService.findChanceWithPlanById(chanceId);
		session.setAttribute("chance", chanceExtend);
		session.setAttribute("chanceId", chanceId);
		return "sales/plan_edit";
	}
	
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(SalPlan plan,HttpSession session) {
		Long chanceId = (Long)session.getAttribute("chanceId");
		plan.setPlaChcId(chanceId);
		planService.saveOrUpdate(plan);
		return "forward:/plan/toPlanEdit/"+chanceId;
	}
	
	@RequestMapping("/findPlanById/{plaId}")
	@ResponseBody
	public SalPlan findPlanById(@PathVariable long plaId) {
		SalPlan plan = planService.findPlanById(plaId);
		return plan;
	}
	@RequestMapping("/deletePlanById/{plaId}")
	public String deletePlanById(@PathVariable long plaId,HttpSession session) {
		planService.deletePlanById(plaId);
		Long chanceId = (Long)session.getAttribute("chanceId");
		return "forward:/plan/toPlanEdit/"+chanceId;
	}
	
	@RequestMapping("/editPlan")
	public String editPlan(SalPlan plan,HttpSession session) {
		Long chanceId = (Long)session.getAttribute("chanceId");
		planService.editPlan(plan, chanceId);
		return "forward:/plan/findPlansByUserName/1";
	}
	
	@RequestMapping("/toPlanDetail/{chanceId}")
	public String toPlanDetail(@PathVariable long chanceId,HttpSession session) {
		SalChanceExtend chanceExtend = chanceService.findChanceWithPlanById(chanceId);
		session.setAttribute("chance", chanceExtend);
		return "sales/plan_detail";
	}
	
}
