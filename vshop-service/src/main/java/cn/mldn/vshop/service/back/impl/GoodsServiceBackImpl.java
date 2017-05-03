package cn.mldn.vshop.service.back.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IGoodsDAO;
import cn.mldn.vshop.dao.IItemDAO;
import cn.mldn.vshop.dao.ISubitemDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.back.IGoodsServiceBack;
import cn.mldn.vshop.vo.Goods;

public class GoodsServiceBackImpl extends AbstractService
		implements
			IGoodsServiceBack {
	@Override
	public List<Goods> listDetails() throws Exception {
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
		return goodsDAO.findAllSplit(1, 12); 	// 显示12条数据信息
	}
	@Override
	public boolean delete(Set<Integer> gid) throws Exception {
		if (gid == null || gid.size() == 0) {
			return false ;
		}
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
		return goodsDAO.doUpdateDeflag(gid, 1);
	}
	@Override
	public boolean edit(Goods vo) throws Exception {
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
		return goodsDAO.doUpdate(vo);
	}
	@Override
	public Map<String, Object> getEditPre(int gid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
		Goods vo = goodsDAO.findById(gid) ;
		if (vo != null) {
			IItemDAO itemDAO = Factory.getDAOInstance("item.dao");
			ISubitemDAO subitemDAO = Factory.getDAOInstance("subitem.dao");
			map.put("allItems", itemDAO.findAll());
			map.put("allSubitems", subitemDAO.findAllByItem(vo.getIid())) ;
			map.put("goods", vo) ;
		}
		return map;
	}
	@Override
	public Map<String,Object> show(int gid) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
		IItemDAO itemDAO = Factory.getDAOInstance("item.dao") ;
		ISubitemDAO subitemDAO = Factory.getDAOInstance("subitem.dao") ;
		Goods goods = goodsDAO.findById(gid) ;
		map.put("goods", goods) ;
		map.put("item", itemDAO.findById(goods.getIid())) ;
		map.put("subitem", subitemDAO.findById(goods.getSid())) ;
		return map; 
	}
	@Override
	public Map<String, Object> list(int currentPage, int lineSize,
			String column, String keyWord) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		IGoodsDAO goodsDAO = Factory.getDAOInstance("goods.dao") ;
		if (column == null || keyWord == null || "".equals(column) || "".equals(keyWord)) {
			map.put("allGoodss", goodsDAO.findAllSplit(currentPage, lineSize)) ;
			map.put("allRecorders",goodsDAO.getAllCount()) ;
		}  else {
			map.put("allGoodss", goodsDAO.findAllSplit(currentPage, lineSize,column,keyWord)) ;
			map.put("allRecorders",goodsDAO.getAllCount(column,keyWord)) ;
		}
		return map;
	}
	
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
