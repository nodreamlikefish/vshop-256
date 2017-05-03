package cn.mldn.vshop.service.front.impl;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IGoodsDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.front.IGoodsServiceFront;
import cn.mldn.vshop.vo.Goods;

public class GoodsServiceFrontImpl extends AbstractService
		implements
			IGoodsServiceFront { 
	@Override
	public Map<String, Object> listBySubitem(int sid,int currentPage,int lineSize) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
		map.put("allRecorders", goodsDAO.getAllCountBySubitem(sid)) ;
		map.put("allGoodss", goodsDAO.findAllSplitBySubitem(sid, currentPage, lineSize)) ;
		return map;
	}
	@Override
	public Goods get(int gid) throws Exception {
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
		return goodsDAO.findById(gid);
	}

}
