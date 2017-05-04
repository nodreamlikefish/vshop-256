package cn.mldn.vshop.service.abs;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.IActionDAO;

public abstract class AbstractService {
	public boolean exists(String mid,String flag) throws Exception {
		IActionDAO actionDAO = Factory.getDAOInstance("action.dao") ;
		return actionDAO.findActionByMember(mid, flag) ;
	}
	public boolean isEmpty(String column,String keyWord) {
		return column == null || keyWord == null || "".equals(column) || "".equals(keyWord) ;
	}
}
