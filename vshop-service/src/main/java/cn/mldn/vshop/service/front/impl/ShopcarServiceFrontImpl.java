package cn.mldn.vshop.service.front.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IGoodsDAO;
import cn.mldn.vshop.dao.IShopcarDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.front.IShopcarServiceFront;
import cn.mldn.vshop.vo.Goods;
import cn.mldn.vshop.vo.Shopcar;

public class ShopcarServiceFrontImpl extends AbstractService
		implements
			IShopcarServiceFront {
	@Override
	public boolean deleteByMember(String mid, Set<Integer> gids)
			throws Exception {
		if (gids == null || gids.size() == 0) {
			return false ;
		}
		IShopcarDAO shopcarDAO = Factory.getDAOInstance("shopcar.dao") ;
		return shopcarDAO.doRemoveByMemberAndGoods(mid, gids);
	}
	
	@Override
	public boolean editAmounts(String mid, Map<Integer, Integer> sc)
			throws Exception {
		if (sc.size() == 0) {
			return false ;
		}
		IShopcarDAO shopcarDAO = Factory.getDAOInstance("shopcar.dao") ;
		return shopcarDAO.doUpdateAmountBatch(mid, sc); 
	}
	
	@Override
	public boolean editAmount(String mid, int gid, int amount)
			throws Exception {
		IShopcarDAO shopcarDAO = Factory.getDAOInstance("shopcar.dao") ;
		if (amount == 0) {	// 数量已经没有了，那么应该进行删除处理
			return shopcarDAO.doRemoveByMemberAndGid(mid, gid) ;
		}
		if (amount > 0) {	// 有数量
			return shopcarDAO.doUpdateIncrementById(mid, gid, amount) ;
		}
		return false;
	}
	@Override
	public Map<String, Object> list(String mid) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		IShopcarDAO shopcarDAO = Factory.getDAOInstance("shopcar.dao") ;
		Map<Long,Integer> shopcar = shopcarDAO.findAllByMember(mid) ;
		if (shopcar.size() > 0) {
			IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
			List<Goods> allGoodss = goodsDAO.findAllByIds(shopcar.keySet()) ;
			map.put("allGoodss", allGoodss) ;
		}
		map.put("allShopcars", shopcar) ;
		return map;
	}

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
