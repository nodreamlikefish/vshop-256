package cn.mldn.vshop.service.back.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IAddressDAO;
import cn.mldn.vshop.dao.IMemberDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.back.IMemberServiceBack;
import cn.mldn.vshop.vo.Member;

public class MemberServiceBackImpl extends AbstractService
		implements
			IMemberServiceBack {
	
	@Override
	public Map<String, Object> getDetails(String mid) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		IMemberDAO memberDAO = Factory.getDAOInstance("member.dao");
		Member vo = memberDAO.findById(mid) ;
		if (vo != null) {	// 当前已经查询到了用户的信息
			map.put("member", vo) ;
			IAddressDAO addressDAO = Factory.getDAOInstance("address.dao") ;
			map.put("allAddress", addressDAO.findAllByMember(mid)) ;
		}
		return map;
	}
	
	@Override
	public boolean editLocked(Set<String> mid, int locked) throws Exception {
		IMemberDAO memberDAO = Factory.getDAOInstance("member.dao");
		Iterator<String> iter = mid.iterator() ;
		while (iter.hasNext()) {
			memberDAO.doUpdateLocked(iter.next(), locked) ;
		}
		return true ;
	}
	@Override
	public boolean editPassword(String mid, String password) throws Exception {
		IMemberDAO memberDAO = Factory.getDAOInstance("member.dao");
		return memberDAO.doUpdatePassword(mid, password);
	}
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
