package com.briup.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.SalPlan;
import com.briup.crm.dao.SalChanceMapper;
import com.briup.crm.dao.SalPlanMapper;
import com.briup.crm.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService{

	@Autowired
	private SalPlanMapper planMapper;
	@Autowired
	private SalChanceMapper chanceMapper;
	
	@Override
	public void savePlan(SalPlan plan,long chanceId) {
		planMapper.insertSelective(plan);
		//根据chanceId查询销售商机
		SalChance chance = chanceMapper.selectByPrimaryKey(chanceId);
		chance.setChcStatus(2);
		//修改状态为正在开发后保存更新
		chanceMapper.updateByPrimaryKey(chance);
	}

	@Override
	public void saveOrUpdate(SalPlan plan) {
		if(plan.getPlaId()==null) {
			planMapper.insertSelective(plan);
		}else {
			planMapper.updateByPrimaryKey(plan);
		}
		
	}

	@Override
	public SalPlan findPlanById(long plaId) {
		SalPlan plan = planMapper.selectByPrimaryKey(plaId);
		return plan;
	}

	@Override
	public void deletePlanById(long plaId) {
		planMapper.deleteByPrimaryKey(plaId);
	}

	@Override
	public void editPlan(SalPlan plan, long chanceId) {
		planMapper.updateByPrimaryKey(plan);
		SalChance chance = chanceMapper.selectByPrimaryKey(chanceId);
		chance.setChcStatus(3);
		//修改状态为开发完成后保存更新
		chanceMapper.updateByPrimaryKey(chance);
	}

	
	
}
