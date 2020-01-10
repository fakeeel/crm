package com.briup.crm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.Contribution;
import com.briup.crm.service.ConstituteService;
import com.briup.crm.service.CustomerService;

@Service
public class ConstituteServiceImpl implements ConstituteService{

	@Autowired
	private CustomerService customerService;
	
	@Override
	public List<Contribution> findCustMarkup(int condition) {
		//所有顾客人数
		int count = customerService.findAllCustomer().size();
		List<Contribution> conlist = new ArrayList<Contribution>();
		if(condition == 0) { //按等级
			//获取所有的等级
			Set<String> levels = customerService.findAllLevel();
			for (String level : levels) {
				//获取不同等级对应人数
				int size = customerService.getCustByLevel(level);
				//获得每个等级所占百分比
				//java中两个int型数据相除会自动取整 1/2=0  1.0/2=0.5
				float percent =(float)size/count;
				Contribution contribution = new Contribution();
				contribution.setName(level);
				contribution.setY(percent);
				conlist.add(contribution);
			}
		}else if(condition == 1) { //按信用度
			//获取所有信用度
			Set<Integer> credits = customerService.findAllCredit();
			for (Integer credit : credits) {
				//获得每个信用度对应的顾客数
				int size = customerService.getCustByCredit(credit);
				//获取每个信用度的百分比
				float percent = (float)size/count;
				Contribution contribution = new Contribution();
				contribution.setName(""+credit);
				contribution.setY(percent);
				conlist.add(contribution);
			}
		}else if(condition == 2) { //按满意度
			Set<Integer> satisfies = customerService.findAllSatisfy();
			for (Integer satisfy : satisfies) {
				//获取每个满意度对应的顾客数
				int size = customerService.getCustBySatisfy(satisfy);
				//获得每个满意度所占百分比
				float percent = (float)size/count;
				Contribution contribution = new Contribution();
				contribution.setName(""+satisfy);
				contribution.setY(percent);
				conlist.add(contribution);
			}
		}
		return conlist;
	}

}
