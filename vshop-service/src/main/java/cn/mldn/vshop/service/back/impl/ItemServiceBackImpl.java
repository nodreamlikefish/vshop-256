package cn.mldn.vshop.service.back.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IItemDAO;
import cn.mldn.vshop.dao.ISubitemDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.back.IItemServiceBack;
import cn.mldn.vshop.vo.Item;
import cn.mldn.vshop.vo.Subitem;

public class ItemServiceBackImpl extends AbstractService
		implements
			IItemServiceBack {
	@Override
	public Map<Item, List<Subitem>> listDetails() throws Exception {
		Map<Item, List<Subitem>> map = new HashMap<Item, List<Subitem>>() ;
		IItemDAO itemDAO = Factory.getDAOInstance("item.dao");
		ISubitemDAO subitemDAO = Factory.getDAOInstance("subitem.dao");
		List<Item> allItems = itemDAO.findAll() ;	// 查询出所有的一级栏目
		Iterator<Item> iter = allItems.iterator() ;
		while (iter.hasNext()) {
			Item item = iter.next() ;
			map.put(item, subitemDAO.findAllByItem(item.getIid())) ;
		}
		return map ;
	}
	@Override
	public boolean edit(Item vo) throws Exception {
		IItemDAO itemDAO = Factory.getDAOInstance("item.dao");
		return itemDAO.doUpdate(vo);
	}
	@Override
	public List<Item> list() throws Exception {
		IItemDAO itemDAO = Factory.getDAOInstance("item.dao");
		return itemDAO.findAll();
	}

}
