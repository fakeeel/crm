package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.SysRole;
import com.briup.crm.bean.SysRoleExample;
import com.briup.crm.dao.SysRoleMapper;
import com.briup.crm.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private SysRoleMapper roleMapper;
	
	@Override
	public PageInfo<SysRole> findAllRole(int curPage, int size) {
		PageHelper.startPage(curPage, size);
		SysRoleExample example = new SysRoleExample();
		List<SysRole> rolelist = roleMapper.selectByExample(example);
		PageInfo<SysRole> roleinfo = new PageInfo<SysRole>(rolelist);
		return roleinfo;
	}

	@Override
	public void saveOrUpdate(SysRole role) {
		if(role.getRoleId()==null) {
			roleMapper.insertSelective(role);
		}else {
			roleMapper.updateByPrimaryKey(role);
		}
	}

	@Override
	public SysRole findRoleById(long roleId) {
		SysRole role = roleMapper.selectByPrimaryKey(roleId);
		return role;
	}

	@Override
	public void deleteRoleById(long roleId) {
		roleMapper.deleteByPrimaryKey(roleId);
	}
	

}
