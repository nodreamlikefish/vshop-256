package cn.mldn.vshop.service.back.impl;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IItemDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.back.IGoodsServiceBack;

public class GoodsServiceBackImpl extends AbstractService
		implements
			IGoodsServiceBack {
	@Override
	public Map<String, Object> getAddPre() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		IItemDAO itemDAO = Factory.getDAOInstance("item.dao");
		map.put("allItems", itemDAO.findAll());
		return map;
	}

}
