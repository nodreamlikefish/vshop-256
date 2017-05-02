package cn.mldn.vshop.service.back.impl;

import java.util.List;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.ISubitemDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.back.ISubitemServiceBack;
import cn.mldn.vshop.vo.Subitem;

public class SubitemServiceBackImpl extends AbstractService
		implements
			ISubitemServiceBack {

	@Override
	public List<Subitem> listByItem(int iid) throws Exception {
		ISubitemDAO subitemDAO = Factory.getDAOInstance("subitem.dao");
		return subitemDAO.findAllByItem(iid);
	}

}
