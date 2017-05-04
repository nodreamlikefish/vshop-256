package cn.mldn.vshop.service.back.impl;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IDetailsDAO;
import cn.mldn.vshop.dao.IGoodsDAO;
import cn.mldn.vshop.dao.IOrdersDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.back.IOrdersServiceBack;
import cn.mldn.vshop.vo.Orders;

public class OrdersServiceBackImpl extends AbstractService
		implements
			IOrdersServiceBack {
	
	@Override
	public Map<String, Object> get(int oid) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>() ;
		// 1、根据用户编号和订单编号获取订单信息
		IOrdersDAO ordersDAO = Factory.getDAOInstance("orders.dao") ;
		Orders orders = ordersDAO.findById(oid) ;
		if (orders != null) {	// 已经获取了该订单的信息
			map.put("orders", orders) ;
			// 2、应该根据订单信息获取全部的详情信息，但是考虑到随后还需要进行EL控制。
			IDetailsDAO detailsDAO = Factory.getDAOInstance("details.dao") ;
			Map<Long,Integer> detailsMap = detailsDAO.findAllByOrders(oid) ;
			map.put("allDetailss", detailsMap) ;
			// 3、取得所有的购买的商品信息
			IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
			map.put("allGoodss", goodsDAO.findAllByIds(detailsMap.keySet())) ;
		}
		return map;
	}

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
