package cn.mldn.vshop.service.back.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IGoodsDAO;
import cn.mldn.vshop.dao.IItemDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.back.IGoodsServiceBack;
import cn.mldn.vshop.vo.Goods;

public class GoodsServiceBackImpl extends AbstractService
		implements
			IGoodsServiceBack {
	@Override
	public boolean add(Goods vo) throws Exception {
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
		vo.setPubdate(new Date());// 商品发布日期为今天
		vo.setDelflag(0); 	// 商品没有删除掉
		return goodsDAO.doCreate(vo);
	}
	@Override
	public Map<String, Object> getAddPre() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		IItemDAO itemDAO = Factory.getDAOInstance("item.dao");
		map.put("allItems", itemDAO.findAll());
		return map;
	}

}
