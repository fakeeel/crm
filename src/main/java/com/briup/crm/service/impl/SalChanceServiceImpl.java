package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.SalChanceExample;
import com.briup.crm.bean.extend.SalChanceExtend;
import com.briup.crm.dao.SalChanceMapper;
import com.briup.crm.dao.extend.SalChanceExtendMapper;
import com.briup.crm.service.SalChanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SalChanceServiceImpl implements SalChanceService {

	@Autowired
	private SalChanceMapper chanceMapper;
	@Autowired
	private SalChanceExtendMapper chanceExtendMapper;

	@Override
	public PageInfo<SalChance> findSalChance(int curPage, int size) {
		PageHelper.startPage(curPage, size);
		SalChanceExample example = new SalChanceExample();
		List<SalChance> chancelist = chanceMapper.selectByExample(example);
		PageInfo<SalChance> chanceinfo = new PageInfo<SalChance>(chancelist);
		return chanceinfo;
	}

	@Override
	public PageInfo<SalChance> findSalChanceLike(int curPage, int size, String custName, String region) {
		SalChanceExample example = new SalChanceExample();
		example.createCriteria().andChcCustNameLike("%" + custName + "%").andChcAddrLike("%" + region + "%");
		List<SalChance> chancelist = chanceMapper.selectByExample(example);
		PageInfo<SalChance> chanceinfo = new PageInfo<SalChance>(chancelist);
		return chanceinfo;
	}

	@Override
	public void saveOrUpdate(SalChance chance) {
		if (chance.getChcId() == null) {
			chanceMapper.insertSelective(chance);
		} else {
			chanceMapper.updateByPrimaryKeySelective(chance);
		}
	}

	@Override
	public SalChance findChanceById(long chanceId) {
		SalChance chance = chanceMapper.selectByPrimaryKey(chanceId);
		return chance;
	}

	@Override
	public void deleteChanceById(long chanceId) {
		chanceMapper.deleteByPrimaryKey(chanceId);
	}

	@Override
	public PageInfo<SalChance> findChanceByUserName(int curPage,int size,String dueTo) {
		PageHelper.startPage(curPage, size);
		SalChanceExample example = new SalChanceExample();
		example.createCriteria().andChcDueToEqualTo(dueTo);
		List<SalChance> chancelist = chanceMapper.selectByExample(example);
		PageInfo<SalChance> chanceinfo = new PageInfo<SalChance>(chancelist);
		return chanceinfo;
	}

	@Override
	public PageInfo<SalChance> findChanceByUserNameAndRegion(int curPage, int size, String dueTo, String region) {
		// TODO Auto-generated method stub
		PageHelper.startPage(curPage, size);
		SalChanceExample example = new SalChanceExample();
		example.createCriteria().andChcDueToEqualTo(dueTo).andChcAddrEqualTo(region);
		List<SalChance> chancelist = chanceMapper.selectByExample(example);
		PageInfo<SalChance> chanceinfo = new PageInfo<SalChance>(chancelist);
		return chanceinfo;
	}

	@Override
	public SalChanceExtend findChanceWithPlanById(long id) {
		SalChanceExtend salChanceExtend = chanceExtendMapper.selectChanceWithPlanById(id);
		return salChanceExtend;
	}

}
