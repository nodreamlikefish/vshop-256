package cn.mldn.vshop.service.back.impl;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IMemberDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.back.IMemberServiceBack;

public class MemberServiceBackImpl extends AbstractService
		implements
			IMemberServiceBack {

	@Override
	public Map<String, Object> list(int currentPage, int lineSize,
			String column, String keyWord) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		IMemberDAO memberDAO = Factory.getDAOInstance("member.dao");
		if (super.isEmpty(column, keyWord)) { // 查询全部
			map.put("allMembers",
					memberDAO.findAllSplit(currentPage, lineSize));
			map.put("allRecorders", memberDAO.getAllCount());
		} else { // 模糊查询
			map.put("allMembers", memberDAO.findAllSplit(currentPage, lineSize,
					column, keyWord));
			map.put("allRecorders", memberDAO.getAllCount(column, keyWord));
		}
		return map;
	}

}
