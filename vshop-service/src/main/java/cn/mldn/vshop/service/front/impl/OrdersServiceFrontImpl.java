package cn.mldn.vshop.service.front.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IAddressDAO;
import cn.mldn.vshop.dao.IGoodsDAO;
import cn.mldn.vshop.dao.IShopcarDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.front.IOrdersServiceFront;

public class OrdersServiceFrontImpl extends AbstractService
		implements
			IOrdersServiceFront {

	@Override
	public Map<String, Object> getAddPre(String mid, Set<Long> gid)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		IShopcarDAO shopcarDAO = Factory.getDAOInstance("shopcar.dao") ;
		// 1、取得现在所有购物车中的商品数量，那么最终需要的时候是通过该数量进行信息显示。
		map.put("allShopcars", shopcarDAO.findAllByMember(mid, gid)) ;
		// 2、还需要知道指定商品编号对应的数据信息
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
		map.put("allGoodss",goodsDAO.findAllByIds(gid) ) ;
		// 3、需要知道用户所有的配送地址信息
		IAddressDAO addressDAO = Factory.getDAOInstance("address.dao") ;
		map.put("allAddress", addressDAO.findAllByMember(mid)) ;
		return map;
	}

}
