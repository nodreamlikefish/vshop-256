package cn.mldn.vshop.service.front.impl;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IShopcarDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.front.IShopcarServiceFront;
import cn.mldn.vshop.vo.Shopcar;

public class ShopcarServiceFrontImpl extends AbstractService
		implements
			IShopcarServiceFront {

	@Override
	public boolean add(String mid, int gid) throws Exception {
		// 1、首先需要判断当前的商品信息是否存在
		IShopcarDAO shopcarDAO = Factory.getDAOInstance("shopcar.dao") ;
		Shopcar sc = shopcarDAO.findByMemberAndGoods(mid, gid) ;
		if (sc == null) {	// 第一次添加数据
			Shopcar vo = new Shopcar() ;
			vo.setMid(mid);
			vo.setGid(gid);
			vo.setAmount(1); 
			return shopcarDAO.doCreate(vo) ;
		} else {
			return shopcarDAO.doUpdateIncrementById(mid, gid, sc.getAmount() + 1) ;
		}
	}
}
