package com.briup.crm.service;

import com.briup.crm.bean.CstService;
import com.github.pagehelper.PageInfo;

public interface ServiceService {
	public PageInfo<CstService> findService(int curPage,int size);
	
	public PageInfo<CstService> findServiceByUserName(int curPage,int size,String userName);
	
	public PageInfo<CstService> findServiceLike(int curPage,int size,String custName,String type,String userName);
	
	public PageInfo<CstService> findServiceEqualTo(int curPage,int size,String type,String status);
	
	public void saveOrUpdate(CstService service);
	
	public CstService findServiceById(long serviceId);
	
}
