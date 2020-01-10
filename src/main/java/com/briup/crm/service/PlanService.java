package com.briup.crm.service;

import com.briup.crm.bean.SalPlan;

public interface PlanService {
	
	public void savePlan(SalPlan plan,long chanceId);
	
	public void saveOrUpdate(SalPlan plan);
	
	public SalPlan findPlanById(long plaId);
	
	public void deletePlanById(long plaId);
	
	public void editPlan(SalPlan plan,long chanceId);
	
}
