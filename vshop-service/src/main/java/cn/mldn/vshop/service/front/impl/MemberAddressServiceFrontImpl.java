package cn.mldn.vshop.service.front.impl;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IProvinceDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.front.IMemberAddressServiceFront;

public class MemberAddressServiceFrontImpl extends AbstractService
		implements
			IMemberAddressServiceFront {
	@Override
	public Map<String, Object> getAddPre() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		IProvinceDAO provinceDAO = Factory.getDAOInstance("province.dao");
		map.put("allProvinces", provinceDAO.findAll()) ;
		return map ;
	}

}
