package cn.mldn.vshop.service.back.impl;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IOrdersDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.back.IOrdersServiceBack;

public class OrdersServiceBackImpl extends AbstractService
		implements
			IOrdersServiceBack {

	@Override
	public Map<String, Object> list(int currentPage, int lineSize,
			String column, String keyWord) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		IOrdersDAO ordersDAO = Factory.getDAOInstance("orders.dao");
		if (super.isEmpty(column, keyWord)) {
			map.put("allOrderss",
					ordersDAO.findAllSplit(currentPage, lineSize));
			map.put("allRecorders", ordersDAO.getAllCount());
		} else {
			map.put("allOrderss", ordersDAO.findAllSplit(currentPage, lineSize,
					column, keyWord));
			map.put("allRecorders", ordersDAO.getAllCount(column, keyWord));
		}
		return map;
	}

}
