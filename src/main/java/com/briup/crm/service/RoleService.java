package com.briup.crm.service;

import com.briup.crm.bean.SysRole;
import com.github.pagehelper.PageInfo;

public interface RoleService {

	public PageInfo<SysRole> findAllRole(int curPage,int size);
	
	public void saveOrUpdate(SysRole role);
	
	public SysRole findRoleById(long roleId);
	
	public void deleteRoleById(long roleId);
}
