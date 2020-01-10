package com.briup.crm.service;

import com.briup.crm.bean.SysUser;
import com.github.pagehelper.PageInfo;

public interface UserService {
	public SysUser login(String name,String password) throws Exception;
	
	public PageInfo<SysUser> findAllUser(int curPage,int size);
	
	public PageInfo<SysUser> findServiceEqualTo(int curPage,int size,String type);
	
	public void saveOrUpdate(SysUser user);
	
	public SysUser findUserById(long usrId);

	public void deleteUserById(long usrId);
}
