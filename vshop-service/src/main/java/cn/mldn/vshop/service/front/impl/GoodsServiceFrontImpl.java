package cn.mldn.vshop.service.front.impl;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IGoodsDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.front.IGoodsServiceFront;
import cn.mldn.vshop.vo.Goods;

public class GoodsServiceFrontImpl extends AbstractService
		implements
			IGoodsServiceFront {

	@Override
	public Goods get(int gid) throws Exception {
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
		return goodsDAO.findById(gid);
	}

}
