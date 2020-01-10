package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.CstService;
import com.briup.crm.bean.CstServiceExample;
import com.briup.crm.dao.CstServiceMapper;
import com.briup.crm.service.ServiceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ServiceServiceImpl implements ServiceService {

	@Autowired
	private CstServiceMapper serviceMapper;

	@Override
	public PageInfo<CstService> findService(int curPage, int size) {
		PageHelper.startPage(curPage, size);
		CstServiceExample example = new CstServiceExample();
		List<CstService> servicelist = serviceMapper.selectByExample(example);
		PageInfo<CstService> serviceinfo = new PageInfo<CstService>(servicelist);
		return serviceinfo;
	}

	@Override
	public PageInfo<CstService> findServiceByUserName(int curPage, int size, String userName) {
		PageHelper.startPage(curPage, size);
		CstServiceExample example = new CstServiceExample();
		example.createCriteria().andSvrDisposeEqualTo(userName);
		List<CstService> servicelist = serviceMapper.selectByExample(example);
		PageInfo<CstService> serviceinfo = new PageInfo<CstService>(servicelist);
		return serviceinfo;
	}
	
	@Override
	public PageInfo<CstService> findServiceLike(int curPage, int size, String custName, String type,String userName) {
		CstServiceExample example = new CstServiceExample();
		example.createCriteria().andSvrCustNameLike("%" + custName + "%").andSvrTypeLike("%" + type + "%").andSvrDisposeEqualTo(userName);
		List<CstService> servicelist = serviceMapper.selectByExample(example);
		PageInfo<CstService> serviceinfo = new PageInfo<CstService>(servicelist);
		return serviceinfo;
	}

	@Override
	public void saveOrUpdate(CstService service) {
		if (service.getSvrId() == null) {
			serviceMapper.insertSelective(service);
		} else {
			serviceMapper.updateByPrimaryKeySelective(service);
		}

	}

	@Override
	public CstService findServiceById(long serviceId) {
		// TODO Auto-generated method stub
		CstService service = serviceMapper.selectByPrimaryKey(serviceId);
		return service;
	}

	@Override
	public PageInfo<CstService> findServiceEqualTo(int curPage, int size, String type, String status) {
		CstServiceExample example = new CstServiceExample();
		example.createCriteria().andSvrTypeEqualTo(type).andSvrStatusEqualTo(status);
		List<CstService> servicelist = serviceMapper.selectByExample(example);
		PageInfo<CstService> serviceinfo = new PageInfo<CstService>(servicelist);
		return serviceinfo;
	}

	

}
