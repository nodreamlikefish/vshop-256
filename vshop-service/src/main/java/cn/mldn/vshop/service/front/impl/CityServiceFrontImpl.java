package cn.mldn.vshop.service.front.impl;
import java.util.List;

import cn.mldn.util.factory.Factory;
import cn.mldn.vshop.dao.ICityDAO;
import cn.mldn.vshop.service.abs.AbstractService;
import cn.mldn.vshop.service.front.ICityServiceFront;
import cn.mldn.vshop.vo.City;
public class CityServiceFrontImpl extends AbstractService
		implements
			ICityServiceFront {
	@Override
	public List<City> listByProvince(int pid) throws Exception {
		ICityDAO cityDAO = Factory.getDAOInstance("city.dao") ;
		return cityDAO.findAllByProvince(pid); 
	}
}
