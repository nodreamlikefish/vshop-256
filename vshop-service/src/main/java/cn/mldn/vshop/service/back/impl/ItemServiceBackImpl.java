package cn.mldn.vshop.service.back.impl;

import java.util.List;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IItemDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.back.IItemServiceBack;
import cn.mldn.vshop.vo.Item;

public class ItemServiceBackImpl extends AbstractService
		implements
			IItemServiceBack {
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
