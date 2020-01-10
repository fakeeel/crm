package com.briup.crm.service;

import com.briup.crm.bean.CstLinkman;
import com.github.pagehelper.PageInfo;

public interface linkmanService {

	public PageInfo<CstLinkman> findLinkmanByCustId(long custId,int curPage,int size);
	
	public void deleteLinkmanById(long id);
	
	public void saveOrUpdate(CstLinkman lkm);
	
	public CstLinkman findLinkmanById(long lkmId);
}
